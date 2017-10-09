
### Java 8 ###

Java 8 provided some great feature .it is released 8 March 2014<br/>

1. New Date Time api(java.time)
2. Lambda Expression
3. Streams
4. Optional
5. default methods
6. Intersection type
7. Nashorn Javascript
8. Base64 Encode and Decode
9. StringJoiner
10. Type Annotations
11. JDBC Improvements

### Warmup ###

1. create a directory

	`mkdir java8`

2. go to java8 directoy and apply `gradle init` command to create java project

	`gradle init --type java-application`

	Note : gradle instatallation  https://docs.gradle.org/current/userguide/userguide.html

3.  Create App.java at src/main/java/com/javaaround	

	```java
	package com.javaaround;

	public class App {
	   public static void main( String[] args ){
	      System.out.println( "Hello World!" );
	   }
	}
	```
4. 	Create AppTest.java at src/main/test/com/javaaround	

	```java
	package com.javaaround;
	import org.junit.Test;

	public class AppTest {
	    @Test 
	    public void testMain() {
	        App.main(null);
	    }	
	   
	}
	```
5. Gradle default test output at $project_home/builds/reports/tests/index.html. We need also standard out at console . To do that add below line at build.gradle

	```java
	test {

		//we want display the following test events
	    testLogging {
	    	showStandardStreams = true
	        events "PASSED", "STARTED", "FAILED", "SKIPPED"
	    }
	}
	```	
   
   if you need to run app from package file($builds\libs\java8.jar) by "java -jar" then 	add below line at build.gradle

	```java
	// Define the main class for the application
	mainClassName = 'com.javaaround.App'

	jar {
	   manifest {
	      attributes 'Main-Class': 'com.javaaround.App'
	   }
	}

	```
6. Run app by following command

	`gradle clean bulid`

![Image of Nested](images/1.png) 		

### New Date Time api ###

Old date-time API(java.util.Date,java.util.Calendar)  have the following drawbacks 

1. They aren’t thread-safe as a result leading to potential concurrency issues for users.developer would expect to deal with when writing date-handling code
2.  Poor API design e.g years in java.util.Date start at 1900, months start at 1, and days start at 0.lack of direct methods for date operations.java.util.Date represents number of milli-seconds since the january 1 ,1970.It has no relationship with any particular date, hour, etc . but if you print then " Thu Apr 13 23:47:29 BDT 2017" . causing confusion among developers.
3.  Developers had to write a lot of code to deal with timezone issues

For above problems,  third-party date and time libraries(Joda-Time) are become popular

For above problems,the author of Joda-Time (Stephen Colebourne) and Oracle,provide new date time api(java.time) under JSR-310


Every class of java.time are Immutable(final) value class so that they are thread safe.
e.g 
```java
public final class LocalDate extends Object   
implements Temporal, TemporalAdjuster, ChronoLocalDate, Serializable 
```


### LocalDate,LocalTime,LocalDateTime ###

LocalDate - represent date with a default format of yyyy-MM-dd.<br>
LocalTime - represents time with a default format of HH-mm-ss.zzz(nano second)<br>
LocalDateTime - represents a date-time, with the default format as yyyy-MM-dd-HH-mm-ss.zzz<br>
As their names indicate, they represent the local Date/Time from the  system clock in the default time zone

1. Getting current time

	Update App.java

	```java
	LocalDate curdate = LocalDate.now();
	LocalTime curtime = LocalTime.now();
	LocalDateTime curdatetime = LocalDateTime.now();
	System.out.println("current date " + curdate);
	System.out.println("current time " + curtime);
	System.out.println("current datetime " + curdatetime);
	```

2. Formatting Date with other format

	```java
	//formatter
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    System.out.println(curdate.format(formatter));

	```
3. Representing specific time Using of method

	```java
	LocalDate birthday = LocalDate.of(1990, Month.DECEMBER, 15);
    LocalTime birthtime = LocalTime.of(13, 30, 15);
    LocalDateTime birthdaytime = LocalDateTime.of(1990, Month.DECEMBER, 15,13, 30, 15);
  
    System.out.println("current date " + birthday);
    System.out.println("current time " + birthtime);
    System.out.println("current datetime " + birthdaytime);
	```	
4. Representing specific time Using parse method

	```java
	  // give string default format yyyy-MM-dd
      LocalDate birthday = LocalDate.parse("1990-12-15"); 
      // give string other format
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
      LocalDate birthday2 = LocalDate.parse("15-12-1990",formatter); 
      LocalTime birthtime = LocalTime.parse("13:30:15");
      LocalDateTime birthdaytime = LocalDateTime.parse("1990-12-15T13:30:15");
      
      System.out.println("current date " + birthday);
      System.out.println("current date " + birthday2);
      System.out.println("current time " + birthtime);
      System.out.println("current datetime " + birthdaytime);
	```	

5. check two date isBefore,isAfter,isEqual

	```java
	  LocalDate date1 = LocalDate.of(2009, 12, 31);
      LocalDate date2 = LocalDate.of(2010, 01, 31);
      if (date1.isAfter(date2)) 
            System.out.println("Date2 is after Date1");

      if (date1.isBefore(date2)) 
         System.out.println("Date1 is before Date2");
     
      if (date1.isEqual(date2))  // or date1.equals(date2)
         System.out.println("Date1 is equal Date2");
     
      //Above example using compareTo method
      if (date1.compareTo(date2) > 0) 
         System.out.println("Date2 is after Date1");
      else if (date1.compareTo(date2) < 0) 
         System.out.println("Date1 is before Date2");
      else if (date1.compareTo(date2) == 0) 
         System.out.println("Date1 is equal to Date2");
	```	

	Note : isEqual(), equals() and compareTo() internally apply the same following logic:

	```java
	int compareTo0(LocalDate otherDate) {
	   int cmp = (year - otherDate.year);
	   if (cmp == 0) {
	      cmp = (month - otherDate.month);
	    if (cmp == 0) {
	       cmp = (day - otherDate.day);
	    }
	   }
	   return cmp;
	 }
	```
6. Adding and subtracting minute/hour/day/month 	

	```java
	  //plus day/month from a date 
	  LocalDate tomorrow = curdate.plusDays(1); //or curdate.plus(1, ChronoUnit.DAYS); 
	  LocalDate nextMonth = curdate.plusMonths(1); //or curdate.plus(1, ChronoUnit.MONTHS); 
	  System.out.println("tomorrow " + tomorrow);
	  System.out.println("nextMonth " + nextMonth);
	  

	  //minus day/month from a date
	  LocalDate yesterday = curdate.minusDays(1);
	  LocalDate beforeMonth = curdate.minusMonths(1);
	  System.out.println("yesterday " + yesterday);
	  System.out.println("beforeMonth " + beforeMonth);
	```
7. Extracting specific fields

	```java
	 LocalDate curdate = LocalDate.now();
     //Extract year 
     System.out.println("year = " + curdate.getYear());

     //Extract month 
     System.out.println("month = " + curdate.getMonth());

     //Extract month value 
     System.out.println("month value = " + curdate.getMonthValue());

     //Extract day value 
     System.out.println("day = " + curdate.getDayOfMonth());
	```	
