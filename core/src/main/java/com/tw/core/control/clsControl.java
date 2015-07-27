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
public class clsControl {

    @Autowired
    private UserDao userdao;

    //课程信息
    @RequestMapping(value = "/home/class", method = RequestMethod.GET)
    public ModelAndView getAllCls() {
        ModelAndView modeandview=new ModelAndView();
        List<Classinfo> cls = userdao.queryAllCls();
        List<Employee> coach=userdao.queryCoach();
        modeandview.setViewName("class");
        modeandview.addObject("clsList", cls);
        modeandview.addObject("coach", coach);
        return modeandview;
    }


    @RequestMapping(value = "/home/class/add", method = RequestMethod.POST)
    public ModelAndView addCls(@RequestParam ("classname") String clsname,
                               @RequestParam ("time") String time,
                               @RequestParam ("coach") String coach){
        Classinfo cls=new Classinfo();
        cls.setClassname(clsname);
        cls.setTime(time);
        cls.setCoach(coach);

        userdao.addCls(cls);
        getAllCls();
        return new ModelAndView("redirect:/home/class");
    }

    @RequestMapping(value = "/home/class/update", method = RequestMethod.POST)
    public ModelAndView updateCls(@RequestParam ("updateid") String id,
                                  @RequestParam ("updateclassname") String clsname,
                                  @RequestParam ("updatetime") String time,
                                  @RequestParam ("updatecoach") String coach){
        Classinfo cls=new Classinfo();
        cls.setIdClass(Integer.parseInt(id));
        cls.setClassname(clsname);
        cls.setTime(time);
        cls.setCoach(coach);

        userdao.updateCls(cls);
        getAllCls();
        return new ModelAndView("redirect:/home/class");
    }

    @RequestMapping(value = "/home/class/{id}")
    public ModelAndView delCls(@PathVariable Integer id) {
        Classinfo cls=new Classinfo();
        cls.setIdClass(id);
        userdao.delCls(cls);
        getAllCls();

        return new ModelAndView("redirect:/home/class");
    }
}
