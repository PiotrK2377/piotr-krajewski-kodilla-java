package com.kodilla.hibernate.manytomany.dao;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CompanyDaoTestSuite {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    void testSaveManyToMany() {
        //Given
        Employee johnSmith = new Employee("John","Smith");
        Employee stephanieClarckson = new Employee("Stephanie","Clarckson");
        Employee lindaKovalsky = new Employee("Linda", "Kovalsky");

        Company softwareMachine = new Company("Software Machine");
        Company dataMaesters = new Company("Data Maesters");
        Company greyMatter = new Company("Grey Matter");

        softwareMachine.getEmployees().add(johnSmith);
        dataMaesters.getEmployees().add(stephanieClarckson);
        dataMaesters.getEmployees().add(lindaKovalsky);
        greyMatter.getEmployees().add(johnSmith);
        greyMatter.getEmployees().add(lindaKovalsky);

        johnSmith.getCompanies().add(softwareMachine);
        johnSmith.getCompanies().add(greyMatter);
        stephanieClarckson.getCompanies().add(dataMaesters);
        lindaKovalsky.getCompanies().add(dataMaesters);
        lindaKovalsky.getCompanies().add(greyMatter);

        //When
        companyDao.save(softwareMachine);
        int softwareMachineId = softwareMachine.getId();
        companyDao.save(dataMaesters);
        int dataMaestersId = dataMaesters.getId();
        companyDao.save(greyMatter);
        int greyMatterId = greyMatter.getId();

        //Then
        assertEquals(0, softwareMachineId);
        assertEquals(0,dataMaestersId);
        assertEquals(0,greyMatterId);

        //CleanUp
       try {
           companyDao.deleteById(softwareMachineId);
           companyDao.deleteById(dataMaestersId);
           companyDao.deleteById(greyMatterId);
       } catch (Exception e) {

       }
    }

    @Test
    void testFindCompanyAfterFirstThreeLetters() {
        //Given
        Company company = new Company("Amazon");
        companyDao.save(company);
        int companyId = company.getId();

        //When & Then
        assertEquals(1, companyDao.findCompanyWithFirstThreeLetters("Ama").size());

        //Clean
        try {
            companyDao.deleteById(companyId);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Test
    void testFindEmployeeByLastname() {
        //Given
        Employee employee = new Employee("John", "Smith");
        employeeDao.save(employee);
        int employeeId = employee.getId();

        //When & Then
        assertEquals(1, employeeDao.findEmployeeByLastname("Smith").size());

        //CleanUp
        try {
            employeeDao.deleteById(employeeId);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
