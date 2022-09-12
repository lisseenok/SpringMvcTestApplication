package ru.lisenok.springmvc.models;


import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NotEmpty(message = "name should not be empty")
    @Size(min = 2, max = 30, message = "name should be between 2 and 30 letters")
    @Column(name = "name")
    private String name;

    @Min(value = 0, message = "age should be greater than 0")
    @Column(name = "age")
    private int age;

//    @NotEmpty(message = "email should not be empty")
//    @Email(message = "email should be valid")
//    @Column(name = "email")
//    private String email;
//
//    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "your address should be in this format: Country, City, 123456")
//    @Column(name = "address")
//    private String address;

    public Person() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