### TemporalAdjusters  ### 
TemporalAdjusters are another nice way for date manipulation.It provides a set of predefined adjusters for finding Following

1. the first/last day of the month
2. the first/last day of the year
3. the last Wednesday of the month
4. the first Tuesday after a specific date
5. etc.

```java
LocalDate curdate = LocalDate.now();
LocalDate firstDate = curdate.with(TemporalAdjusters.firstDayOfMonth());
System.out.println(firstDate);
```

Note : Static imports make this more fluent to read(recommended)

```java
import static java.time.temporal.TemporalAdjusters.*;
LocalDate firstDate = curdate.with(firstDayOfMonth());

``` 
| Adjuster        | Description          | 
| ------------- |:-------------:|
| firstDayOfMonth(), lastDayOfMonth()      | get first/last day of the month from any date |
| firstDayOfYear(), lastDayOfYear()      | get first/last day of the year from any date |
| firstDayOfNextMonth()      | get first day of next month from any date |
| firstDayOfNextYear()      | get first day of next year from any date |
| next(DayOfWeek.SUNDAY),previous(DayOfWeek.SUNDAY)      | get next/previous sunday from any date |
| nextOrSame(), previousOrSame()      | Next/current DayOfWeek |
| firstInMonth(DayOfWeek.MONDAY)      | get first monday from any date |

