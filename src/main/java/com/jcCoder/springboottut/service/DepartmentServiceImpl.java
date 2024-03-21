package com.jcCoder.springboottut.service;

import com.jcCoder.springboottut.entity.Department;
import com.jcCoder.springboottut.error.DepartmentNotFoundException;
import com.jcCoder.springboottut.kafka.constant.KafkaConstant;
import com.jcCoder.springboottut.repository.DepartmentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Autowired
    private KafkaTemplate<String,Department>kafkaTemplate;

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

        Department updatedDepartment= departmentRepository.save(depart);
        kafkaTemplate.send(KafkaConstant.DEPARTMENT_DETAILS,updatedDepartment);

        return updatedDepartment;
    }

    @Override
    public String getHodName(String department) {

        // Procedural call
    /*    StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("bis.proc.getHod");
        storedProcedureQuery.registerStoredProcedureParameter("id", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("department", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("iv_count", Integer.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("hodName", String.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("id", 1);
        storedProcedureQuery.setParameter("department", department);

        storedProcedureQuery.execute();

        Integer iv_count = (Integer) storedProcedureQuery.getOutputParameterValue("iv_count");
        String hodName = (String) storedProcedureQuery.getOutputParameterValue("hodName");



        // Make a procedural call

        return hodName;*/

        Integer id = 1; // Assuming your ID
        String departmentName = department; // Assuming your department name

        Integer ivCount = null; // This will be populated by the stored procedure call
        String hodName = null; // This will be populated by the stored procedure call

        departmentRepository.getHodName(id, departmentName, ivCount, hodName);

        // Now ivCount and hodName should be populated with the values from the stored procedure
        System.out.println("ivCount: " + ivCount);
        System.out.println("hodName: " + hodName);

        return hodName;
    }

    @Override
    public String getHodJDBC(String department){

        String sql="SELECT ho.hod_name from hod ho " +
                    "JOIN department dp " +
                    "ON ho.id= dp.hod " +
                    "where dp.department_name=?";
        String hodName = jdbcTemplate.queryForObject(sql, String.class, new Object[]{department});

        return hodName;
    }




}
