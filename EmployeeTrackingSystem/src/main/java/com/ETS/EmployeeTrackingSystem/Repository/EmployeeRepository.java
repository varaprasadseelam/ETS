package com.ETS.EmployeeTrackingSystem.Repository;

import com.ETS.EmployeeTrackingSystem.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartmentId(Long departmentId);
    List<Employee> findByProjectsId(Long projectId);
}