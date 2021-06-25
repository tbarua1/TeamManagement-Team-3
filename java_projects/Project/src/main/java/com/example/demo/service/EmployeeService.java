package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.model.*;

public interface EmployeeService {
 List<Employee> getAllEmployees();
 void saveEmployee(Employee employee);
 Employee getEmployeeById(long id);


 void deleteEmployeeById(long id);








}