package com.employee.controller;

import com.employee.dto.EmployeeDto;
import com.employee.model.EmployeeResponse;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
