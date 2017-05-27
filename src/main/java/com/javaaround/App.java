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
import java.util.function.Function;
public class App {
   public int App(int num3){  
       return num3;
   } 
   String x; 
   static int outerStaticNum;
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
        /*Person p1 = new Person();
        p1.setName("Md.Shamim Miah");
        p1.setAge(28);
        Person p2 = new Person();
        p2.setName("Md.Alamin Miah");
        p2.setAge(36);
        List<Person> programmers = new ArrayList<>();
        programmers.add(p1);
        programmers.add(p2);
        Person.printPersonsOlderThan(programmers,new CheckPerson() {
          @Override
          public boolean searchCriteriaMatch(Person p) {
              return p.getAge() >= 30;
          }
      });
      Supplier<Integer> num2 = ()->{
      return 3;
      };
      System.out.println(num1 + num2.get());
      */
     
      /*List<Person> listPersons = new ArrayList<Person>();
      listPersons.add(new Person("Md.Shamim Miah",24,"Tangail"));  
      listPersons.add(new Person("Shohel Rana",25,"Rajshahi"));  
      listPersons.add(new Person("Ilias Gazi",30,"Natore"));  
      //sort by age
      listPersons.sort((Person o1, Person o2) -> o1.getAge() - o2.getAge());
      for(Person person:listPersons)
        System.out.println(person);*/
      // Using  a  lambda  expression
      // Uses a lambda expression
      
      PersonFactory<Person> personFactory = Person::new;
      Person person = personFactory.create("Peter", 12,"Parker");
      System.out.println(person);

   }
    
  
}
interface PersonFactory<P extends Person> {
      P create(String firstName, int age,String lastName);
}