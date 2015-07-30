package com.tw.core.control;

import com.google.gson.Gson;
import com.tw.core.UserDao;
import com.tw.core.entity.Classinfo;
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
@RequestMapping(value = "/private")
public class priControl {


    @Autowired
    private UserDao userdao;

    //私教预约

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String getAllPri() {
        Gson gson=new Gson();
        List<Classinfo> pricls = userdao.queryAllCls();
        return gson.toJson(pricls);

    }

    @RequestMapping(value = "/selectcoach", method = RequestMethod.POST)
    public ModelAndView selectCoach(@RequestParam("coach")String coach){
        ModelAndView modeandview=new ModelAndView();

        List<Classinfo> spricls = userdao.queryPriCls(coach);
        List<Employee> scoach=userdao.queryCoach();
        modeandview.setViewName("private");
        modeandview.addObject("clsList",spricls);
        modeandview.addObject("coach",scoach);
        return modeandview;
    }

    @RequestMapping(value = "/selecttime", method = RequestMethod.POST)
    public ModelAndView selectTime(@RequestParam ("time")String time){
        ModelAndView modeandview=new ModelAndView();

        List<Classinfo> tpricls = userdao.queryPriTimeCls(time);
        List<Employee> tcoach=userdao.queryCoach();
        modeandview.setViewName("private");
        modeandview.addObject("clsList", tpricls);
        modeandview.addObject("coach", tcoach);
        return modeandview;
    }

    @RequestMapping(value = "/date", method = RequestMethod.POST)
    public ModelAndView priDate(@RequestParam ("datecoach")String coach,
                                @RequestParam ("datetime")String time){
        Classinfo cls=new Classinfo();
        cls.setClassname("私教");
        cls.setTime(time);
        cls.setCoach(coach);
        userdao.addCls(cls);
        return new ModelAndView("redirect:/home/private");

    }
}
