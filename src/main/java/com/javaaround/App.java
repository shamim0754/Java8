package com.javaaround;
import java.time.*;
import java.time.format.DateTimeFormatter;
public class App {
   public static void main( String[] args ){
      System.out.println( "Hello World!" );

      //now
      LocalDate curdate = LocalDate.now();
      System.out.println("current date " + curdate);

      //formatter
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
      System.out.println(curdate.format(formatter));

      //parse string to date
      String now =  "15-04-2017";
      LocalDate nowdate = LocalDate.parse(now, formatter);
      System.out.println(nowdate);

      //plus day/month from a date 
      LocalDate tomorrow = curdate.plusDays(1); 
      LocalDate nextMonth = curdate.plusMonths(1); 
      System.out.println("tomorrow " + tomorrow);
      System.out.println("nextMonth " + nextMonth);
      

      //plus day/month from a date
      LocalDate yesterday = curdate.minusDays(1);
      LocalDate beforeMonth = curdate.minusMonths(1);
      System.out.println("yesterday " + yesterday);
      System.out.println("beforeMonth " + beforeMonth);
     
   }
}