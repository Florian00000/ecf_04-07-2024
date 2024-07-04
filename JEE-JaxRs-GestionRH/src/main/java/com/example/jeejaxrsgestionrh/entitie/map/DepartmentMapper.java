package com.example.jeejaxrsgestionrh.entitie.map;

import com.example.jeejaxrsgestionrh.entitie.Department;

public class DepartmentMapper {
    public static Department map(Department department) {
        Department departmentDto = new Department();
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        return departmentDto;
    }
}
