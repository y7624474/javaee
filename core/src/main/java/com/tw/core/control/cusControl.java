package com.tw.core.control;

import com.tw.core.UserDao;
import com.tw.core.entity.Customer;
import com.tw.core.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by twer on 7/26/15.
 */
@Controller

public class cusControl {

    @Autowired
    private UserDao userdao;

    //顾客信息
    @RequestMapping(value = "/home/customer", method = RequestMethod.GET)
    public ModelAndView getAllCus() {
        ModelAndView modeandview=new ModelAndView();
        List<Customer> cus = userdao.queryAllCus();
        List<Employee> coach=userdao.queryCoach();
        modeandview.setViewName("customer");
        modeandview.addObject("cusList", cus);
        modeandview.addObject("coach",coach);
        return modeandview;
    }


    @RequestMapping(value = "/home/customer/add", method = RequestMethod.POST)
    public ModelAndView addCus(@RequestParam("customer") String cusname,
                               @RequestParam ("coach") String coach){
        Customer cus=new Customer();
        cus.setCustomer(cusname);
        cus.setCoach(coach);
        getAllCus();
        userdao.addCus(cus);
        return new ModelAndView("redirect:/home/customer");
    }

    @RequestMapping(value = "/home/customer/{id}")
    public ModelAndView delCus(@PathVariable Integer id) {
        Customer cus=new Customer();
        cus.setIdCustomer(id);
        userdao.delCus(cus);
        getAllCus();

        return new ModelAndView("redirect:/home/customer");
    }

}
