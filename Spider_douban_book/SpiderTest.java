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
			fail("SendGet() 结果不一致");
		}
	}

	@Test
	public void testGetBooks() {
		String content=Spider.SendGet("https://book.douban.com/tag/%E7%BC%96%E7%A8%8B");
		ArrayList<Book> bookList=Spider.GetBooks(content);
		ArrayList<Book> realBookList=new ArrayList<Book>();
		Book book0=new Book("计算机程序的构造和解释","Harold Abelson、Gerald Jay Sussman、Julie Sussman / 裘宗燕 / 机械工业出版社 / 2004-2 / 45.00元","9.5","1869");
		realBookList.add(book0);
		Book book1=new Book("编码","[美] Charles Petzold / 左飞、薛佟佟 / 电子工业出版社 / 2010 / 55.00元","9.2","1957");
		realBookList.add(book1);
		Book book2=new Book("代码大全（第2版）","[美] 史蒂夫·迈克康奈尔 / 金戈、汤凌、陈硕、张菲 译、裘宗燕 审校 / 电子工业出版社 / 2006-3 / 128.00元","9.3","3397");
		realBookList.add(book2);
		Book book3=new Book("C程序设计语言","（美）Brian W. Kernighan、（美）Dennis M. Ritchie / 徐宝文、李志译、尤晋元审校 / 机械工业出版社 / 2004-1 / 30.00元","9.4","3907");
		realBookList.add(book3);
		Book book4=new Book("算法导论（原书第2版）","[美] Thomas H.Cormen、Charles E.Leiserson、Ronald L.Rivest、Clifford Stein / 潘金贵 等 / 机械工业出版社 / 2006-9 / 85.00元","9.3","4699");
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
			fail("GetBooks() 结果不一致");
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
			fail("GetLinkUrl() 结果不一致");
		}
		
	}

}