[Full List](http://docs.oracle.com/javase/8/docs/api/index.html?java/time/temporal/TemporalAdjusters.html)

### Custom Adjusters ###
1. Create NextOddDay.java
```java
package com.javaaround;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextOddDay implements TemporalAdjuster {
	public Temporal adjustInto(Temporal temporalInput) {
		LocalDate localDate = LocalDate.from(temporalInput);
		int day = localDate.getDayOfMonth();
		if (day % 2 == 0) 
			localDate = localDate.plusDays(1);
		 else 
			localDate = localDate.plusDays(2);

		return temporalInput.with(localDate);
	}
}	
```

Usage <br>
```java
LocalDate curdate = LocalDate.now();
LocalDate nextOddDay = curdate.with(new NextOddDay());
System.out.println(nextOddDay);
```
### Above Using Lampda expression ###

Since TemporalAdjuster is functional interface(when have only one method),So we can use Lampdd expression

```java
LocalDate curdate = LocalDate.now();
LocalDate nextOddDay = curdate.with((Temporal date) -> {
  return curdate.getDayOfMonth() % 2 == 0 ? curdate.plusDays(1) : curdate.plusDays(2);
});
System.out.println(nextOddDay);
```  

### ZoneId,ZoneOffset,ZonedDateTime ###

ZoneId - is an identifier for a region(time zones)<br>
ZoneOffset - is the period of time representing a difference between Greenwich/UTC(Universal time coordinated)<br/>
ZonedDateTime - combines the LocalDateTime class with the ZoneId class. It is used to represent a full date (year, month, day) and time (hour, minute, second, nanosecond) with a time zone (region/city, such as Europe/Paris).<br/>
OffsetDateTime - handles a date and time with a corresponding time zone offset from Greenwich/UTC, without a time zone ID.<br/>
OffsetTime - handles time with a corresponding time zone offset from Greenwich/UTC, without a time zone ID.


1. get Available Zone

	```java
	List<String> zoneList = new ArrayList<>(ZoneId.getAvailableZoneIds());
	for(String zone : zoneList)
		System.out.println(zone);
	```
2. get system Default Zone

	```java
	ZoneId currentZone = ZoneId.systemDefault();
    System.out.println("CurrentZone: " + currentZone);
	```
3. specific zone datetime

	```java
	LocalDateTime tokyodate = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));  
    System.out.println("Tokyo date and time now : " + tokyodate);

    LocalDateTime curdatetime = LocalDateTime.now();
    //ZonedDateTime tokyodate1 = curdatetime.atZone(ZoneId.of("Asia/Tokyo")); 
    ZonedDateTime tokyodate1 = ZonedDateTime.of(curdatetime,ZoneId.of("Asia/Tokyo"));  
    System.out.println("Tokyo date and time now : " + tokyodate1);
	```
4. get offset

	```java
	ZoneId currentZone = ZoneId.of("Asia/Dacca");  
    System.out.println("Tokyo date and time : " + currentZone.getRules());
	```
4. create offset

	```java
	ZoneId currentZone = ZoneId.of("Asia/Dacca");  
    System.out.println("Tokyo date and time : " + currentZone.getRules());
	```
5. get OffsetDateTime

	```java
	LocalDateTime localDate = LocalDateTime.of(2013, Month.JULY, 20, 19, 30);
	ZoneOffset offset = ZoneOffset.of("-08:00");

	OffsetDateTime offsetDate = OffsetDateTime.of(localDate, offset);
	```	


### Lambda Expression ###
Provides the implementation of Functional interface(have only one method) nicer way .Previously it is done by annomous class

```java
package com.javaaround;
public class App {
   public static void main( String[] args ){
      int num1 = 2;
      Number num2 = new Number(){
        @Override
        public int get(int num3){
          return num3;
        }
      
      };
      System.out.println(num1 + num2.get(3));
   }
}
interface Number{
  int get(int num);
}
```

Traditional Above OOP style need

1. Create object
2. do operation on object

that leads to less readable and less maintainable code

Can we bipass above way ? 

Can we assign function as value (num2 is 3) similar way where num1 is  assign to 2 ? 

```java
package com.javaaround;
public class App {
   public static void main( String[] args ){
      int num1 = 2;
      Number num2 = public int get(int num3){
          return num3;
      };
      System.out.println(num1 + num2);
   }
}

```

It is a common feature for some programming languages, such as Lisp, Python, Scala.That is functional programming language. but you can't do it at java before java8

you do it using Java 8 Lambda expression

```java
import java.util.function.Supplier;

int num1 = 2;
Number num2 = (num3)-> num3;
System.out.println(num1 + num2.get(3));

```

### Advantage functional Programming(Lambda expression)

1. Enable more readable and maintainable code
2. Enable parallel processing

### Lambda Expression Syntax ###

```java
Number num2 = public int get(int num3){
    return num3;
};
```
 Remove access type , Optional type declaration(The compiler can inference the type from the value of the parameter),function name and insert '->' symbol.Then it is called lambda expression.

```java
Number num2 = (num3)->{
    return num3;
};
```

Short notation

1.  No need to use parenthesis  if contain single parameter.but for multiple parameters, parentheses are required.
	```java
	Number num2 = num3->{
	    return num3;
	};
2. No need to use curly braces & return if contain single expression.but for multiple expression, curly braces & return are required.
	```java
	Number num2 = num3-> num3;
	```

### Functional Interface ###
1. Built In Old
	1. Runnable#run()	
	2. Comparator#compare()	
2. Built In New	
	1. java.util.function : defines several standard functional interfaces that can we used without creating manually functional interface
	[List](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html)

	Above functional interface
	```java
	@FunctionalInterface //optional,compile error if have more than one method 
	interface Number{
	  int get(int num);
	}

	```	

	No need to use it because similar interfaces  `Supplier<T>` define java.util.function

	```java
	 Supplier<Integer> num2 = ()->{
      return 3;
      };
    System.out.println(num1 + num2.get());
	```

### Collection Sort ###
1. Add lombok library to generate setter,getter,constructure at runtime

Update build.gradle
```java
dependencies {
   
    // lombok
    compile 'org.projectlombok:lombok:1.16.12'
}
```
2. Create Person.java

```java
package com.javaaround;
import lombok.Data;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
public class Person {
    private String name;
    private Integer age;
    private String city;
    
}    
```
3. Update App.java sort without lambda
```java
List<Person> listPersons = new ArrayList<Person>();
  listPersons.add(new Person("Md.Shamim Miah",24,"Tangail"));  
  listPersons.add(new Person("Shohel Rana",25,"Rajshahi"));  
  listPersons.add(new Person("Ilias Gazi",30,"Natore"));  
  //sort by age
  listPersons.sort(new Comparator<Person>() {
    @Override
    public int compare(Person o1, Person o2) {
      return o1.getAge() - o2.getAge();
    }
  }
  
  );
  for(Person person:listPersons)
    System.out.println(person);
```

4. Update App.java sort using lambda
```java
List<Person> listPersons = new ArrayList<Person>();
  listPersons.add(new Person("Md.Shamim Miah",24,"Tangail"));  
  listPersons.add(new Person("Shohel Rana",25,"Rajshahi"));  
  listPersons.add(new Person("Ilias Gazi",30,"Natore"));  
  //sort by age
  listPersons.sort((Person o1, Person o2) -> o1.getAge() - o2.getAge());
  for(Person person:listPersons)
    System.out.println(person);
```

### Variable Scope ###
1. lambda expression can access local variables that creates effectively final variable so you can't reassign

```java
String x = "Lambda"; 
Function<String,String> func1 = y -> {
// x="expression"; // compile error
return y + " "+ x ;
};
// x="expression"; // compile not error


System.out.println(func1.apply("javaaround.com"));
```

Note: for annomous class and before java 8.0  need final String x="lambda"; java 8 it is assumed effectively final

Here lambda expression keep tracks of value x when used this lambda later although it modified further.This concept is called closures

2. Accessing fields and static variables : we can access to instance fields and static variables from within lambda expressions.

```java
public class App {
   
   String x; 
   static int outerStaticNum;
   public static void main( String[] args ){
      
      Function<String,String> func1 = y -> {
       outerStaticNum=3;// compile error
      return y + " ";
      };
      outerStaticNum=3;
      System.out.println(func1.apply("javaaround.com"));

   }
      
}
```

3. this referece does not refer lambda expression type.it refer object that use lambda
```java
public class App {
   public static void main( String[] args ){
      new App();

   }
  public App(){
    Number num1 = x -> {
        System.out.println(this);
        return x;
    };
    System.out.println(num1.get(3));  
  }
}
```
### Method reference ###

Method reference creates a lambda expression using using an existing method that refer to functional interface implementation

1. Reference to an Static Method

	above example using method reference .

	```java
	public class App {
	   public static int get(int num3){  
	       return num3;
	   } 
	   public static void main( String[] args ){
	      Number num2 = App::get;
	      System.out.println(num2.get(3));
	   }
	}
	interface Number{
	  int get(int num);
	}
	```

	Builtin functional interface reference(Runnable)

	```java
	public class App {
	    public static void ThreadStatus(){  
	        System.out.println("Thread is running...");  
	    }  
	    public static void main( String[] args ){
	      Thread t=new Thread(App::ThreadStatus);  
	      t.start(); 
	    }
	}
	```
2. Reference to an Instance Method

	```java
		public int get(int num3){  
	       return num3;
	   } 
	   public static void main( String[] args ){
	      App app = new App();
	      Number num2 = app::get;
	      System.out.println(num2.get(3));

	   }
	}
	```
3. Reference to a Constructor
	```java
   package com.javaaround;
   public static void main( String[] args ){
      
      PersonFactory<Person> personFactory = Person::new;
      Person person = personFactory.create("Peter", 12,"Parker");
      System.out.println(person);

	   }
	  
	}
	interface PersonFactory<P extends Person> {
	      P create(String firstName, int age,String lastName);
	}
	```
4. Reference to an Instance Method of an Arbitrary Object of a Particular Type
	```java
	String[] stringArray = { 
        "Barbara", "James", "Mary", "John",
        "Patricia", "Robert", "Michael", "Linda" 
      };
      Arrays.sort(stringArray, String::compareToIgnoreCase);
      for(String person:stringArray)
        System.out.println(person);
	```	
### Exception Handling at lambda ###

	```java
	public class App {
	   public static void main( String[] args ){
	        System.out.println( "Hello World!" );
	    	int [] numbers = {1,2,3,5};
	    	int key = 0;
	    	process(numbers,key,(v,k) -> System.out.println(v/k));

	   }
	   public static void process(int[] numbers,int key,BiConsumer<Integer,Integer> consumer){
	      for(int i : numbers){
	        consumer.accept(i,key);
	      }
	   }
	}
	```

java.lang.ArithmeticException happens

Solution 1:



	```java

	public static void process(int[] numbers,int key,BiConsumer<Integer,Integer> consumer){
      for(int i : numbers){
        try{
          consumer.accept(i,key);
        }catch(ArithmeticException ae){
          System.out.println("Exception : " + ae.getMessage());
        }
        
      }
    }

	```


By above solution if Nullpointer/others  exception happens need to add catch block . then long list catch block exits. Besides we don't know which exception happens because biconsumer can take any operation



	Solution 2 :

	```java
	process(numbers,key,(v,k) -> {
        try{
          System.out.println(v/k);
        }catch(ArithmeticException ae){
          System.out.println("Exception : " + ae.getMessage());
        }
      });

	```

By above solution 2 still similar problem

Solution 3 :


	```java

	process(numbers,key,wrapperLambd((v,k) ->System.out.println(v/k)));
	
	public static BiConsumer<Integer,Integer> wrapperLambd(BiConsumer<Integer,Integer> consumer){
      return (v,k)->{
         try{
          consumer.accept(v,k);
         }catch(ArithmeticException ae){
           System.out.println("Exception : " + ae.getMessage());
         }
      };
    }

	```	


### java.util.function ###

1. Predicate : Predicates are boolean-valued functions of one argument.
```java
interface Predicate<t>{
boolean test(T t)
}
```

```java
Predicate<Integer> pr = a -> (a > 18); // Creating predicate  
System.out.println(pr.test(10));    // Calling Predicate method
Predicate<Boolean> nonNull = Objects::nonNull;
Predicate<Boolean> isNull = Objects::isNull;

