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

1. Local − Simplified date-time API with no time-zone(defualt system time zone).

2. Zoned − Specialized date-time API to deal with various timezones.

### LocalDate,LocalTime,LocalDateTime ###

LocalDate - represent date with a default format of yyyy-MM-dd.<br>
LocalTime - represents time with a default format of HH-mm-ss.zzz<br>
LocalDateTime - represents a date-time, with the default format as yyyy-MM-dd-HH-mm-ss.zzz

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




