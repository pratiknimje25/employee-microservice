package com.employee.controller;

import com.employee.dto.EmployeeDto;
import com.employee.model.EmployeeResponse;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

  @Autowired private EmployeeService service;

  @Autowired private EmployeeRepository repository;

  // Add methods to handle HTTP requests here

  @PostMapping("/create-employee")
  public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto employeeDto) {

    if (repository.existsByEmail(employeeDto.getEmail())) {
      return ResponseEntity.badRequest()
          .body(new EmployeeResponse("'" + employeeDto.getEmail() + "'" + " already exists."));
    }
    return new ResponseEntity<>(service.createEmployee(employeeDto), HttpStatus.CREATED);
  }

  @GetMapping("/fetch-all")
  public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
    return new ResponseEntity<>(service.getAllEmployees(), HttpStatus.OK);
  }

  @GetMapping("/fetchById/{id}")
  public ResponseEntity<?> getEmployeeById(@PathVariable Integer id) {
    EmployeeDto employeeDto = service.getEmployeeById(id);
    if (employeeDto != null) {
      return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    } else {
      return ResponseEntity.badRequest()
          .body(new EmployeeResponse("Employee with " + id + " not found."));
    }
  }

  @DeleteMapping("/deleteById/{id}")
  public ResponseEntity<?> deleteEmployeeById(@PathVariable Integer id) {
    if (service.deleteEmployeeById(id)) {
      return ResponseEntity.ok(
          new EmployeeResponse("Employee with id " + id + " deleted successfully."));
    } else {
      return ResponseEntity.badRequest()
          .body(new EmployeeResponse("Employee with id " + id + " not found."));
    }
  }

  @PutMapping("/updateById/{id}")
  public ResponseEntity<?> updateEmployeeById(
      @PathVariable Integer id, @RequestBody EmployeeDto employeeDto) {
    EmployeeDto updatedEmployee = service.updateEmployeeById(id, employeeDto);
    if (updatedEmployee != null) {
      return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    } else {
      return ResponseEntity.badRequest()
          .body(new EmployeeResponse("Employee with id " + id + " not found."));
    }
  }
}
