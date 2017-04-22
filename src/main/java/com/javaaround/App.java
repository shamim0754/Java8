package com.javaaround;
import java.time.*;
import java.time.format.DateTimeFormatter;
public class App {
   public static void main( String[] args ){
      System.out.println( "Hello World!" );

      LocalDate date1 = LocalDate.of(2009, 12, 31);
      LocalDate date2 = LocalDate.of(2010, 01, 31);
      if (date1.isAfter(date2)) 
            System.out.println("Date1 is after Date2");

      if (date1.isBefore(date2)) 
         System.out.println("Date1 is before Date2");
     
      if (date1.isEqual(date2))  // or date1.equals(date2)
         System.out.println("Date1 is equal Date2");
     
      //Above example using compareTo method
      /*//plus day/month from a date 
      LocalDate tomorrow = curdate.plusDays(1); 
      LocalDate nextMonth = curdate.plusMonths(1); 
      System.out.println("tomorrow " + tomorrow);
      System.out.println("nextMonth " + nextMonth);
      

      //plus day/month from a date
      LocalDate yesterday = curdate.minusDays(1);
      LocalDate beforeMonth = curdate.minusMonths(1);
      System.out.println("yesterday " + yesterday);
      System.out.println("beforeMonth " + beforeMonth);
      
      /*
      LocalDate birthday = LocalDate.parse("25-11-1988", formatter);
      if(curdate.equals())*/
   }
}