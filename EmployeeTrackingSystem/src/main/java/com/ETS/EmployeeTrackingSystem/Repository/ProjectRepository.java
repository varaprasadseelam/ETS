package com.ETS.EmployeeTrackingSystem.Repository;

import com.ETS.EmployeeTrackingSystem.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByDepartmentId(Long departmentId);
}
