package com.javaaround;
import lombok.Data;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
public class Person implements Comparable<Person>{
    private String name;
    private Integer age;
    private String city;

    @Override
    public int compareTo(Person person){  
    return age.compareTo(person.age);
    }
    
}    