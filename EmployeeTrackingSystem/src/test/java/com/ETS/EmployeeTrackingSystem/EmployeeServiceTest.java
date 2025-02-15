package com.ETS.EmployeeTrackingSystem;

import com.ETS.EmployeeTrackingSystem.Entity.Employee;
import com.ETS.EmployeeTrackingSystem.Repository.EmployeeRepository;
import com.ETS.EmployeeTrackingSystem.Service.EmployeeService;
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
public class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;

    @MockitoBean
    private EmployeeRepository employeeRepository;

    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setName("John Doe");
        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
        assertEquals(employee, employeeService.saveEmployee(employee));
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        Mockito.when(employeeRepository.findAll()).thenReturn(employees);
        assertEquals(employees, employeeService.getAllEmployees());
    }

    @Test
    public void testGetEmployeeById() {
        Employee employee = new Employee();
        employee.setId(1L);
        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        assertEquals(employee, employeeService.getEmployeeById(1L));
    }

    @Test
    public void testDeleteEmployee() {
        employeeService.deleteEmployee(1L);
        Mockito.verify(employeeRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testGetEmployeesByDepartment() {
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        Mockito.when(employeeRepository.findByDepartmentId(1L)).thenReturn(employees);
        assertEquals(employees, employeeService.getEmployeesByDepartment(1L));
    }

    @Test
    public void testGetEmployeesByProject() {
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        Mockito.when(employeeRepository.findByProjectsId(1L)).thenReturn(employees);
        assertEquals(employees, employeeService.getEmployeesByProject(1L));
    }
}
