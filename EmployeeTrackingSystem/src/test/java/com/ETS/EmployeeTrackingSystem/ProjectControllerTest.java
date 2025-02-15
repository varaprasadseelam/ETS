package com.ETS.EmployeeTrackingSystem;

import com.ETS.EmployeeTrackingSystem.Entity.Project;
import com.ETS.EmployeeTrackingSystem.Service.ProjectService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProjectService projectService;

    @Test
    public void testCreateProject() throws Exception {
        Project project = new Project();
        project.setName("Project A");

        Mockito.when(projectService.saveProject(Mockito.any(Project.class))).thenReturn(project);

        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Project A\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Project A"));
    }

    @Test
    public void testGetAllProjects() throws Exception {
        List<Project> projects = Arrays.asList(new Project(), new Project());
        Mockito.when(projectService.getAllProjects()).thenReturn(projects);

        mockMvc.perform(get("/projects")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGetProjectById() throws Exception {
        Project project = new Project();
        project.setId(1L);
        project.setName("Project A");

        Mockito.when(projectService.getProjectById(1L)).thenReturn(project);

        mockMvc.perform(get("/projects/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Project A"));
    }

    @Test
    public void testDeleteProject() throws Exception {
        mockMvc.perform(delete("/projects/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(projectService, Mockito.times(1)).deleteProject(1L);
    }

    @Test
    public void testGetProjectsByDepartment() throws Exception {
        List<Project> projects = Arrays.asList(new Project(), new Project());
        Mockito.when(projectService.getProjectsByDepartment(1L)).thenReturn(projects);

        mockMvc.perform(get("/projects/department/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
