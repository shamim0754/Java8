package com.javaaround;
import java.time.*;
import java.time.format.DateTimeFormatter;
public class App {
   public static void main( String[] args ){
      System.out.println( "Hello World!" );

      //now
      LocalDate birthday = LocalDate.of(1990, Month.DECEMBER, 15);
      LocalTime birthtime = LocalTime.of(13, 30, 15);
      LocalDateTime birthdaytime = LocalDateTime.of(1990, Month.DECEMBER, 15,13, 30, 15);
      
      System.out.println("current date " + birthday);
      System.out.println("current time " + birthtime);
      System.out.println("current datetime " + birthdaytime);

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
      
      /*//
      LocalDate birthday = LocalDate.parse("25-11-1988", formatter);
      if(curdate.equals())*/
   }
}