Predicate<String> isEmpty = String::isEmpty;
Predicate<String> isNotEmpty = isEmpty.negate();
```

2. Function : Represents a function that accepts one arguments and produces a result.similar Consumer<T> but it has no return type
```java
Interface Function<T,R>{
R apply(T t)
}
//T = input type
//R = function return type
```

```java
// Using  a  lambda  expression
Function<Integer, String> func1  = x -> Integer.toBinaryString(x);
System.out.println(func1.apply(10));

// Using  a  method  reference
Function<Integer, String> func2  = Integer::toBinaryString;
System.out.println(func2.apply(10));  
```

3. Function : Represents a function that accepts two arguments and produces a result.imilar BiConsumer<T,T> but it has no return type.
```java
Interface BiFunction<T,R>{
R apply(T t,U u)
}
//T ,U= input type
//R = function return type
```

```java
// Uses a lambda expression
BiFunction<Integer, Integer, Integer> func1 = (x, y) -> Integer.sum(x, y);
System.out.println(func1.apply(2, 3));

// Uses a method reference
BiFunction<Integer, Integer, Integer> func2 = Integer::sum;
System.out.println(func2.apply(2, 3)); 
```

### Stream : ### 

consider the following SQL statement −

SELECT max(salary), employee_id, employee_name FROM Employee
The above SQL expression automatically returns the maximum salaried employee's details, without doing any computation on the developer's end. Using collections framework in Java, a developer has to use loops and make repeated checks. Another concern is efficiency; as multi-core processors are available at ease, a Java developer has to write parallel code processing that can be pretty error-prone.

To resolve such issues, Java 8 introduced the concept of stream

Stream represents a sequence of objects from a source, which supports aggregate operations like filter, map, limit, reduce, find, match

Stream operations are divided into two category
1.  Intermediate operation : return stream itself so that their result can be pipelined.e.g filter,map
2.  Terminal operation : to mark the end of the stream. e.g collect(),reduce() , as well as multiple specialized reduction forms such as sum(), max(), or count().

 A stream pipeline consists of a source (such as a Collection, an array, a generator function, or an I/O channel); followed by zero or more intermediate operations and a terminal operation

### How to create Stream ###
Streams can be obtained in a number of ways. Some examples include:
1. From a Collection.stream() and Collection.parallelStream() methods;
2. From an array via Arrays.stream(Object[]) or Stream.of(Object[];
3. From static factory methods on the stream classes, such as Stream.of(Object[]), IntStream.range(int, int) or Stream.iterate(Object, UnaryOperator);
4. The lines of a file can be obtained from BufferedReader.lines();
5. Streams of file paths can be obtained from methods in Files;
6. Streams of random numbers can be obtained from Random.ints();
7. Numerous other stream-bearing methods in the JDK, including BitSet.stream(), Pattern.splitAsStream(java.lang.CharSequence), and JarFile.stream().

### Default method ###

### Collection forEach ###
```java
List<Person> listPersons = new ArrayList<Person>();
listPersons.add(new Person("Md.Shamim Miah",24,"Tangail"));  
listPersons.add(new Person("Shohel Rana",25,"Rajshahi"));  
listPersons.add(new Person("Ilias Gazi",30,"Natore"));  

listPersons.forEach(person->System.out.println(person));

//using method reference
listPersons.forEach(System.out::println); //if one parameter and operation of that parameter

//loop with condition

