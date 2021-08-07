package com.server.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.server.dao.EmployeeDAO;
import com.server.entity.Employee;
import com.server.exceptions.customExceptions.DatabaseException;
import com.server.exceptions.customExceptions.RecordNotFoundException;
import com.server.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO dao;

	@Override
	
	public Employee saveEmp(Employee emp) throws DatabaseException {
		try {
			emp = dao.save(emp);
		} catch (DataAccessException e) {
			throw new DatabaseException("Some issue with database!! "+e.getMessage());
		}
		return emp;
	}

	@Override
	 
	public String deleteEmp(int id) throws DatabaseException {
		boolean flag = dao.existsById(id);
		if (flag == false)
			throw new RecordNotFoundException("Employee not found with given id : " + id);
		try {
			dao.deleteById(id);
			return "The Employee with id :" + id + " deleted";
		} catch (DataAccessException e) {
			throw new DatabaseException("Some issue with database!! "+e.getMessage());
		}
	}

	@Override
	
	public Employee updateEmp(int id, float salary) throws DatabaseException {
		Optional<Employee> optional = null;
		if (!dao.existsById(id))
			throw new RecordNotFoundException("Employee not found with given id : " + id);
		try {
			optional = dao.findById(id);
		} catch (DataAccessException e) {
			throw new DatabaseException("Some issue with database!! "+e.getMessage());
		}
		Employee obj = optional.get();
		obj.setSalary(salary);
		dao.save(obj);
		return obj;
	}

	@Override

	public Employee getEmpById(int id) throws DatabaseException {
		Optional<Employee> optional = null;
		boolean flag = dao.existsById(id);
		if (!flag)
			throw new RecordNotFoundException("Employee not found with given id : " + id);
		try {
			optional = dao.findById(id);
		} catch (DataAccessException e) {
			throw new DatabaseException("Some issue with database!! "+e.getMessage());
		}
		Employee obj = optional.get();
		return obj;
	}

	@Override

	public List<Employee> getAllEmp() throws DatabaseException {
		Iterable<Employee> iterable = null;
		try {
			iterable = dao.findAll();
		} catch (DataAccessException e) {
			throw new DatabaseException("Some issue with database!! "+e.getMessage());
		}
		List<Employee> list = new ArrayList<Employee>();
		iterable.forEach(list::add);
		return list;
	}

	@Override

	public int getCount() throws DatabaseException {
		int count = 0;
		try {
			count = (int) dao.count();
		} catch (DataAccessException e) {
			throw new DatabaseException("Some issue with database!! "+e.getMessage());
		}
		return count;
	}
}
