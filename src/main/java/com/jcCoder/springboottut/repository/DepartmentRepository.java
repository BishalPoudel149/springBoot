package com.jcCoder.springboottut.repository;

import com.jcCoder.springboottut.entity.Department;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    public  Department findByDepartmentNameIgnoreCase(String departmentName);

    public List<Department> findByDepartmentAddress(String address);
}
