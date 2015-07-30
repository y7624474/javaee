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
@RestController
@RequestMapping(value = "/class")
public class clsControl {

    @Autowired
    private UserDao userdao;

    //课程信息
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String getAllCls() {
        Gson gson=new Gson();
        List<Classinfo> cls = userdaoç.queryAllCls();
//        List<Employee> coach=userdao.queryCoach();
        return gson.toJson(cls);
    }

    @RequestMapping(value = "/getcoach" ,method = RequestMethod.GET)
    public @ResponseBody String getCoachs() {
        Gson gson=new Gson();
        List<Employee> coachs=userdao.queryCoach();
        return gson.toJson(coachs);
    }


    @RequestMapping(method = RequestMethod.POST)
    public void addCls(@RequestBody Classinfo cls){
        String str=cls.getTime().substring(0,10);
        cls.setTime(str);
        userdao.addCls(cls);
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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delCls(@PathVariable Integer id) {
        Classinfo cls=new Classinfo();
        cls.setIdClass(id);
        userdao.delCls(cls);
    }
}
