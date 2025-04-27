package com.employee.service;

import java.util.List;
import java.util.Optional;

import com.employee.dto.EmployeeDto;

public interface EmployeeService {

  EmployeeDto createEmployee(EmployeeDto employeeDto);

  List<EmployeeDto> getAllEmployees();

  EmployeeDto getEmployeeById(Integer id);

  boolean deleteEmployeeById(Integer id);

  EmployeeDto updateEmployeeById(Integer id, EmployeeDto employeeDto);
}