listPersons.forEach(person->{
  if("Md.Shamim Miah".equals(person.getName()))
     System.out.println(person);
});
```
### Stream Example ###
1. Stream with iterate : creating an infinite stream.	

	```java
	 Stream.iterate(1, element->element+1)  
        .limit(5)  
        .forEach(System.out::println);  
	```
2. Stream with Foreach : To iterate each element of the stream.parameter java.util.function.Consumer<Person>
	```java
	List<Person> listPersons = new ArrayList<Person>();
    listPersons.add(new Person("Md.Shamim Miah",24,"Tangail"));  
    listPersons.add(new Person("Shohel Rana",25,"Rajshahi"));  
    listPersons.add(new Person("Ilias Gazi",30,"Natore"));  

    Stream<Person> sListPerson =   listPersons.stream();
    //using method reference
    sListPerson.forEach(System.out::println); 
    
    //above example at one line
    listPersons.stream().forEach(System.out::println); 
	```
2. Stream with Filter : To eliminate elements based on a criteria(using lambda).parameter java.util.function.Predicate<Person>

	```java
	 listPersons.stream()
    .filter(person -> person.getAge() >= 25)  
    .forEach(System.out::println); 
	```
3. Stream with Map : Converts each element of stream to its corresponding result 
	```java
	listPersons.stream()
    .map(person -> person.getAge())
    .collect(Collectors.toList())
    .forEach(System.out::println);
	```
	
3. Stream with limit : To reduce the size of the stream
	```java
	 listPersons.stream()
    .map(person -> person.getAge())
    .limit(1)
    .collect(Collectors.toList())
    .forEach(System.out::println);
	```

	Note : Collectors enum return a list or a string
	//delimiter ,
	Collectors.joining(", ")
	//delimiter , with prefix [
	Collectors.joining(", ", "[")
	//delimiter , with prefix [ and suffix ]
	Collectors.joining(", ", "[", "]")

3. Stream with sort : To sort the stream according to natural order. If the elements of this stream are not Comparable, java.lang.ClassCastException  thrown

	```java
	 listPersons.stream()
    .map(person -> person.getAge())
    .sorted()
    .collect(Collectors.toList())
    .forEach(System.out::println); 
	```	
    Uncomment below line then throw exception.expect sorted(Comparator) paramter since it person object.

	```java
	 listPersons.stream()
    //.map(person -> person.getAge())
    .sorted()
    //.collect(Collectors.toList())
    .forEach(System.out::println); 
	```
	Update Person.java

	```java
	public class Person implements Comparable<Person>{
	    private String name;
	    private Integer age;
	    private String city;

	    @Override
	    public int compareTo(Person person){  
	    return age.compareTo(person.age);
	    }
	    
	}  
	```

	Run again no exception occur


	you can use Comparator interface to sort by any field

	```java
	listPersons.stream()
    .sorted((Person o1, Person o2) -> o1.getAge() - o2.getAge())
    .forEach(System.out::println); 
	```	
4. Stream with match :  check whether a certain predicate matches the .it is terminal operation and return a boolean result.

	```java
	 boolean isTangailPerson = listPersons.stream()
    //any match return true
    .anyMatch(person -> person.getCity() == "Tangail");

    //all match return true
    //.allMatch(person -> person.getCity() == "Tangail");
    //no match return true
    //.noneMatch(person -> person.getCity() == "Tangail");
    System.out.println(isTangailPerson);
	```
4. Stream with count,min,max :  terminal operation(count return long) and none terminal operation(min,max  object result)
	```java
	//count
    long size = listPersons.stream()
    .count();
    System.out.println(size);

    //max
    Person person = listPersons.stream()  
           .max((p1, p2)->p1.getAge() > p2.getAge() ? 1: -1)
           .get();
    System.out.println(person);  

    //min
    Person person = listPersons.stream()  
           .min((p1, p2)->p1.getAge() > p2.getAge() ? 1: -1)
           .get();
    System.out.println(person); 
	```
=======
### Java 8 ###

Java 8 provided some great feature .it is released 8 March 2014<br/>

1. New Date Time api(java.time)
2. Lambda Expression
3. Streams
4. Optional
5. default methods
6. Intersection type
7. Nashorn Javascript
8. Base64 Encode and Decode
9. StringJoiner
10. Type Annotations
11. JDBC Improvements

### Warmup ###

1. create a directory

	`mkdir java8`

2. go to java8 directoy and apply `gradle init` command to create java project

	`gradle init --type java-application`

	Note : gradle instatallation  https://docs.gradle.org/current/userguide/userguide.html

3.  Create App.java at src/main/java/com/javaaround	

	```java
	package com.javaaround;

	public class App {
	   public static void main( String[] args ){
	      System.out.println( "Hello World!" );
	   }
	}
	```
4. 	Create AppTest.java at src/main/test/com/javaaround	

	```java
	package com.javaaround;
	import org.junit.Test;

	public class AppTest {
	    @Test 
	    public void testMain() {
	        App.main(null);
	    }	
	   
	}
	```
5. Gradle default test output at $project_home/builds/reports/tests/index.html. We need also standard out at console . To do that add below line at build.gradle

	```java
	test {

		//we want display the following test events
	    testLogging {
	    	showStandardStreams = true
	        events "PASSED", "STARTED", "FAILED", "SKIPPED"
	    }
	}
	```	
   
   if you need to run app from package file($builds\libs\java8.jar) by "java -jar" then 	add below line at build.gradle

	```java
	// Define the main class for the application
	mainClassName = 'com.javaaround.App'

	jar {
	   manifest {
	      attributes 'Main-Class': 'com.javaaround.App'
	   }
	}

	```
6. Run app by following command

	`gradle clean bulid`

![Image of Nested](images/1.png) 		

### New Date Time api ###

Old date-time API(java.util.Date,java.util.Calendar)  have the following drawbacks 

1. They aren’t thread-safe as a result leading to potential concurrency issues for users.developer would expect to deal with when writing date-handling code
2.  Poor API design e.g years in java.util.Date start at 1900, months start at 1, and days start at 0.lack of direct methods for date operations.java.util.Date represents number of milli-seconds since the january 1 ,1970.It has no relationship with any particular date, hour, etc . but if you print then " Thu Apr 13 23:47:29 BDT 2017" . causing confusion among developers.
3.  Developers had to write a lot of code to deal with timezone issues

For above problems,  third-party date and time libraries(Joda-Time) are become popular

For above problems,the author of Joda-Time (Stephen Colebourne) and Oracle,provide new date time api(java.time) under JSR-310


Every class of java.time are Immutable(final) value class so that they are thread safe.
e.g 
```java
public final class LocalDate extends Object   
implements Temporal, TemporalAdjuster, ChronoLocalDate, Serializable 
```


### LocalDate,LocalTime,LocalDateTime ###

LocalDate - represent date with a default format of yyyy-MM-dd.<br>
LocalTime - represents time with a default format of HH-mm-ss.zzz(nano second)<br>
LocalDateTime - represents a date-time, with the default format as yyyy-MM-dd-HH-mm-ss.zzz<br>
As their names indicate, they represent the local Date/Time from the  system clock in the default time zone

1. Getting current time

	Update App.java

	```java
	LocalDate curdate = LocalDate.now();
	LocalTime curtime = LocalTime.now();
	LocalDateTime curdatetime = LocalDateTime.now();
	System.out.println("current date " + curdate);
	System.out.println("current time " + curtime);
	System.out.println("current datetime " + curdatetime);
	```

2. Formatting Date with other format

	```java
	//formatter
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    System.out.println(curdate.format(formatter));

	```
3. Representing specific time Using of method

	```java
	LocalDate birthday = LocalDate.of(1990, Month.DECEMBER, 15);
    LocalTime birthtime = LocalTime.of(13, 30, 15);
    LocalDateTime birthdaytime = LocalDateTime.of(1990, Month.DECEMBER, 15,13, 30, 15);
  
    System.out.println("current date " + birthday);
    System.out.println("current time " + birthtime);
    System.out.println("current datetime " + birthdaytime);
	```	
4. Representing specific time Using parse method

	```java
	  // give string default format yyyy-MM-dd
      LocalDate birthday = LocalDate.parse("1990-12-15"); 
      // give string other format
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
      LocalDate birthday2 = LocalDate.parse("15-12-1990",formatter); 
      LocalTime birthtime = LocalTime.parse("13:30:15");
      LocalDateTime birthdaytime = LocalDateTime.parse("1990-12-15T13:30:15");
      
      System.out.println("current date " + birthday);
      System.out.println("current date " + birthday2);
      System.out.println("current time " + birthtime);
      System.out.println("current datetime " + birthdaytime);
	```	

5. check two date isBefore,isAfter,isEqual

	```java
	  LocalDate date1 = LocalDate.of(2009, 12, 31);
      LocalDate date2 = LocalDate.of(2010, 01, 31);
      if (date1.isAfter(date2)) 
            System.out.println("Date2 is after Date1");

      if (date1.isBefore(date2)) 
         System.out.println("Date1 is before Date2");
     
      if (date1.isEqual(date2))  // or date1.equals(date2)
         System.out.println("Date1 is equal Date2");
     
      //Above example using compareTo method
      if (date1.compareTo(date2) > 0) 
         System.out.println("Date2 is after Date1");
      else if (date1.compareTo(date2) < 0) 
         System.out.println("Date1 is before Date2");
      else if (date1.compareTo(date2) == 0) 
         System.out.println("Date1 is equal to Date2");
	```	

	Note : isEqual(), equals() and compareTo() internally apply the same following logic:

	```java
	int compareTo0(LocalDate otherDate) {
	   int cmp = (year - otherDate.year);
	   if (cmp == 0) {
	      cmp = (month - otherDate.month);
	    if (cmp == 0) {
	       cmp = (day - otherDate.day);
	    }
	   }
	   return cmp;
	 }
	```
6. Adding and subtracting minute/hour/day/month 	

	```java
	  //plus day/month from a date 
	  LocalDate tomorrow = curdate.plusDays(1); //or curdate.plus(1, ChronoUnit.DAYS); 
	  LocalDate nextMonth = curdate.plusMonths(1); //or curdate.plus(1, ChronoUnit.MONTHS); 
	  System.out.println("tomorrow " + tomorrow);
	  System.out.println("nextMonth " + nextMonth);
	  

	  //minus day/month from a date
	  LocalDate yesterday = curdate.minusDays(1);
	  LocalDate beforeMonth = curdate.minusMonths(1);
	  System.out.println("yesterday " + yesterday);
	  System.out.println("beforeMonth " + beforeMonth);
	```
7. Extracting specific fields

	```java
	 LocalDate curdate = LocalDate.now();
     //Extract year 
     System.out.println("year = " + curdate.getYear());

     //Extract month 
     System.out.println("month = " + curdate.getMonth());

     //Extract month value 
     System.out.println("month value = " + curdate.getMonthValue());

     //Extract day value 
     System.out.println("day = " + curdate.getDayOfMonth());
	```	
### TemporalAdjusters  ### 
TemporalAdjusters are another nice way for date manipulation.It provides a set of predefined adjusters for finding Following

1. the first/last day of the month
2. the first/last day of the year
3. the last Wednesday of the month
4. the first Tuesday after a specific date
5. etc.

```java
LocalDate curdate = LocalDate.now();
LocalDate firstDate = curdate.with(TemporalAdjusters.firstDayOfMonth());
System.out.println(firstDate);
```

Note : Static imports make this more fluent to read(recommended)

```java
import static java.time.temporal.TemporalAdjusters.*;
LocalDate firstDate = curdate.with(firstDayOfMonth());

