package com.tw.core.control;

import com.tw.core.UserDao;
import com.tw.core.entity.Classinfo;
import com.tw.core.entity.Customer;
import com.tw.core.entity.Employee;
import com.tw.core.entity.User;
import org.hibernate.Session;
import org.hibernate.annotations.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Date;
import java.util.List;
import javax.servlet.http.*;
/**
 * Created by twer on 7/13/15.
 */
@Controller
public class empControl {

    @Autowired
    private UserDao userdao;

    //雇员登记
    @RequestMapping(value = "/home/regist_emp", method = RequestMethod.GET)
    public ModelAndView getAllEmUser() {
        List<Employee> emps = userdao.queryAllEmUsers();
        return new ModelAndView("regist_employee","empList",emps);
    }

    @RequestMapping(value = "/home/regist_emp/add", method = RequestMethod.POST)
    public ModelAndView addEmUser(@RequestParam ("name") String name,
                                  @RequestParam ("role") String role,
                                  @RequestParam ("num") String num
    ){
        Employee emp=new Employee();

        emp.setName(name);
        emp.setRole(role);
        emp.setIdEmployee(Integer.parseInt(num));
        userdao.addEmp(emp);
        getAllEmUser();
        return new ModelAndView("redirect:/home/regist_emp");
    }

    @RequestMapping(value = "/home/regist_emp/{id}")
    public ModelAndView delEmUser(@PathVariable Integer id) {
        Employee emp=new Employee();
        emp.setIdEmployee(id);
        userdao.delEmp(emp);
        getAllEmUser();
        return new ModelAndView("redirect:/home/regist_emp");
    }
}
