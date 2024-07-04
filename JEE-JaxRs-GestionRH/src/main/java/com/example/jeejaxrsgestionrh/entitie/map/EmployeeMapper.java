package com.example.jeejaxrsgestionrh.entitie.map;

import com.example.jeejaxrsgestionrh.entitie.Employee;

public class EmployeeMapper {
    public static Employee map(Employee employee) {
        Employee employeeDTO = new Employee();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        return employeeDTO;
    }
}
