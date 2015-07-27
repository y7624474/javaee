package com.tw.core.control;

import com.tw.core.UserDao;
import com.tw.core.entity.Classinfo;
import com.tw.core.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by twer on 7/26/15.
 */
@Controller

public class priControl {


    @Autowired
    private UserDao userdao;

    //私教预约

    @RequestMapping(value = "/home/private", method = RequestMethod.GET)
    public ModelAndView getPrivate() {
        ModelAndView modeandview=new ModelAndView();

        List<Classinfo> pricls = userdao.queryAllCls();
        List<Employee> coach=userdao.queryCoach();
        modeandview.setViewName("private");
        modeandview.addObject("clsList",pricls);
        modeandview.addObject("coach",coach);

        return modeandview;
    }

    @RequestMapping(value = "home/private/selectcoach", method = RequestMethod.POST)
    public ModelAndView selectCoach(@RequestParam("coach")String coach){
        ModelAndView modeandview=new ModelAndView();

        List<Classinfo> spricls = userdao.queryPriCls(coach);
        List<Employee> scoach=userdao.queryCoach();
        modeandview.setViewName("private");
        modeandview.addObject("clsList",spricls);
        modeandview.addObject("coach",scoach);
        return modeandview;
    }

    @RequestMapping(value = "home/private/selecttime", method = RequestMethod.POST)
    public ModelAndView selectTime(@RequestParam ("time")String time){
        ModelAndView modeandview=new ModelAndView();

        List<Classinfo> tpricls = userdao.queryPriTimeCls(time);
        List<Employee> tcoach=userdao.queryCoach();
        modeandview.setViewName("private");
        modeandview.addObject("clsList", tpricls);
        modeandview.addObject("coach", tcoach);
        return modeandview;
    }

    @RequestMapping(value = "home/private/date", method = RequestMethod.POST)
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
