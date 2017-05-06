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
       LocalDateTime localDate = LocalDateTime.of(2013, Month.JULY, 20, 19, 30);
       ZoneOffset offset = ZoneOffset.of("-08:00");

        OffsetDateTime offsetDate = OffsetDateTime.of(localDate, offset);
        OffsetDateTime lastThursday =
        offsetDate.with(TemporalAdjusters.lastInMonth(DayOfWeek.THURSDAY));
        System.out.println(offsetDate);  
        LocalDate curdate = LocalDate.now();
        LocalDate firstDate = curdate.with(firstDayOfMonth());
        System.out.println(firstDate);
      
   }
}