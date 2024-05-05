package com.example.hibernate.controller;

import com.example.hibernate.dto.EmployeeDto;
import com.example.hibernate.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/Employee")
public class EmployeeController {

    private EmployeeService employeeService;

    //create an employee in db
    @PostMapping("/createEmployee")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //to find employee by ID
    @GetMapping("/FindEmployeeById/{Id}")
    public  ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("Id") Long employeeId){
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        System.out.println(employeeDto);
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);

    }

    //to get all employees
    @GetMapping("/GetAllEmployees")
    public ResponseEntity<List<EmployeeDto>> getEmployees(){
        List<EmployeeDto> EmployeeDtos = employeeService.getAllEmployee();
        return new ResponseEntity<>(EmployeeDtos,HttpStatus.OK);
    }

    //updated employee data
    @PutMapping("/UpdateEmployee/{Id}")
    public  ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("Id") Long employeeId,@RequestBody EmployeeDto updateEmployee){
        EmployeeDto employeeDto = employeeService.updateEmployee(employeeId,updateEmployee);
        return  new ResponseEntity<>(employeeDto,HttpStatus.OK);
    }

    //delete employee by Id
    @DeleteMapping(value = "/Delete/{employeeId}")
    public String deleteEmployee(@PathVariable("employeeId") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return "Employee deleted";
    }
}
