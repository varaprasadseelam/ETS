package com.ETS.EmployeeTrackingSystem;

// EmployeeRepositoryTest.java
import com.ETS.EmployeeTrackingSystem.Entity.Department;
import com.ETS.EmployeeTrackingSystem.Entity.Employee;
import com.ETS.EmployeeTrackingSystem.Entity.Project;
import com.ETS.EmployeeTrackingSystem.Repository.DepartmentRepository;
import com.ETS.EmployeeTrackingSystem.Repository.EmployeeRepository;
import com.ETS.EmployeeTrackingSystem.Repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setDepartment(new Department());
        employee.setProjects(new HashSet<>());

        Employee savedEmployee = employeeRepository.save(employee);
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isNotNull();
    }

    @Test
    public void testFindByDepartmentId() {
        Department department = new Department();
        department.setName("HR");
        department = departmentRepository.save(department);

        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setDepartment(department);
        employee.setProjects(new HashSet<>());
        employeeRepository.save(employee);

        List<Employee> employees = employeeRepository.findByDepartmentId(department.getId());
        assertThat(employees).isNotEmpty();
    }

    @Test
    public void testFindByProjectsId() {
        Project project = new Project();
        project.setName("Project A");
        project = projectRepository.save(project);

        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setDepartment(new Department());
        employee.setProjects(Set.of(project));
        employeeRepository.save(employee);

        List<Employee> employees = employeeRepository.findByProjectsId(project.getId());
        assertThat(employees).isNotEmpty();
    }
}