``` 
| Adjuster        | Description          | 
| ------------- |:-------------:|
| firstDayOfMonth(), lastDayOfMonth()      | get first/last day of the month from any date |
| firstDayOfYear(), lastDayOfYear()      | get first/last day of the year from any date |
| firstDayOfNextMonth()      | get first day of next month from any date |
| firstDayOfNextYear()      | get first day of next year from any date |
| next(DayOfWeek.SUNDAY),previous(DayOfWeek.SUNDAY)      | get next/previous sunday from any date |
| nextOrSame(), previousOrSame()      | Next/current DayOfWeek |
| firstInMonth(DayOfWeek.MONDAY)      | get first monday from any date |

[Full List](http://docs.oracle.com/javase/8/docs/api/index.html?java/time/temporal/TemporalAdjusters.html)

### Custom Adjusters ###
1. Create NextOddDay.java
```java
package com.javaaround;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextOddDay implements TemporalAdjuster {
	public Temporal adjustInto(Temporal temporalInput) {
		LocalDate localDate = LocalDate.from(temporalInput);
		int day = localDate.getDayOfMonth();
		if (day % 2 == 0) 
			localDate = localDate.plusDays(1);
		 else 
			localDate = localDate.plusDays(2);

		return temporalInput.with(localDate);
	}
}	
```

Usage <br>
```java
LocalDate curdate = LocalDate.now();
LocalDate nextOddDay = curdate.with(new NextOddDay());
System.out.println(nextOddDay);
```
### Above Using Lampda expression ###

Since TemporalAdjuster is functional interface(when have only one method),So we can use Lampdd expression

```java
LocalDate curdate = LocalDate.now();
LocalDate nextOddDay = curdate.with((Temporal date) -> {
  return curdate.getDayOfMonth() % 2 == 0 ? curdate.plusDays(1) : curdate.plusDays(2);
});
System.out.println(nextOddDay);
```  

### ZoneId,ZoneOffset,ZonedDateTime ###

ZoneId - is an identifier for a region(time zones)<br>
ZoneOffset - is the period of time representing a difference between Greenwich/UTC(Universal time coordinated)<br/>
ZonedDateTime - combines the LocalDateTime class with the ZoneId class. It is used to represent a full date (year, month, day) and time (hour, minute, second, nanosecond) with a time zone (region/city, such as Europe/Paris).<br/>
OffsetDateTime - handles a date and time with a corresponding time zone offset from Greenwich/UTC, without a time zone ID.<br/>
OffsetTime - handles time with a corresponding time zone offset from Greenwich/UTC, without a time zone ID.


1. get Available Zone

	```java
	List<String> zoneList = new ArrayList<>(ZoneId.getAvailableZoneIds());
	for(String zone : zoneList)
		System.out.println(zone);
	```
2. get system Default Zone

	```java
	ZoneId currentZone = ZoneId.systemDefault();
    System.out.println("CurrentZone: " + currentZone);
	```
3. specific zone datetime

	```java
	LocalDateTime tokyodate = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));  
    System.out.println("Tokyo date and time now : " + tokyodate);

    LocalDateTime curdatetime = LocalDateTime.now();
    //ZonedDateTime tokyodate1 = curdatetime.atZone(ZoneId.of("Asia/Tokyo")); 
    ZonedDateTime tokyodate1 = ZonedDateTime.of(curdatetime,ZoneId.of("Asia/Tokyo"));  
    System.out.println("Tokyo date and time now : " + tokyodate1);
	```
4. get offset

	```java
	ZoneId currentZone = ZoneId.of("Asia/Dacca");  
    System.out.println("Tokyo date and time : " + currentZone.getRules());
	```
4. create offset

	```java
	ZoneId currentZone = ZoneId.of("Asia/Dacca");  
    System.out.println("Tokyo date and time : " + currentZone.getRules());
	```
5. get OffsetDateTime

	```java
	LocalDateTime localDate = LocalDateTime.of(2013, Month.JULY, 20, 19, 30);
	ZoneOffset offset = ZoneOffset.of("-08:00");

	OffsetDateTime offsetDate = OffsetDateTime.of(localDate, offset);
	```	


### Lambda Expression ###
Provides the implementation of Functional interface(have only one method) nicer way .Previously it is done by annomous class

```java
package com.javaaround;
public class App {
   public static void main( String[] args ){
      int num1 = 2;
      Number num2 = new Number(){
        @Override
        public int get(int num3){
          return num3;
        }
      
      };
      System.out.println(num1 + num2.get(3));
   }
}
interface Number{
  int get(int num);
}
```

Traditional Above OOP style need

1. Create object
2. do operation on object

that leads to less readable and less maintainable code

Can we bipass above way ? 

Can we assign function as value (num2 is 3) similar way where num1 is  assign to 2 ? 

```java
package com.javaaround;
public class App {
   public static void main( String[] args ){
      int num1 = 2;
      Number num2 = public int get(int num3){
          return num3;
      };
      System.out.println(num1 + num2);
   }
}

```

It is a common feature for some programming languages, such as Lisp, Python, Scala.That is functional programming language. but you can't do it at java before java8

you do it using Java 8 Lambda expression

```java
import java.util.function.Supplier;

int num1 = 2;
Number num2 = (num3)-> num3;
System.out.println(num1 + num2.get(3));

```

### Advantage functional Programming(Lambda expression)

1. Enable more readable and maintainable code
2. Enable parallel processing

### Lambda Expression Syntax ###

```java
Number num2 = public int get(int num3){
    return num3;
};
```
 Remove access type , Optional type declaration(The compiler can inference the type from the value of the parameter),function name and insert '->' symbol.Then it is called lambda expression.

```java
Number num2 = (num3)->{
    return num3;
};
```

Short notation

1.  No need to use parenthesis  if contain single parameter.but for multiple parameters, parentheses are required.
	```java
	Number num2 = num3->{
	    return num3;
	};
2. No need to use curly braces & return if contain single expression.but for multiple expression, curly braces & return are required.
	```java
	Number num2 = num3-> num3;
	```

### Functional Interface ###
1. Built In Old
	1. Runnable#run()	
	2. Comparator#compare()	
