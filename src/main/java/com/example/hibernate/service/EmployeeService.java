package com.example.hibernate.service;

import com.example.hibernate.dto.EmployeeDto;
import com.example.hibernate.entity.Employee;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee (EmployeeDto employeeDto);
    EmployeeDto getEmployeeById (Long employeeId);
    List<EmployeeDto> getAllEmployee();
    EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee);
    void deleteEmployee(Long employeeId);
}
