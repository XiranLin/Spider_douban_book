import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Spider{
  static String SendGet(String url){
    String result="";
    BufferedReader in=null;
    try{
      URL realUrl=new URL(url);
      URLConnection connection = realUrl.openConnection();
      connection.connect();
      in=new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
      String line;
      while((line=in.readLine())!=null){
        result+=line;
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    finally{
      try{
        if(in!=null){
          in.close();
        }
      }catch(Exception e2){
        e2.printStackTrace();
      }
    }
    return result;
  }
  
  static ArrayList<Book> GetBooks(String content){
    ArrayList<Book> bookList=new ArrayList<Book>();
    Pattern pattern=
      Pattern.compile("<h2 class=\"\">.*?<a href=\".*?\".*?title=\"(.+?)\".*?</h2>.*?<div class=\"pub\">(.+?)</div>.*?<span class=\"rating_nums\">(.+?)</span>.*?<span class=\"pl\">.*?\\((\\d+)");
    Matcher matcher=pattern.matcher(content);
    Boolean isFind=matcher.find();;
    while(isFind){
      if(Integer.parseInt(matcher.group(4))>1000){
        Book currBook=new Book(matcher.group(1),matcher.group(2),matcher.group(3),matcher.group(4));
        bookList.add(currBook);
      }
      isFind=matcher.find();
    }
    
    return bookList;
  }
  
  static ArrayList<String> GetLinkUrl(String content){
    ArrayList<String> result=new ArrayList<String>();
    Pattern pattern=Pattern.compile("<a href=\"/tag/.*?start=(\\d+)&amp;type=T\"");
    Matcher matcher=pattern.matcher(content);
    Boolean isFind=matcher.find();
    while(isFind){   
      String linkUrl=matcher.group(1);
      result.add(linkUrl);
      isFind=matcher.find();
    }
    return result;
  }
}
    