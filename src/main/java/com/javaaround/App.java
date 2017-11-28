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
import java.util.StringJoiner;

public class App {
   
   public static void main( String[] args ){
   		List<List<String>> list = Arrays.asList(Arrays.asList("a"),Arrays.asList("b"));

   	    Stream<String> stringStream = list.stream().flatMap(Collection::stream);
   	    stringStream.forEach(System.out::println);  
        
   }
}
