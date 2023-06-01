package org.ex.dao;


import org.ex.model.Employee;

import java.util.List;

public interface EmployeeManagementDAO {
	public List<Employee> getEmployees();
	public void save(Employee employee);
	
	public int delete(int id);
	
	public Employee get(int id);
	
	public List<Employee> list();
}