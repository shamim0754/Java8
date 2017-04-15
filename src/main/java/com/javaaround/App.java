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
   }
}