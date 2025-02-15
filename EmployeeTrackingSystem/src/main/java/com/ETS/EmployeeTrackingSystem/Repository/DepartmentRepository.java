package com.ETS.EmployeeTrackingSystem.Repository;

import com.ETS.EmployeeTrackingSystem.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
