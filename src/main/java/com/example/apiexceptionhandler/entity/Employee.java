package com.example.apiexceptionhandler.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String firstName;
    private String lastName;
    private double salary;
    private String email;
    private String cubicleNo;

    public Employee() {
    }

    public Employee(String firstName, String lastName, double salary, String email, String cubicleNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.email = email;
        this.cubicleNo = cubicleNo;
    }
}
