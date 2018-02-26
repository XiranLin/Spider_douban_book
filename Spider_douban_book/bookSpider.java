import java.util.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import java.io.FileOutputStream;  
import java.io.IOException;

public class bookSpider{
  private static ArrayList<Book> allBooksList=new ArrayList<Book>();
  private static ArrayList<String> allwaitUrl=new ArrayList<String>();
  private static ArrayList<String> alloverUrl=new ArrayList<>();
  private static ArrayList<String> allUrl=new ArrayList<>();
  private static int spareThread=0;
  private static int MAX_THREAD=5;
  private static Object signal=new Object();
  private static long start;
  private static long end;
  public static void main(String[] args) throws IOException{
    String url="https://book.douban.com/tag/%E7%BC%96%E7%A8%8B";
    start=System.currentTimeMillis();
    addUrl(url);
    for(int i=0;i<MAX_THREAD;i++) {
    	new bookSpider().new MyThread().start();
    }
  }
  
  public static void workUrl(String url){
	if(url!=null) {
		if(!(alloverUrl.contains(url))) {
			System.out.println("��ǰִ��: "+Thread.currentThread().getName()+" ������ȡ��"+url);
		}
	
		String content=Spider.SendGet(url);
		ArrayList<String> linkUrls=Spider.GetLinkUrl(content);
		for(String currLinkUrl:linkUrls){
			addUrl("https://book.douban.com/tag/%E7%BC%96%E7%A8%8B?start="+currLinkUrl+"&amp;type=T");
		}
		ArrayList<Book> bookList=Spider.GetBooks(content);
		for(Book book:bookList){
			addBook(book);
		}
   
    
		alloverUrl.add(url);
		System.out.println(url+"��ҳ��ɣ�����ȡ��"+alloverUrl.size()+", ʣ����ȡ������"+allwaitUrl.size());
		if(allwaitUrl.size()>0) {
			synchronized(signal) {
				signal.notify();
			}
		}
	}
  }
  
  public static synchronized void addUrl(String url) {
	  if(!allUrl.contains(url)) {
		  allwaitUrl.add(url);
		  allUrl.add(url);
	  }
  }
  public static synchronized void addBook(Book currBook) {
	  Boolean isFind=false;
	  for(Book book:allBooksList) {
		  if(currBook.title.equals(book.title)) {
			  isFind=true;
		  }
	  }
	  if(!isFind) {
		  allBooksList.add(currBook);
	  }
  }
  public static synchronized String getUrl() {
	  String result=null;
	  if(allwaitUrl.size()>0) {
		  result=allwaitUrl.get(0);
		  allwaitUrl.remove(0);
	  }  
	  return result;
  }
  
  public class MyThread extends Thread{
	  @Override
	  public void run() {
		  while(true){
			  if(allwaitUrl.size()>0) {
				  String url=getUrl();
				  workUrl(url);
			  }else {
				  System.out.println("��ǰ�߳�׼���������ȴ�������ȡ��"+this.getName());
				  spareThread++;
				  System.out.println("���� "+(MAX_THREAD-spareThread)+" �� Thread �������С���������������");
				  if(allwaitUrl.isEmpty()&&spareThread==MAX_THREAD) {
					  System.out.println("ȫ����ɣ� ����excel�ļ�");
					    Collections.sort(allBooksList);
					    HSSFWorkbook wb = new HSSFWorkbook();
					    try {
							 FileOutputStream fileOut=new FileOutputStream("result.xls");
							 HSSFSheet sheet=wb.createSheet("sheet0");
							 HSSFRow row1=sheet.createRow(0);
							 HSSFCell cell1=row1.createCell(0);
							 cell1.setCellValue("���");
							 HSSFCell cell2=row1.createCell(1);
							 cell2.setCellValue("����");
							 HSSFCell cell3=row1.createCell(2);
							 cell3.setCellValue("����");
							 HSSFCell cell4=row1.createCell(3);
							 cell4.setCellValue("��������");
							 HSSFCell cell5=row1.createCell(4);
							 cell5.setCellValue("����");
							 HSSFCell cell6=row1.createCell(5);
							 cell6.setCellValue("������");
							 HSSFCell cell7=row1.createCell(6);
							 cell7.setCellValue("��������");
							 HSSFCell cell8=row1.createCell(7);
							 cell8.setCellValue("�۸�");
							 for(int i=0;i<40;i++) {
								 Book currentBook=allBooksList.get(i);
								 HSSFRow row=sheet.createRow(i+1);
								 HSSFCell cell11=row.createCell(0);
								 cell11.setCellValue(i+1);
								 HSSFCell cell12=row.createCell(1);
								 cell12.setCellValue(currentBook.title);
								 HSSFCell cell13=row.createCell(2);
								 cell13.setCellValue(currentBook.score);
								 HSSFCell cell14=row.createCell(3);
								 cell14.setCellValue(currentBook.numReviewers);
								 HSSFCell cell15=row.createCell(4);
								 cell15.setCellValue(currentBook.authors);
								 HSSFCell cell16=row.createCell(5);
								 cell16.setCellValue(currentBook.press);
								 HSSFCell cell17=row.createCell(6);
								 cell17.setCellValue(currentBook.date);
								 HSSFCell cell18=row.createCell(7);
								 cell18.setCellValue(currentBook.price); 
							 }
							 wb.write(fileOut);
							 fileOut.close();
							 wb.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					    end=System.currentTimeMillis();
					    System.out.println("�ܺ�ʱ "+(end-start)+" ����");
					    System.exit(1);
					    break;
				  }
				  synchronized(signal) {
					  try {
						  signal.wait();
					  }catch(Exception e) {
						  
					  }
				  }
				  spareThread--;
			  }
			  
			  
		  }
	  }
  }
  
}