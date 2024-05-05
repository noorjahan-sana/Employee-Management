package com.example.hibernate.service.serviceImpl;

import com.example.hibernate.dto.EmployeeDto;
import com.example.hibernate.entity.Employee;
import com.example.hibernate.exception.ResourceNotFoundException;
import com.example.hibernate.mapper.EmployeeMapper;
import com.example.hibernate.respository.EmployeeRepository;
import com.example.hibernate.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
       Employee employee = employeeRepository.findById(employeeId).
               orElseThrow(()-> new ResourceNotFoundException("EMPLOYEE ID NOT FOUND "+ employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> EmployeeList = employeeRepository.findAll();
        List<EmployeeDto> EmployeeDtos = new ArrayList<>();
        for(Employee employee: EmployeeList){
            EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
            EmployeeDtos.add(employeeDto);
        }

        return EmployeeDtos;
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {
       Employee employee = employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("ID Not Found"));
       employee.setFirstname(updateEmployee.getFirstName());
       employee.setLastName(updateEmployee.getLastName());
       employee.setEmail(updateEmployee.getEmail());
       Employee updatedEmp = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmp);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("ID Not Found"));
        employeeRepository.deleteById(employeeId);
    }
}
