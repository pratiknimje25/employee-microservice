package com.employee.service;

import java.util.List;
import com.employee.dto.EmployeeDto;

public interface EmployeeService {

  EmployeeDto createEmployee(EmployeeDto employeeDto);

  List<EmployeeDto> getAllEmployees();
}
