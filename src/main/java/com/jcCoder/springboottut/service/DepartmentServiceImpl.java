package com.jcCoder.springboottut.service;

import com.jcCoder.springboottut.entity.Department;
import com.jcCoder.springboottut.error.DepartmentNotFoundException;
import com.jcCoder.springboottut.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;


    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchByIdService(Long id) throws DepartmentNotFoundException {
       Optional<Department> department= departmentRepository.findById(id);

       if(!department.isPresent()){
           throw new DepartmentNotFoundException("Department with id "+id+" is not found");
       }
       return department.get();
    }


    @Override
    public Department getAllDepartmentName(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }

    @Override
    public List<Department> fetAllByAddressService(String address) {
        return departmentRepository.findByDepartmentAddress(address);
    }

    @Override
    public void deleteDepartmentService(Long id) {
        Department depart=departmentRepository.findById(id).get();

        departmentRepository.delete(depart);
    }

    @Override
    public Department updateDepartment(Long id, Department department) {

        Department depart=departmentRepository.findById(id).get();

        if(Objects.nonNull(department.getDepartmentName()) &&
                !"".equalsIgnoreCase(department.getDepartmentName())){
            depart.setDepartmentName(department.getDepartmentName());
        }
        if(Objects.nonNull(department.getDepartmentAddress()) &&
                !"".equalsIgnoreCase(department.getDepartmentAddress())){
            depart.setDepartmentAddress(department.getDepartmentAddress());
        }

        if(Objects.nonNull(department.getDepartmentCode()) &&
                !"".equalsIgnoreCase(department.getDepartmentCode())){
            depart.setDepartmentCode(department.getDepartmentCode());
        }

        return departmentRepository.save(depart);
    }


}
