package com.tw.core.control;

import com.google.gson.Gson;
import com.tw.core.UserDao;
import com.tw.core.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 * Created by twer on 7/13/15.
 */
@RestController
@RequestMapping(value = "/regist_emp")
public class empControl {

    @Autowired
    private UserDao userdao;

    //雇员登记
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String getAllEmUser() {
        Gson gson=new Gson();
        List<Employee> emps = userdao.queryAllEmUsers();
        return gson.toJson(emps);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addEmUser(@RequestBody Employee emp
    ){
          userdao.addEmp(emp);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delEmUser(@PathVariable Integer id) {
        Employee emp=new Employee();
        emp.setIdEmployee(id);
        userdao.delEmp(emp);
    }
}
