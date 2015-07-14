package com.tw.core.control;

import com.tw.core.UserDao;
import com.tw.core.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by twer on 7/13/15.
 */
@Controller
public class usercontrol {



    @RequestMapping(value = "/usertable", method = RequestMethod.GET)
    public ModelAndView getAllUser() {
        UserDao userdao=new UserDao();
        List<User> users = userdao.queryAllUsers();
        return new ModelAndView("usertable","userList",users);
    }


    @RequestMapping("/usertable")
    public ModelAndView adduser(@RequestParam ("username") String name)
    {
        User user = new User();
        user.setUsername(name);

        UserDao userdao=new UserDao();
        userdao.addUser(user);
        getAllUser();
        return new ModelAndView("redirect:/usertable");
    }


    @RequestMapping("/usertable/del")
    public ModelAndView deluser(@RequestParam ("username") String name)
    {
        User user = new User();
        user.setUsername(name);

        UserDao userdao=new UserDao();
        userdao.deleteUser(user);
        getAllUser();
        return new ModelAndView("redirect:/usertable");

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView start()
    {
        return new ModelAndView("index");

    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam ("loginname") String name)
    {
        System.out.println("~~~~~~~~~~~~~~");
        if(name=="") {
            return new ModelAndView("index");
        }
        else {
            return new ModelAndView("redirect:/usertable");
        }

    }
}
