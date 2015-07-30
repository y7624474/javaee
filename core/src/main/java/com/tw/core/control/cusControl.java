package com.tw.core.control;

import com.google.gson.Gson;
import com.tw.core.UserDao;
import com.tw.core.entity.Customer;
import com.tw.core.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by twer on 7/26/15.
 */
@RestController
@RequestMapping(value = "/customer")
public class cusControl {

    @Autowired
    private UserDao userdao;

    //顾客信息
    @RequestMapping(method = RequestMethod.GET)
        public @ResponseBody String getAllCus() {
        Gson gson=new Gson();
        List<Customer> cus = userdao.queryAllCus();
        return gson.toJson(cus);
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addCus(@RequestParam("customer") String cusname,
                               @RequestParam ("coach") String coach){
        Customer cus=new Customer();
        cus.setCustomer(cusname);
        cus.setCoach(coach);
        getAllCus();
        userdao.addCus(cus);
        return new ModelAndView("redirect:/home/customer");
    }

    @RequestMapping(value = "/{id}")
    public ModelAndView delCus(@PathVariable Integer id) {
        Customer cus=new Customer();
        cus.setIdCustomer(id);
        userdao.delCus(cus);
        getAllCus();

        return new ModelAndView("redirect:/home/customer");
    }

}
