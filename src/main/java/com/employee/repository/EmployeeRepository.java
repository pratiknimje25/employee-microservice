package com.employee.repository;

import com.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

  boolean existsByEmail(String email);

  boolean existsById(Integer id);
}
