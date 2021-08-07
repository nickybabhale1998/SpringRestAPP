package com.server.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.server.entity.Employee;
import com.server.exceptions.DatabaseErrorException;
import com.server.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Employee")
@RestController
@RequestMapping(value = { "/data" })
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@GetMapping(value = { "/home" })
	@ResponseBody
	public String getHome() {
		return "Home";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json", produces = {
			"application/json" })

	public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee emp) throws  DatabaseErrorException {
		Employee savedEmp = service.saveEmp(emp);
		return new ResponseEntity<Employee>(savedEmp, HttpStatus.CREATED);
	}

	@GetMapping(value = { "/get/{id}" }, produces = { "application/json" })
	@ApiOperation(value = "Search Employee by Id", response = Employee.class)
	public ResponseEntity<Employee> getEmp(@PathVariable int id) throws  DatabaseErrorException {
		Employee obj = service.getEmpById(id);
		return new ResponseEntity<Employee>(obj, HttpStatus.OK);
	}

	@GetMapping(value = { "/getAll" }, produces = { "application/json" })
	@ApiOperation(value = "Find  Employee")

	public ResponseEntity<List<Employee>> getAllEmp() throws  DatabaseErrorException {
		List<Employee> list = service.getAllEmp();
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteEmployee(@PathVariable int id) throws  DatabaseErrorException {
		String msg = service.deleteEmp(id);
		return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = "application/json", produces = {
			"application/json" })
	public ResponseEntity<Employee> updateEmplouee(@RequestBody Employee e) throws  DatabaseErrorException {
		int id =e.getId();
		float salary =e.getSalary();
		Employee updatedEmp = service.updateEmp(id, salary);
		return new ResponseEntity<Employee>(updatedEmp, HttpStatus.ACCEPTED);
	}

}
