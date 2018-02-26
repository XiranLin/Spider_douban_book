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
		Book book0=new Book("计算机程序的构造和解释","Harold Abelson、Gerald Jay Sussman、Julie Sussman / 裘宗燕 / 机械工业出版社 / 2004-2 / 45.00元","9.5","1869");
		if(!book0.title.equals("计算机程序的构造和解释")) {
			isPass=false;
		}
		if(!book0.authors.equals("Harold Abelson、Gerald Jay Sussman、Julie Sussman")) {
			isPass=false;
		}
		if(!book0.press.equals("机械工业出版社")) {
			isPass=false;
		}
		if(!book0.date.equals("2004-2")) {
			isPass=false;
		}
		if(!book0.price.equals("45.00元")) {
			isPass=false;
		}
		if(book0.score!=9.5) {
			isPass=false;
		}
		if(book0.numReviewers!=1869) {
			isPass=false;
		}
		if(!isPass) {
			fail("Book() 结果不一致");
		}
	}

	@Test
	public void testCompareTo() {
		Book book0=new Book("计算机程序的构造和解释","Harold Abelson、Gerald Jay Sussman、Julie Sussman / 裘宗燕 / 机械工业出版社 / 2004-2 / 45.00元","9.5","1869");
		Book book1=new Book("编码","[美] Charles Petzold / 左飞、薛佟佟 / 电子工业出版社 / 2010 / 55.00元","9.2","1957");
		int result=book0.compareTo(book1);
		assertEquals("CompareTo() 结果不一致", -1,result);
	}

	@Test
	public void testToString() {
		Book book0=new Book("计算机程序的构造和解释","Harold Abelson、Gerald Jay Sussman、Julie Sussman / 裘宗燕 / 机械工业出版社 / 2004-2 / 45.00元","9.5","1869");
		String result="书名:"+" 计算机程序的构造和解释"+"\n作者:"+" Harold Abelson、Gerald Jay Sussman、Julie Sussman"+"\n出版社:"+" 机械工业出版社"+"\n出版时间:"+" 2004-2"
		+"\n价格:"+" 45.00元"+"\n评分:"+" 9.5"+"\n评论人数:"+" 1869\n";
		if(!book0.toString().equals(result)) {
			fail("toString() 结果不一致");
		}
	}

}
