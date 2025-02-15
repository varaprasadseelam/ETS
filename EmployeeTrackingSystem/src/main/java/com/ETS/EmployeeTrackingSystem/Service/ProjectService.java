package com.ETS.EmployeeTrackingSystem.Service;

import com.ETS.EmployeeTrackingSystem.Entity.Project;
import com.ETS.EmployeeTrackingSystem.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }


    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public List<Project> getProjectsByDepartment(Long departmentId) {
        return projectRepository.findByDepartmentId(departmentId);
    }
}
