package com.javaaround;
import java.time.*;
import java.time.format.DateTimeFormatter;
public class App {
   public static void main( String[] args ){
      System.out.println( "Hello World!" );

      // give string default format yyyy-MM-dd
      LocalDate birthday = LocalDate.parse("1990-12-15"); 
      // give string other formate
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
      LocalDate birthday2 = LocalDate.parse("15-12-1990",formatter); 
      LocalTime birthtime = LocalTime.parse("13:30:15");
      LocalDateTime birthdaytime = LocalDateTime.parse("1990-12-15T13:30:15");
      
      System.out.println("current date " + birthday);
      System.out.println("current date " + birthday2);
      System.out.println("current time " + birthtime);
      System.out.println("current datetime " + birthdaytime);


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