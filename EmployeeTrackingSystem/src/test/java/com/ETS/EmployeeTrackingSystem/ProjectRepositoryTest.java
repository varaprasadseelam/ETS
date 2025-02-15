package com.ETS.EmployeeTrackingSystem;

// ProjectRepositoryTest.java
import com.ETS.EmployeeTrackingSystem.Entity.Department;
import com.ETS.EmployeeTrackingSystem.Entity.Project;
import com.ETS.EmployeeTrackingSystem.Repository.DepartmentRepository;
import com.ETS.EmployeeTrackingSystem.Repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    @Test
    public void testSaveProject() {
        Project project = new Project();
        project.setName("Project A");
        project.setBudget(50000.0);

        Project savedProject = projectRepository.save(project);
        assertThat(savedProject).isNotNull();
        assertThat(savedProject.getId()).isNotNull();
    }

    @Test
    public void testFindByDepartmentId() {
        Department department = new Department();
        department.setName("HR");
        department = departmentRepository.save(department);

        Project project = new Project();
        project.setName("Project A");
        project.setDepartment(department);
        project.setBudget(50000.0);
        projectRepository.save(project);

        List<Project> projects = projectRepository.findByDepartmentId(department.getId());
        assertThat(projects).isNotEmpty();
    }
}
