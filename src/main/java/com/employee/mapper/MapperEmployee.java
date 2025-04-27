package com.employee.mapper;

import com.employee.dto.EmployeeDto;
import com.employee.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapperEmployee {

  MapperEmployee MAPPER = Mappers.getMapper(MapperEmployee.class);

  Employee mapEmployeeDTOTOEmployee(EmployeeDto dto);

  EmployeeDto mapEmployeeToEmployeeDTO(Employee employee);
}
