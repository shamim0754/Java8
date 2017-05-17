package com.javaaround;
import lombok.Data;
import java.util.List;

@Data
public class Person {
    private String name;
    private Integer age;
    
    /*public static void printPersonsOlderThan(List<Person> programmers, int age) {
	    for (Person p : programmers) {
	        if (p.getAge() >= age) 
	            System.out.println(p);
	    }    
    }
    */
    public static void printPersonsOlderThan(List<Person> programmers,CheckPerson tester){
    	for (Person p : programmers) {
        	if (tester.searchCriteriaMatch(p)) 
            	System.out.println(p);
    	}
    }	
    
}    