package com.example.employee;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


import com.example.employee.Employee;
import com.example.employee.EmployeeRepository;

import java.util.*;

public class EmployeeService implements EmployeeRepository {
    int uniqueId = 7;

    private static HashMap<Integer, Employee> employeeList = new HashMap<>();

    public EmployeeService() {
        employeeList.put(1, new Employee(1, "John Doe", "johndoe@example.com", "Marketing"));
        employeeList.put(2, new Employee(2, "Jane Smith", "janesmith@example.com", "Human Resources"));
        employeeList.put(3, new Employee(3, "Bob Johnson", "bjohnson@example.com", "Sales"));
        employeeList.put(4, new Employee(4, "Alice Lee", "alee@example.com", "IT"));
        employeeList.put(5, new Employee(5, "Mike Brown", "mbrown@example.com", "Finance"));
        employeeList.put(6, new Employee(6, "Sara Lee", "slee@example.com", "Operations"));

    }

    @Override
    public ArrayList<Employee> getEmployee(){
        Collection<Employee> allSongs = employeeList.values();
        ArrayList<Employee> songsPlayList = new ArrayList<>(allSongs);
        return songsPlayList;
    }
    @Override
    public Employee getEmployeeById(int songId){
        Employee song = employeeList.get(songId);
        if(song == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return song;
    }
    @Override
    public Employee addEmployee(Employee song){
        song.setEmployeeId(uniqueId);
        employeeList.put(uniqueId , song);
        uniqueId += 1;
        return song;
    }
    @Override
    public Employee updateEmployee(int employeeId , Employee song){
        Employee existingSong = employeeList.get(employeeId);
        if(existingSong == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if(song.getEmployeeName() != null){
            existingSong.setEmployeeName(song.getEmployeeName());
        }
        if(song.getEmail() != null){
            existingSong.setEmail(song.getEmail());
        }
        if(song.getDepartment() != null){
            existingSong.setDepartment(song.getDepartment());
        }
        return existingSong;
    }
    @Override
    public void deleteEmployee(int employeeId){
        Employee song = employeeList.get(employeeId);
        if(song == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else{
            employeeList.remove(employeeId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

}
