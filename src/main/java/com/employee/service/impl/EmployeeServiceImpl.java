package com.employee.service.impl;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.mapper.MapperEmployee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired private EmployeeRepository repository;

  @Override
  public EmployeeDto createEmployee(EmployeeDto employeeDto) {

    Employee employee1 = MapperEmployee.MAPPER.mapEmployeeDTOTOEmployee(employeeDto);
    // Convert EmployeeDto to Employee entity
    Employee employee = repository.save(employee1);

    return MapperEmployee.MAPPER.mapEmployeeToEmployeeDTO(employee);
  }

  @Override
  public List<EmployeeDto> getAllEmployees() {
    return repository.findAll().stream()
        .map(MapperEmployee.MAPPER::mapEmployeeToEmployeeDTO)
        .toList();
  }
}
