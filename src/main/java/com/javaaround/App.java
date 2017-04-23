package com.javaaround;
import java.time.*;
import java.time.format.DateTimeFormatter;
public class App {
   public static void main( String[] args ){
      System.out.println( "Hello World!" );
      LocalDate curdate = LocalDate.now();
      //Extract year 
      System.out.println("year = " + curdate.getYear());

      //Extract month 
      System.out.println("month = " + curdate.getMonth());

      //Extract month value 
      System.out.println("month value = " + curdate.getMonthValue());

      //Extract day value 
      System.out.println("day = " + curdate.getDayOfMonth());
      
   }
}