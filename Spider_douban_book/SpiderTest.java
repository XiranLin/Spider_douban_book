import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class SpiderTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSendGet() {
		String result=Spider.SendGet("https://book.douban.com/tag/%E7%BC%96%E7%A8%8B");
		String fileName="sendGet_test.txt";
		String sendGet_test="";
		String line=null;
		Boolean isPass=true;
		try {
			FileReader fileReader=new FileReader(fileName);
			BufferedReader bufferedReader=new BufferedReader(fileReader);
			while((line=bufferedReader.readLine())!=null) {
				sendGet_test+=line;
			}
			bufferedReader.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		for(int i=0;i<sendGet_test.length();i++) {
			if(sendGet_test.charAt(i)!=result.charAt(i)) {
				isPass=false;
				break;
			}
		}
		if(!isPass) {
			fail("SendGet() �����һ��");
		}
	}

	@Test
	public void testGetBooks() {
		String content=Spider.SendGet("https://book.douban.com/tag/%E7%BC%96%E7%A8%8B");
		ArrayList<Book> bookList=Spider.GetBooks(content);
		ArrayList<Book> realBookList=new ArrayList<Book>();
		Book book0=new Book("���������Ĺ���ͽ���","Harold Abelson��Gerald Jay Sussman��Julie Sussman / ������ / ��е��ҵ������ / 2004-2 / 45.00Ԫ","9.5","1869");
		realBookList.add(book0);
		Book book1=new Book("����","[��] Charles Petzold / ��ɡ�Ѧ١١ / ���ӹ�ҵ������ / 2010 / 55.00Ԫ","9.2","1957");
		realBookList.add(book1);
		Book book2=new Book("�����ȫ����2�棩","[��] ʷ�ٷ����˿��ζ� / ��ꡢ���衢��˶���ŷ� �롢������ ��У / ���ӹ�ҵ������ / 2006-3 / 128.00Ԫ","9.3","3397");
		realBookList.add(book2);
		Book book3=new Book("C�����������","������Brian W. Kernighan��������Dennis M. Ritchie / �챦�ġ���־�롢�Ƚ�Ԫ��У / ��е��ҵ������ / 2004-1 / 30.00Ԫ","9.4","3907");
		realBookList.add(book3);
		Book book4=new Book("�㷨���ۣ�ԭ���2�棩","[��] Thomas H.Cormen��Charles E.Leiserson��Ronald L.Rivest��Clifford Stein / �˽�� �� / ��е��ҵ������ / 2006-9 / 85.00Ԫ","9.3","4699");
		realBookList.add(book4);
		Boolean isPass=true;
		for(int i=0;i<realBookList.size();i++) {
			Book book=bookList.get(i);
			Book realBook=realBookList.get(i);

			if(!book.title.equals(realBook.title)) {
				isPass=false;
				
				break;
			}
			if(!book.authors.equals(realBook.authors)) {
				isPass=false;
			
				break;
			}
			if(!book.press.equals(realBook.press)) {
				isPass=false;
				
				break;
			}
			if(!book.date.equals(realBook.date)) {
				isPass=false;
				
				break;
			}
			if(!book.price.equals(realBook.price)) {
				isPass=false;
				
				break;
			}
			if(book.score!=realBook.score) {
				isPass=false;
				
				break;
			}
			if(book.numReviewers!=realBook.numReviewers) {
				isPass=false;
				
				break;
			}
		}
		if(!isPass) {
			fail("GetBooks() �����һ��");
		}
		
	}

	@Test
	public void testGetLinkUrl() {
		String content=Spider.SendGet("https://book.douban.com/tag/%E7%BC%96%E7%A8%8B");
		ArrayList<String> linkUrls=Spider.GetLinkUrl(content);
		ArrayList<String> realLinkUrls=new ArrayList<String>();
		realLinkUrls.add("20");
		realLinkUrls.add("40");
		realLinkUrls.add("60");
		realLinkUrls.add("80");
		realLinkUrls.add("100");
		Boolean isPass=true;
		for(int i=0;i<realLinkUrls.size();i++) {
			String link=linkUrls.get(i);
			String realLink=realLinkUrls.get(i);
			if(!link.equals(realLink)) {
				isPass=false;
			}
		}
		if(!isPass) {
			fail("GetLinkUrl() �����һ��");
		}
		
	}

}
