package com.javaaround;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.time.chrono.HijrahDate;
import java.time.temporal.Temporal;  
import java.util.*;
import java.util.function.Supplier;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.BiFunction;
import static java.time.temporal.TemporalAdjusters.*;
import java.util.function.BiConsumer;
import java.util.stream.Stream;
import java.util.stream.Collectors;
public class App {
   
   public static void main( String[] args ){
      System.out.println( "Hello World!" );
      List<Person> listPersons = new ArrayList<Person>();
	  listPersons.add(new Person("Md.Shamim Miah",24,"Tangail"));  
	  listPersons.add(new Person("Shohel Rana",24,"Rajshahi"));  
	  listPersons.add(new Person("Ilias Gazi",30,"Natore"));  

	  Map<Integer, List<Person>> personsByAge = listPersons
      .stream()
      .collect(Collectors.groupingBy(p -> p.getAge()));

	  personsByAge
     .forEach((age, p) -> System.out.format("age %s: %s\n", age, p)); 
        
   }
}
