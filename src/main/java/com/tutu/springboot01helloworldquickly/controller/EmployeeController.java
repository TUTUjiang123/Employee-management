package com.tutu.springboot01helloworldquickly.controller;

import com.tutu.springboot01helloworldquickly.dao.DepartmentDao;
import com.tutu.springboot01helloworldquickly.dao.EmployeeDao;
import com.tutu.springboot01helloworldquickly.entities.Department;
import com.tutu.springboot01helloworldquickly.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String toAddPage(Model model){
        //来到添加页面,查出所有的部门，在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    //添加员工
    //SpringMvc自动将请求参数和入参对象的属性进行一一绑定：要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //来到员工修改页面，查出当前员工，在页面回显（在页面依次取出来）
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id ,Model model){
       Employee employee = employeeDao.get(id);
       model.addAttribute("emp",employee);

        //页面要显示所有的部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    //修改员工
    @PutMapping("/emp")
    public String EditEmp(Employee employee) {
        employeeDao.save(employee);
        System.out.println("修改的员工数据："+employee);
        return "redirect:/emps";
    }

    //员工删除
    @PostMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
