package net.guides.springboot2.springboot2jpacrudexample.services;

import lombok.extern.slf4j.Slf4j;
import net.guides.springboot2.springboot2jpacrudexample.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2jpacrudexample.model.Employee;
import net.guides.springboot2.springboot2jpacrudexample.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    private IEmployeeRepository IEmployeeRepository;

    public List<Employee> getEmployeeList() {
        return IEmployeeRepository.findAll();
    }

    public Optional<Employee> findEmployeeById(Long employeeId) throws ResourceNotFoundException {
        return Optional.ofNullable(IEmployeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId)));
    }

    public Employee updateEmployee(Long employeeId, Employee employeeDetails) throws ResourceNotFoundException {

        Employee employee = IEmployeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = IEmployeeRepository.save(employee);

        return employee;
    }

    public Employee creeerEmployer(Employee employee) {

        return IEmployeeRepository.save(employee);
    }

    public void supprimerEmployee(Long employeeId) throws ResourceNotFoundException {

        Employee employee = IEmployeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        IEmployeeRepository.delete(employee);
    }
}