2. Built In New	
	1. java.util.function : defines several standard functional interfaces that can we used without creating manually functional interface
	[List](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html)

	Above functional interface
	```java
	@FunctionalInterface //optional,compile error if have more than one method 
	interface Number{
	  int get(int num);
	}

	```	

	No need to use it because similar interfaces  `Supplier<T>` define java.util.function

	```java
	 Supplier<Integer> num2 = ()->{
      return 3;
      };
    System.out.println(num1 + num2.get());
	```

### Collection Sort ###
1. Add lombok library to generate setter,getter,constructure at runtime

Update build.gradle
```java
dependencies {
   
    // lombok
    compile 'org.projectlombok:lombok:1.16.12'
}
```
2. Create Person.java

```java
package com.javaaround;
import lombok.Data;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
public class Person {
    private String name;
    private Integer age;
    private String city;
    
}    
```
3. Update App.java sort without lambda
```java
List<Person> listPersons = new ArrayList<Person>();
  listPersons.add(new Person("Md.Shamim Miah",24,"Tangail"));  
  listPersons.add(new Person("Shohel Rana",25,"Rajshahi"));  
  listPersons.add(new Person("Ilias Gazi",30,"Natore"));  
  //sort by age
  listPersons.sort(new Comparator<Person>() {
    @Override
    public int compare(Person o1, Person o2) {
      return o1.getAge() - o2.getAge();
    }
  }
  
  );
  for(Person person:listPersons)
    System.out.println(person);
```

4. Update App.java sort using lambda
```java
List<Person> listPersons = new ArrayList<Person>();
  listPersons.add(new Person("Md.Shamim Miah",24,"Tangail"));  
  listPersons.add(new Person("Shohel Rana",25,"Rajshahi"));  
  listPersons.add(new Person("Ilias Gazi",30,"Natore"));  
  //sort by age
  listPersons.sort((Person o1, Person o2) -> o1.getAge() - o2.getAge());
  for(Person person:listPersons)
    System.out.println(person);
```
### Collection forEach ###
```java
List<Person> listPersons = new ArrayList<Person>();
listPersons.add(new Person("Md.Shamim Miah",24,"Tangail"));  
listPersons.add(new Person("Shohel Rana",25,"Rajshahi"));  
listPersons.add(new Person("Ilias Gazi",30,"Natore"));  

listPersons.forEach(person->System.out.println(person));

//using method reference
listPersons.forEach(System.out::println); //if one parameter and operation of that parameter

//loop with condition

listPersons.forEach(person->{
  if("Md.Shamim Miah".equals(person.getName()))
     System.out.println(person);
});
```
### Variable Scope ###
1. lambda expression can access local variables that creates effectively final variable so you can't reassign

```java
String x = "Lambda"; 
Function<String,String> func1 = y -> {
// x="expression"; // compile error
return y + " "+ x ;
};
// x="expression"; // compile not error


System.out.println(func1.apply("javaaround.com"));
```

Note: for annomous class and before java 8.0  need final String x="lambda"; java 8 it is assumed effectively final

Here lambda expression keep tracks of value x when used this lambda later although it modified further.This concept is called closures

2. Accessing fields and static variables : we can access to instance fields and static variables from within lambda expressions.

```java
public class App {
   
   String x; 
   static int outerStaticNum;
   public static void main( String[] args ){
      
      Function<String,String> func1 = y -> {
       outerStaticNum=3;// compile error
      return y + " ";
      };
      outerStaticNum=3;
      System.out.println(func1.apply("javaaround.com"));

   }
      
}
```

3. this referece does not refer lambda expression type.it refer object that use lambda
```java
public class App {
   public static void main( String[] args ){
      new App();

   }
  public App(){
    Number num1 = x -> {
        System.out.println(this);
        return x;
    };
    System.out.println(num1.get(3));  
  }
}
```
### Method reference ###

Method reference creates a lambda expression using using an existing method that refer to functional interface implementation

1. Reference to an Static Method

	above example using method reference .

	```java
	public class App {
	   public static int get(int num3){  
	       return num3;
	   } 
	   public static void main( String[] args ){
	      Number num2 = App::get;
	      System.out.println(num2.get(3));
	   }
	}
	interface Number{
	  int get(int num);
	}
	```

	Builtin functional interface reference(Runnable)

	```java
	public class App {
	    public static void ThreadStatus(){  
	        System.out.println("Thread is running...");  
	    }  
	    public static void main( String[] args ){
	      Thread t=new Thread(App::ThreadStatus);  
	      t.start(); 
	    }
	}
	```
2. Reference to an Instance Method

	```java
		public int get(int num3){  
	       return num3;
	   } 
	   public static void main( String[] args ){
	      App app = new App();
	      Number num2 = app::get;
	      System.out.println(num2.get(3));

	   }
	}
	```
3. Reference to a Constructor
	```java
   package com.javaaround;
   public static void main( String[] args ){
      
      PersonFactory<Person> personFactory = Person::new;
      Person person = personFactory.create("Peter", 12,"Parker");
      System.out.println(person);

	   }
	  
	}
	interface PersonFactory<P extends Person> {
	      P create(String firstName, int age,String lastName);
	}
	```
4. Reference to an Instance Method of an Arbitrary Object of a Particular Type
	```java
	String[] stringArray = { 
        "Barbara", "James", "Mary", "John",
        "Patricia", "Robert", "Michael", "Linda" 
      };
      Arrays.sort(stringArray, String::compareToIgnoreCase);
      for(String person:stringArray)
        System.out.println(person);
	```	
### Exception Handling at lambda ###

	```java
	public class App {
	   public static void main( String[] args ){
	        System.out.println( "Hello World!" );
	    	int [] numbers = {1,2,3,5};
	    	int key = 0;
	    	process(numbers,key,(v,k) -> System.out.println(v/k));

	   }
	   public static void process(int[] numbers,int key,BiConsumer<Integer,Integer> consumer){
	      for(int i : numbers){
	        consumer.accept(i,key);
	      }
	   }
	}
	```

java.lang.ArithmeticException happens

Solution 1:



	```java

	public static void process(int[] numbers,int key,BiConsumer<Integer,Integer> consumer){
      for(int i : numbers){
        try{
          consumer.accept(i,key);
        }catch(ArithmeticException ae){
          System.out.println("Exception : " + ae.getMessage());
        }
        
      }
    }

	```


By above solution if Nullpointer/others  exception happens need to add catch block . then long list catch block exits. Besides we don't know which exception happens because biconsumer can take any operation



	Solution 2 :

	```java
	process(numbers,key,(v,k) -> {
        try{
          System.out.println(v/k);
        }catch(ArithmeticException ae){
          System.out.println("Exception : " + ae.getMessage());
        }
      });

	```

By above solution 2 still similar problem

Solution 3 :


	```java

	process(numbers,key,wrapperLambd((v,k) ->System.out.println(v/k)));
	
	public static BiConsumer<Integer,Integer> wrapperLambd(BiConsumer<Integer,Integer> consumer){
      return (v,k)->{
         try{
          consumer.accept(v,k);
         }catch(ArithmeticException ae){
           System.out.println("Exception : " + ae.getMessage());
         }
      };
    }

	```	


### java.util.function ###

