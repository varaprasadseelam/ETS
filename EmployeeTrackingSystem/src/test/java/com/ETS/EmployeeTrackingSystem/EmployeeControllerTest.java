package com.ETS.EmployeeTrackingSystem;

import com.ETS.EmployeeTrackingSystem.Entity.Employee;
import com.ETS.EmployeeTrackingSystem.Service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// EmployeeControllerTest.java
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Test
    public void testCreateEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setName("John Doe");

        Mockito.when(employeeService.saveEmployee(Mockito.any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                     .content("{\"name\":\"John Doe\"}"))
                .andExpect((ResultMatcher) jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        Mockito.when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");

        Mockito.when(employeeService.getEmployeeById(1L)).thenReturn(employee);

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isOk());

        Mockito.verify(employeeService, Mockito.times(1)).deleteEmployee(1L);
    }

    @Test
    public void testGetEmployeesByDepartment() throws Exception {
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        Mockito.when(employeeService.getEmployeesByDepartment(1L)).thenReturn(employees);

        mockMvc.perform(get("/employees/department/1"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGetEmployeesByProject() throws Exception {
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        Mockito.when(employeeService.getEmployeesByProject(1L)).thenReturn(employees);

        mockMvc.perform(get("/employees/project/1"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(2)));
    }
}
