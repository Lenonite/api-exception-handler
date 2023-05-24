package com.example.apiexceptionhandler.controller;

import com.example.apiexceptionhandler.dao.EmployeeDao;
import com.example.apiexceptionhandler.entity.Employee;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeDao employeeDao;
    @GetMapping("/creation")
    public  String creation(){
        Arrays.asList(
                new Employee("John","Doe",3000,"johnDoe@gmail.com","E12"),
                new Employee("Tun","Thein",4000,"tunthein@gmail.com","E13")
        ).forEach(employeeDao::save);
        return "success";
    }
    record RequestEmployee(@JsonProperty("first_name")
                           @NotEmpty(message = "firstName cannot be empty!")
                           @Pattern(regexp = "[a-zA-Z ]*",message = "firstName cannot contain invalid characters!") String firstName,
                           @JsonProperty("last_name")
                           @NotEmpty(message = "lastName cannot be empty!")
                           @Pattern(regexp = "[a-zA-Z ]*",message = "lastName cannot contain invalid characters!")String lastName,
                           @Email(message = "Invalid email format!") String email,
                           @JsonProperty("cubicle_no") String cubicleNo,
                           double salary){}
    record ResponseEmployee(int id,
                            @JsonProperty("first_name") String firstName,
                            @JsonProperty("last_name") String lastName){}
    @PostMapping("/employees")
    public ResponseEmployee create(@RequestBody @Valid RequestEmployee requestEmployee){
        Employee employee= employeeDao.save(
                new Employee(
                requestEmployee.firstName(),
                requestEmployee.lastName(),
                requestEmployee.salary(),
                requestEmployee.email(),
                requestEmployee.cubicleNo()
                ));
        return new ResponseEmployee(employee.getId(),
                                    employee.getFirstName(),
                                    employee.getLastName());
    }
    @GetMapping("/employee/all")
    public List<Employee> findAllEmployee(){
        return employeeDao.findAll();
    }
}
