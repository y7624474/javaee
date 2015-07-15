package com.tw.core.control;

import com.tw.core.UserDao;
import com.tw.core.entity.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import javax.servlet.http.*;
/**
 * Created by twer on 7/13/15.
 */
@Controller
public class usercontrol {

//@Autowired
//private UserDao userdao;
    @RequestMapping(value = "/usertable", method = RequestMethod.GET)
    public ModelAndView getAllUser() {
        UserDao userdao=new UserDao();
        List<User> users = userdao.queryAllUsers();
        return new ModelAndView("usertable","userList",users);
    }


    @RequestMapping("/regist")
    public ModelAndView adduser()
    {
//        User user = new User();
//        user.setUsername(name);
//
//        UserDao userdao=new UserDao();
//        userdao.addUser(user);
//        getAllUser();
        return new ModelAndView("registuser");
    }

    @RequestMapping("/register")
    public ModelAndView registuser(@RequestParam ("username") String name)
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

    @RequestMapping("/usertable/quit")
    public ModelAndView quitlogin(HttpServletRequest request,HttpServletResponse response)
    {
        request.getSession().setAttribute("Login", "NO");
        System.out.print("quit");
        Cookie[] cookies=request.getCookies();

        for(Cookie cookie: cookies)
        {
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return new ModelAndView("redirect:/");

    }

    @RequestMapping("/regist/quit")
    public ModelAndView quitregistlogin(HttpServletRequest request,HttpServletResponse response)
    {
        request.getSession().setAttribute("Login", "NO");
        System.out.print("quit");
        Cookie[] cookies=request.getCookies();

        for(Cookie cookie: cookies)
        {
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return new ModelAndView("redirect:/");

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView start()
    {
        return new ModelAndView("index");

    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request,HttpServletResponse response,@RequestParam ("loginname") String name,@RequestParam ("password") String password)
    {
        request.getSession().setAttribute("sessionname",name);     //用Session保存用户名
        request.getSession().setAttribute("sessionpwd", password);
        System.out.print("0");
        if(name.equals("yy"))//做一些数据库验证
        {

            request.getSession().setAttribute("Login", "OK");
            Cookie[] cookieid=request.getCookies();
            System.out.print("1");
            if(!cookieurl(cookieid).equals(""))
            {
                System.out.print("2");
                String url=cookieurl(cookieid);
                return new ModelAndView("redirect:"+url);
            }

            System.out.print("3");

            return new ModelAndView("redirect:/usertable");

        }
        else {
            return new ModelAndView("index");
        }

    }

    public String cookieurl(Cookie[] id)
    {
        String url="";
        for(int i=0;i<id.length;i++)
        {
            if(id[i].getName().equals("url_jump"))
            {
                System.out.print("4");
                url=id[i].getValue();
                break;
            }
            if(id[i].getName().equals("url_jumpregist"))
            {
                System.out.print("4");
                url=id[i].getValue();
                break;
            }
        }

       return url;
    }
}
