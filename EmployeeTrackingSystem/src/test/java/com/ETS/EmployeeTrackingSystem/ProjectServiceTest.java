package com.ETS.EmployeeTrackingSystem;

import com.ETS.EmployeeTrackingSystem.Entity.Project;
import com.ETS.EmployeeTrackingSystem.Repository.ProjectRepository;
import com.ETS.EmployeeTrackingSystem.Service.ProjectService;
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
public class ProjectServiceTest {
    @Autowired
    private ProjectService projectService;

    @MockitoBean
    private ProjectRepository projectRepository;

    @Test
    public void testSaveProject() {
        Project project = new Project();
        project.setName("Project A");
        Mockito.when(projectRepository.save(project)).thenReturn(project);
        assertEquals(project, projectService.saveProject(project));
    }

    @Test
    public void testGetAllProjects() {
        List<Project> projects = Arrays.asList(new Project(), new Project());
        Mockito.when(projectRepository.findAll()).thenReturn(projects);
        assertEquals(projects, projectService.getAllProjects());
    }

    @Test
    public void testGetProjectById() {
        Project project = new Project();
        project.setId(1L);
        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        assertEquals(project, projectService.getProjectById(1L));
    }

    @Test
    public void testDeleteProject() {
        projectService.deleteProject(1L);
        Mockito.verify(projectRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testGetProjectsByDepartment() {
        List<Project> projects = Arrays.asList(new Project(), new Project());
        Mockito.when(projectRepository.findByDepartmentId(1L)).thenReturn(projects);
        assertEquals(projects, projectService.getProjectsByDepartment(1L));
    }
}