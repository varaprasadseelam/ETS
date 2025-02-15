package com.ETS.EmployeeTrackingSystem;

import com.ETS.EmployeeTrackingSystem.Entity.Department;
import com.ETS.EmployeeTrackingSystem.Repository.DepartmentRepository;
import com.ETS.EmployeeTrackingSystem.Service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DepartmentServiceTest {
    @Autowired
    private DepartmentService departmentService;

    @MockitoBean
    private DepartmentRepository departmentRepository;

    @Test
    public void testSaveDepartment() {
        Department department = new Department();
        department.setName("HR");
        Mockito.when(departmentRepository.save(department)).thenReturn(department);
        assertEquals(department, departmentService.saveDepartment(department));
    }

    @Test
    public void testGetAllDepartments() {
        List<Department> departments = Arrays.asList(new Department(), new Department());
        Mockito.when(departmentRepository.findAll()).thenReturn(departments);
        assertEquals(departments, departmentService.getAllDepartments());
    }

    @Test
    public void testGetDepartmentById() {
        Department department = new Department();
        department.setId(1L);
        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        assertEquals(department, departmentService.getDepartmentById(1L));
    }

    @Test
    public void testDeleteDepartment() {
        departmentService.deleteDepartment(1L);
        Mockito.verify(departmentRepository, Mockito.times(1)).deleteById(1L);
    }
}
