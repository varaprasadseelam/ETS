package com.ETS.EmployeeTrackingSystem;

// DepartmentRepositoryTest.java
import com.ETS.EmployeeTrackingSystem.Entity.Department;
import com.ETS.EmployeeTrackingSystem.Repository.DepartmentRepository;
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
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void testSaveDepartment() {
        Department department = new Department();
        department.setName("HR");
        department.setBudget(100000.0);

        Department savedDepartment = departmentRepository.save(department);
        assertThat(savedDepartment).isNotNull();
        assertThat(savedDepartment.getId()).isNotNull();
    }

    @Test
    public void testFindAll() {
        Department department1 = new Department();
        department1.setName("HR");
        department1.setBudget(100000.0);
        departmentRepository.save(department1);

        Department department2 = new Department();
        department2.setName("IT");
        department2.setBudget(200000.0);
        departmentRepository.save(department2);

        List<Department> departments = departmentRepository.findAll();
        assertThat(departments).hasSize(2);
    }
}