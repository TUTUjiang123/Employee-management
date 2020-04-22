package com.tutu.springboot01helloworldquickly.controller;

import com.tutu.springboot01helloworldquickly.dao.DepartmentDao;
import com.tutu.springboot01helloworldquickly.dao.EmployeeDao;
import com.tutu.springboot01helloworldquickly.entities.Department;
import com.tutu.springboot01helloworldquickly.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //查询所有员工返回列表页面
    @GetMapping("/emps")
    public String  list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        // thymeleaf默认就会拼串
        // classpath:/templates/xxxx.html
        return "emp/list";
    }

    //来到员工添加页面
    @GetMapping("/emp")
    public String toAddPage(){
        //来到添加页面,查出所有的部门，在页面显示
//        Collection<Department> departments = departmentDao.getDepartments();
//        model.addAttribute("depts",departments);
        return "emp/add";
    }
}
