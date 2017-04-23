### Java 8 ###

Java 8 provided some great feature .it is released 8 March 2014<br/>

1. New Date Time api(java.time)
2. Lambda Expression

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

5. check two date before,after,equal

	```java
	  LocalDate date1 = LocalDate.of(2009, 12, 31);
      LocalDate date2 = LocalDate.of(2010, 01, 31);
      if (date1.isAfter(date2)) 
            System.out.println("Date1 is after Date2");

      if (date1.isBefore(date2)) 
         System.out.println("Date1 is before Date2");
     
      if (date1.isEqual(date2))  // or date1.equals(date2)
         System.out.println("Date1 is equal Date2");
     
      //Above example using compareTo method
      if (date1.compareTo(date2) > 0) 
         System.out.println("Date1 is after Date2");
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
	  LocalDate tomorrow = curdate.plusDays(1); 
	  LocalDate nextMonth = curdate.plusMonths(1); 
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




