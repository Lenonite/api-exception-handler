package com.example.apiexceptionhandler.dao;

import com.example.apiexceptionhandler.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee,Integer> {

}