1. Predicate : Predicates are boolean-valued functions of one argument.
```java
interface Predicate<t>{
boolean test(T t)
}
```

```java
Predicate<Integer> pr = a -> (a > 18); // Creating predicate  
System.out.println(pr.test(10));    // Calling Predicate method
Predicate<Boolean> nonNull = Objects::nonNull;
Predicate<Boolean> isNull = Objects::isNull;

Predicate<String> isEmpty = String::isEmpty;
Predicate<String> isNotEmpty = isEmpty.negate();
```

2. Function : Represents a function that accepts one arguments and produces a result.similar Consumer<T> but it has no return type
```java
Interface Function<T,R>{
R apply(T t)
}
//T = input type
//R = function return type
```

```java
// Using  a  lambda  expression
Function<Integer, String> func1  = x -> Integer.toBinaryString(x);
System.out.println(func1.apply(10));

// Using  a  method  reference
Function<Integer, String> func2  = Integer::toBinaryString;
System.out.println(func2.apply(10));  
```

3. Function : Represents a function that accepts two arguments and produces a result.imilar BiConsumer<T,T> but it has no return type.
```java
Interface BiFunction<T,R>{
R apply(T t,U u)
}
//T ,U= input type
//R = function return type
```

```java
// Uses a lambda expression
BiFunction<Integer, Integer, Integer> func1 = (x, y) -> Integer.sum(x, y);
System.out.println(func1.apply(2, 3));

// Uses a method reference
BiFunction<Integer, Integer, Integer> func2 = Integer::sum;
System.out.println(func2.apply(2, 3)); 
```

### Stream : ### 

consider the following SQL statement −

SELECT max(salary), employee_id, employee_name FROM Employee
The above SQL expression automatically returns the maximum salaried employee's details, without doing any computation on the developer's end. Using collections framework in Java, a developer has to use loops and make repeated checks. Another concern is efficiency; as multi-core processors are available at ease, a Java developer has to write parallel code processing that can be pretty error-prone.

To resolve such issues, Java 8 introduced the concept of stream

Stream represents a sequence of objects from a source, which supports aggregate operations like filter, map, limit, reduce, find, match

Stream operations are divided into two category
1.  Intermediate operation : return stream itself so that their result can be pipelined.e.g filter,map
2.  Terminal operation : to mark the end of the stream. e.g collect(),reduce() , as well as multiple specialized reduction forms such as sum(), max(), or count().

 A stream pipeline consists of a source (such as a Collection, an array, a generator function, or an I/O channel); followed by zero or more intermediate operations and a terminal operation

### How to create Stream ###
Streams can be obtained in a number of ways. Some examples include:
1. From a Collection.stream() and Collection.parallelStream() methods;
2. From an array via Arrays.stream(Object[]) or Stream.of(Object[];
3. From static factory methods on the stream classes, such as Stream.of(Object[]), IntStream.range(int, int) or Stream.iterate(Object, UnaryOperator);
4. The lines of a file can be obtained from BufferedReader.lines();
5. Streams of file paths can be obtained from methods in Files;
6. Streams of random numbers can be obtained from Random.ints();
7. Numerous other stream-bearing methods in the JDK, including BitSet.stream(), Pattern.splitAsStream(java.lang.CharSequence), and JarFile.stream().

### Stream Example ###
1. Stream with iterate : creating an infinite stream.	

	```java
	 Stream.iterate(1, element->element+1)  
        .limit(5)  
        .forEach(System.out::println);  
	```
2. Stream with Foreach : To iterate each element of the stream.parameter java.util.function.Consumer<Person>
	```java
	List<Person> listPersons = new ArrayList<Person>();
    listPersons.add(new Person("Md.Shamim Miah",24,"Tangail"));  
    listPersons.add(new Person("Shohel Rana",25,"Rajshahi"));  
    listPersons.add(new Person("Ilias Gazi",30,"Natore"));  

    Stream<Person> sListPerson =   listPersons.stream();
    //using method reference
    sListPerson.forEach(System.out::println); 
    
    //above example at one line
    listPersons.stream().forEach(System.out::println); 
	```
2. Stream with Filter : To eliminate elements based on a criteria(using lambda).parameter java.util.function.Predicate<Person>

	```java
	 listPersons.stream()
    .filter(person -> person.getAge() >= 25)  
    .forEach(System.out::println); 
	```
3. Stream with Map : Converts each element of stream to its corresponding result 
	```java
	listPersons.stream()
    .map(person -> person.getAge())
    .collect(Collectors.toList())
    .forEach(System.out::println);
	```
	
3. Stream with limit : To reduce the size of the stream
	```java
	 listPersons.stream()
    .map(person -> person.getAge())
    .limit(1)
    .collect(Collectors.toList())
    .forEach(System.out::println);
	```

	Note : Collectors enum return a list or a string
	//delimiter ,
	Collectors.joining(", ")
	//delimiter , with prefix [
	Collectors.joining(", ", "[")
	//delimiter , with prefix [ and suffix ]
	Collectors.joining(", ", "[", "]")

3. Stream with sort : To sort the stream according to natural order. If the elements of this stream are not Comparable, java.lang.ClassCastException  thrown

	```java
	 listPersons.stream()
    .map(person -> person.getAge())
    .sorted()
    .collect(Collectors.toList())
    .forEach(System.out::println); 
	```	
    Uncomment below line then throw exception.expect sorted(Comparator) paramter since it person object.

	```java
	 listPersons.stream()
    //.map(person -> person.getAge())
    .sorted()
    //.collect(Collectors.toList())
    .forEach(System.out::println); 
	```
	Update Person.java

	```java
	public class Person implements Comparable<Person>{
	    private String name;
	    private Integer age;
	    private String city;

	    @Override
	    public int compareTo(Person person){  
	    return age.compareTo(person.age);
	    }
	    
	}  
	```

	Run again no exception occur


	you can use Comparator interface to sort by any field

	```java
	listPersons.stream()
    .sorted((Person o1, Person o2) -> o1.getAge() - o2.getAge())
    .forEach(System.out::println); 
	```	
4. Stream with match :  check whether a certain predicate matches the .it is terminal operation and return a boolean result.

	```java
	 boolean isTangailPerson = listPersons.stream()
    //any match return true
    .anyMatch(person -> person.getCity() == "Tangail");

    //all match return true
    //.allMatch(person -> person.getCity() == "Tangail");
    //no match return true
    //.noneMatch(person -> person.getCity() == "Tangail");
    System.out.println(isTangailPerson);
	```
4. Stream with count,min,max :  terminal operation(count return long) and none terminal operation(min,max  object result)
	```java
	//count
    long size = listPersons.stream()
    .count();
    System.out.println(size);

    //max
    Person person = listPersons.stream()  
           .max((p1, p2)->p1.getAge() > p2.getAge() ? 1: -1)
           .get();
    System.out.println(person);  

    //min
    Person person = listPersons.stream()  
           .min((p1, p2)->p1.getAge() > p2.getAge() ? 1: -1)
           .get();
    System.out.println(person); 
	```
	