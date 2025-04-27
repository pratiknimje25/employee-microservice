package com.employee.service.impl;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import com.employee.mapper.MapperEmployee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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

  @Cacheable(value = "employee", key = "#id")
  @Override
  public EmployeeDto getEmployeeById(Integer id) {

    Optional<Employee> employee = repository.findById(id);
    if (employee.isPresent()) {
      return MapperEmployee.MAPPER.mapEmployeeToEmployeeDTO(employee.get());
    }
    return null;
  }

  @Override
  public boolean deleteEmployeeById(Integer id) {
    Optional<Employee> employee = repository.findById(id);
    if (employee.isPresent()) {
      repository.delete(employee.get());
      return true;
    }
    return false;
  }

  @Override
  public EmployeeDto updateEmployeeById(Integer id, EmployeeDto employeeDto) {
    if (repository.existsById(id)) {
      Optional<Employee> employeeSave = repository.findById(id);
      Employee employeeRequest = MapperEmployee.MAPPER.mapEmployeeDTOTOEmployee(employeeDto);

      if (employeeRequest.getName() != null) {
        employeeSave.get().setName(employeeRequest.getName());
      }
      if (employeeRequest.getEmail() != null) {
        employeeSave.get().setEmail(employeeRequest.getEmail());
      }
      if (employeeRequest.getPhone() != null) {
        employeeSave.get().setPhone(employeeRequest.getPhone());
      }
      employeeSave.get().setId(id);
      Employee employee = employeeSave.get();
      return MapperEmployee.MAPPER.mapEmployeeToEmployeeDTO(repository.save(employee));
    }
    return null;
  }
}
