package org.ex.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaQuery;
import org.ex.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeManagementDAOImpl implements EmployeeManagementDAO {
//	@Autowired
//	NamedParameterJdbcTemplate jdbcTemplate;
@PersistenceContext
private EntityManager entityManager;

	public List<Employee> getEmployees(){

		CriteriaQuery<Employee> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Employee.class);
		criteriaQuery.from(Employee.class);
		return entityManager.createQuery(criteriaQuery).getResultList();

	/*	return entityManager.createQuery("SELECT * FROM employees",
				(ResultSet rs, int rowNum) -> {
					Employee p = new Employee();
					p.setId(rs.getInt("id"));
					p.setName(rs.getString("name"));
					p.setAddress(rs.getString("address"));
					p.setEmail(rs.getString("email"));
					p.setTelephone(rs.getString("telephone"));
					return p;
				});*/
	}
//EmployeeRepository employeeRepository;
//	public List<Employee> getEmployees() {
//		return employeeRepository.findAll();
//	}


	// Dummy database. Initialize with some dummy values.
		private static List<Employee> employees;
		{
			employees = new ArrayList<Employee>();
			employees.add(new Employee(101, "Test 1", "test1@gmail.com", "", "121-232-3435"));
			employees.add(new Employee(201, "Test 4", "test3@gmail.com", "", "343-545-2345"));
			employees.add(new Employee(301, "Test 3", "test3@gmail.com", "", "876-237-2987"));
		}

		/**
		 * Returns list of employees from dummy database.
		 * 
		 * @return list of employees
		 */
		public List<Employee> list() {
			return employees;
		}

		/**
		 * Return employee object for given id from dummy database. If employee is
		 * not found for id, returns null.
		 * 
		 * @param id
		 *            employee id
		 * @return employee object for given id
		 */
		public Employee get(int id) {
			for (Employee employee : employees) {
				if (employee.getId() == id) {
					return employee;
				}
			}
			return null;
		}

		/**
		 * Create new employee in dummy database. Updates the id and insert new
		 * employee in list.
		 * 
		 * @param employee
		 *            Employee object
		 * @return employee object with updated id
		 */
		public Employee create(Employee employee) {
			employee.setId((int) System.currentTimeMillis());
			employees.add(employee);
			return employee;
		}

		/**
		 * Delete the employee object from dummy database. If employee not found for
		 * given id, returns null.
		 * 
		 * @param id
		 *            the employee id
		 * @return id of deleted employee object
		 */
		public int delete(int id) {

			for (Employee employee : employees) {
				if (employee.getId() == id) {
					employees.remove(employee);
					return id;
				}
			}

			return 0;
		}

		/**
		 * Update the employee object for given id in dummy database. If employee
		 * not exists, returns null
		 * 
//		 * @param id
		 * @param employee
		 * @return employee object with id
		 */
		public void save(Employee employee) {
			int oldId = 0;
			if(employee != null && employee.getId() > 0) {
				oldId = employee.getId();
				Employee emp = get(oldId);
				employees.remove(emp);
			}
			employee.setId(oldId);
			employees.add(employee);
		}
	
}