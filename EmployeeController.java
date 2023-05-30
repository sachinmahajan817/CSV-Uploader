package com.example.csvuploader.controller;

import com.example.csvuploader.entity.Employee;
import com.example.csvuploader.repository.IEmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    IEmployeeRepo employeeRepo;
    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping("/employees")
    public ResponseEntity<Employee> save(@RequestBody Employee employee) {
        try {
            return new ResponseEntity<>(employeeRepo.save(employee), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> list = employeeRepo.findAll();
            if (list.isEmpty() || list.size() == 0){
                return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
