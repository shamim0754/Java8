package com.javaaround;
import lombok.Data;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
public class Person implements Comparable<Person>{
    public String name;
    public Integer age;
    public String city;

    @Override
    public int compareTo(Person person){  
    return age.compareTo(person.age);
    }
    
}    