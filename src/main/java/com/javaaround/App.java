package com.javaaround;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.time.chrono.HijrahDate;
import java.time.temporal.Temporal;  
import java.util.*;
import static java.time.temporal.TemporalAdjusters.*;
public class App {
   public static void main( String[] args ){
      System.out.println( "Hello World!" );
     /* HijrahDate ramadan = HijrahDate.now()
                .with(ChronoField.DAY_OF_MONTH, 1).with(ChronoField.MONTH_OF_YEAR, 9);
        System.out.println("HijrahDate : " + ramadan);

        //HijrahDate -> LocalDate
        System.out.println("\n--- Ramandan 2016 ---");
        System.out.println("Start : " + LocalDate.from(ramadan));

        //until the end of the month
        System.out.println("End : " + LocalDate.from(ramadan.with(TemporalAdjusters.lastDayOfMonth())));*/
        LocalDate curdate = LocalDate.now();
        LocalDate nextOddDay = curdate.with((Temporal date) -> {
          return curdate.getDayOfMonth() % 2 == 0 ? curdate.plusDays(1) : curdate.plusDays(2);
        });
        System.out.println(nextOddDay);
      
   }
}