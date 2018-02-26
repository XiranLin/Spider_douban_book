public class Book implements Comparable<Book> {
  public String title;
  public String authors;
  public String press;
  public String date;
  public String price;
  public double score;
  public int numReviewers;
  
  public Book(String group1,String group2,String group3,String group4){
    String[] tokens=group2.trim().split("/");
    title=group1;
    authors=tokens[0].trim();
    press=tokens[tokens.length-3].trim();
    date=tokens[tokens.length-2].trim();
    price=tokens[tokens.length-1].trim();
    score=Double.parseDouble(group3);
    numReviewers=Integer.parseInt(group4);
  }
  @Override
  public int compareTo(Book compareBook){
    if(compareBook.score>this.score){
      return 1;
    }
    else if(compareBook.score==this.score){
      return 0;
    }
    else{
      return -1;
    }
  }
  @Override
  public String toString(){
    return "����: "+title+"\n����: "+authors+"\n������: " +press+"\n����ʱ��: "+date+"\n�۸�: "+price+"\n����: "+score+"\n��������: "+numReviewers+"\n";
  }
}
    