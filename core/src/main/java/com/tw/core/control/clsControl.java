package com.tw.core.control;

import com.google.gson.Gson;
import com.tw.core.UserDao;
import com.tw.core.entity.Classinfo;
import com.tw.core.entity.Customer;
import com.tw.core.entity.Employee;
import com.tw.core.entity.User;
import org.hibernate.Session;
import org.hibernate.annotations.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
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
    @RequestMapping(value = "/class", method = RequestMethod.GET)
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
    public void updateCls(HttpServletResponse response,@RequestParam ("updateid") String id,
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
        Gson gson = new Gson();
        String strcls = gson.toJson(cls);

        try {
            response.getWriter().write(strcls.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return new ModelAndView("redirect:/home/class");
    }

    @RequestMapping(value = "/home/class/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void delCls(@PathVariable Integer id) {
        Classinfo cls=new Classinfo();
        cls.setIdClass(id);
        userdao.delCls(cls);
        getAllCls();

//        return new ModelAndView("redirect:/home/class");
    }
}
