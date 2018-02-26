import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BookTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBook() {
		Boolean isPass=true;
		Book book0=new Book("���������Ĺ���ͽ���","Harold Abelson��Gerald Jay Sussman��Julie Sussman / ������ / ��е��ҵ������ / 2004-2 / 45.00Ԫ","9.5","1869");
		if(!book0.title.equals("���������Ĺ���ͽ���")) {
			isPass=false;
		}
		if(!book0.authors.equals("Harold Abelson��Gerald Jay Sussman��Julie Sussman")) {
			isPass=false;
		}
		if(!book0.press.equals("��е��ҵ������")) {
			isPass=false;
		}
		if(!book0.date.equals("2004-2")) {
			isPass=false;
		}
		if(!book0.price.equals("45.00Ԫ")) {
			isPass=false;
		}
		if(book0.score!=9.5) {
			isPass=false;
		}
		if(book0.numReviewers!=1869) {
			isPass=false;
		}
		if(!isPass) {
			fail("Book() �����һ��");
		}
	}

	@Test
	public void testCompareTo() {
		Book book0=new Book("���������Ĺ���ͽ���","Harold Abelson��Gerald Jay Sussman��Julie Sussman / ������ / ��е��ҵ������ / 2004-2 / 45.00Ԫ","9.5","1869");
		Book book1=new Book("����","[��] Charles Petzold / ��ɡ�Ѧ١١ / ���ӹ�ҵ������ / 2010 / 55.00Ԫ","9.2","1957");
		int result=book0.compareTo(book1);
		assertEquals("CompareTo() �����һ��", -1,result);
	}

	@Test
	public void testToString() {
		Book book0=new Book("���������Ĺ���ͽ���","Harold Abelson��Gerald Jay Sussman��Julie Sussman / ������ / ��е��ҵ������ / 2004-2 / 45.00Ԫ","9.5","1869");
		String result="����:"+" ���������Ĺ���ͽ���"+"\n����:"+" Harold Abelson��Gerald Jay Sussman��Julie Sussman"+"\n������:"+" ��е��ҵ������"+"\n����ʱ��:"+" 2004-2"
		+"\n�۸�:"+" 45.00Ԫ"+"\n����:"+" 9.5"+"\n��������:"+" 1869\n";
		if(!book0.toString().equals(result)) {
			fail("toString() �����һ��");
		}
	}

}
