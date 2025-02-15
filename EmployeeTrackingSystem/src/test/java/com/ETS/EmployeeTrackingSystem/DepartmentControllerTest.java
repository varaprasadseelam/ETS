package com.ETS.EmployeeTrackingSystem;

import com.ETS.EmployeeTrackingSystem.Entity.Department;
import com.ETS.EmployeeTrackingSystem.Service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DepartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DepartmentService departmentService;

    @Test
    public void testCreateDepartment() throws Exception {
        Department department = new Department();
        department.setName("HR");

        Mockito.when(departmentService.saveDepartment(Mockito.any(Department.class))).thenReturn(department);

        mockMvc.perform(post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"HR\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("HR"));
    }

    @Test
    public void testGetAllDepartments() throws Exception {
        List<Department> departments = Arrays.asList(new Department(), new Department());
        Mockito.when(departmentService.getAllDepartments()).thenReturn(departments);

        mockMvc.perform(get("/departments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGetDepartmentById() throws Exception {
        Department department = new Department();
        department.setId(1L);
        department.setName("HR");

        Mockito.when(departmentService.getDepartmentById(1L)).thenReturn(department);

        mockMvc.perform(get("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("HR"));
    }

    @Test
    public void testDeleteDepartment() throws Exception {
        mockMvc.perform(delete("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(departmentService, Mockito.times(1)).deleteDepartment(1L);
    }
}
