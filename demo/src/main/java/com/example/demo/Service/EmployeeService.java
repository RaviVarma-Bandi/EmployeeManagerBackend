package com.example.demo.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Interface.EmployeeInterface;
import com.example.demo.Model.Employee;
import com.example.demo.exception.UserNotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
@Service
@Transactional
public class EmployeeService {
    private final EmployeeInterface employeeInterface;

    @Autowired
    public EmployeeService(EmployeeInterface employeeRepo) {

        this.employeeInterface = employeeRepo;
    }

    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeInterface.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeInterface.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        return employeeInterface.save(employee);
    }

    public Employee findEmployeeById(Long Id) {
        return employeeInterface.findEmployeeById(Id)
        		.orElseThrow(() -> new UserNotFoundException("User by id " + Id + " was not found"));
    }

    public void deleteEmployee(Long Id){
        employeeInterface.deleteEmployeeById(Id);
    }
}
