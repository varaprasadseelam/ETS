package com.ETS.EmployeeTrackingSystem.Controller;

import com.ETS.EmployeeTrackingSystem.Entity.Project;
import com.ETS.EmployeeTrackingSystem.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectService.saveProject(project);
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }

    @GetMapping("/department/{departmentId}")
    public List<Project> getProjectsByDepartment(@PathVariable Long departmentId) {
        return projectService.getProjectsByDepartment(departmentId);
    }
}
