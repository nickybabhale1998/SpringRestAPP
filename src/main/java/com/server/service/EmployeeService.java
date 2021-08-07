package com.server.service;

import java.util.List;

import com.server.entity.Employee;
import com.server.exceptions.DatabaseErrorException;


public interface EmployeeService {
	
	public Employee saveEmp(Employee emp) throws  DatabaseErrorException;
	
	public String deleteEmp(int id)throws  DatabaseErrorException;
	
	public Employee updateEmp(int id,float salary)throws  DatabaseErrorException;
	
	public Employee getEmpById(int id)throws  DatabaseErrorException;
	
	public List<Employee> getAllEmp()throws  DatabaseErrorException;


}
