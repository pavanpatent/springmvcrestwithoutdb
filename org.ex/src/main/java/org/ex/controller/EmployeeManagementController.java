package org.ex.controller;

import java.util.List;

import org.ex.dao.EmployeeManagementDAO;
import org.ex.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeManagementController {

	@Autowired
	private EmployeeManagementDAO employeeManagementDAO;

//	@Autowired
//	private EmployeeRepository employeeRepository;

	@GetMapping("/getEmployeesDB")
	public List<Employee> getEmployeesDB() {
		return employeeManagementDAO.getEmployees();
	}

	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		return employeeManagementDAO.list();
	}
	
	@GetMapping(value = "/employees/{id}")
	public ResponseEntity<?> getEmployee(@PathVariable("id") int id) {
		Employee employee = employeeManagementDAO.get(id);
		if(employee == null) {
			return new ResponseEntity<String>("No employee data exist for this ID, Please try another.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@PostMapping(value = "/employees")
	public ResponseEntity<?> save(@RequestBody Employee employee) {
		employeeManagementDAO.save(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@DeleteMapping(value = "/employees/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if(employeeManagementDAO.delete(id) <= 0) {
			return new ResponseEntity<String>("No employee data exist for this ID, Please try another.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Integer>(id, HttpStatus.OK);
	}
}