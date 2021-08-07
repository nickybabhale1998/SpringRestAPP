package com.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.server.dao.EmployeeDAO;
import com.server.entity.Employee;
import com.server.exceptions.DatabaseErrorException;
import com.server.exceptions.RecordNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO dao;

	//employee service 
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Employee saveEmp(Employee emp) throws  DatabaseErrorException {
		try {
			emp = dao.save(emp);
		} catch (Exception e) {
			throw new  DatabaseErrorException("Some with issue with database!! " + e.getMessage());
		}
		return emp;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)

	public String deleteEmp(int id) throws  DatabaseErrorException {
		boolean flag = dao.existsById(id);
		if (flag == false)
			throw new RecordNotFoundException("Employee not found  " + id);
		try {
			dao.deleteById(id);
			return "The Employee with id :" + id + " deleted";
		} catch (Exception e) {
			throw new  DatabaseErrorException("Some issue with database!! " + e.getMessage());
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)

	public Employee updateEmp(int id, float salary) throws  DatabaseErrorException {
		Optional<Employee> optional = null;
		if (!dao.existsById(id))
			throw new RecordNotFoundException("Employee not found with given id : " + id);
		try {
			optional = dao.findById(id);
		} catch (Exception e) {
			throw new  DatabaseErrorException("Some issue with database!! " + e.getMessage());
		}
		Employee emp = optional.get();
		emp.setSalary(salary);
		dao.save(emp);
		return emp;
	}

	@Override
	@Transactional(propagation = Propagation.NEVER)

	public Employee getEmpById(int id) throws  DatabaseErrorException {
		Optional<Employee> optional = null;
		boolean flag = dao.existsById(id);
		if (!flag)
			throw new RecordNotFoundException("Employee not found with given id : " + id);
		try {
			optional = dao.findById(id);
		} catch (Exception e) {
			throw new  DatabaseErrorException("Some issue with database!! " + e.getMessage());
		}
		Employee obj = optional.get();
		return obj;
	}

	@Override
	@Transactional(propagation = Propagation.NEVER)
	public List<Employee> getAllEmp() throws  DatabaseErrorException {
		Iterable<Employee> iterable = null;
		try {
			iterable = dao.findAll();
		} catch (Exception e) {
			throw new  DatabaseErrorException("Some issue with database!! " + e.getMessage());
		}
		List<Employee> list = new ArrayList<Employee>();
		iterable.forEach(list::add);
		return list;
	}

	
}
