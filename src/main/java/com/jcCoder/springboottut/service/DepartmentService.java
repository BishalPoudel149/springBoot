package com.jcCoder.springboottut.service;

import com.jcCoder.springboottut.entity.Department;
import com.jcCoder.springboottut.error.DepartmentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    public Department saveDepartment(Department department);

    public List<Department> getAllDepartment();

    public Department getAllDepartmentName(String departmentName);

    public Department fetchByIdService(Long id) throws DepartmentNotFoundException;

    public List<Department> fetAllByAddressService(String address);

    public void deleteDepartmentService(Long id);

    public Department updateDepartment(Long id, Department department);
}
