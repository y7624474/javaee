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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
@RestController
@RequestMapping(value = "/user")
public class UserControl {

@Autowired
    private UserDao userdao;


//用户管理
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String getAllUser() {
        Gson gson=new Gson();
        List<User> users = userdao.queryAllUsers();

        return gson.toJson(users);
    }


    @RequestMapping(value="/{id}")
    public ModelAndView deluser(@PathVariable Integer id)
    {
        User user = new User();
        user.setIdUser(id);

        userdao.deleteUser(user);
        return new ModelAndView("redirect:/home/user");

    }

    @RequestMapping("/home/user/quit")
    public ModelAndView quitlogin(HttpServletRequest request,HttpServletResponse response)
    {
        return quit(request,response);

    }


//登录

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public ModelAndView start()
//    {
//        return new ModelAndView("index");
//
//    }
//
//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public ModelAndView login(HttpServletRequest request,
//                              HttpServletResponse response,
//                              @RequestParam ("loginname") String username,
//                              @RequestParam ("password") String password)
//            throws UnsupportedEncodingException, NoSuchAlgorithmException {
//
//        if(verify(username,password))//做一些数据库验证
//        {
//
//            request.getSession().setAttribute("Login", "OK");
//            Cookie[] cookieid=request.getCookies();
//            System.out.print("1");
//            if(!cookieurl(cookieid).equals(""))
//            {
//                System.out.print("2");
//                String url=cookieurl(cookieid);
//                return new ModelAndView("redirect:"+url);
//            }
//
//            System.out.print("3");
//
//            return new ModelAndView("redirect:/home");
//
//        }
//        else {
//            return new ModelAndView("index");
//        }
//
//    }


//注册用户
    @RequestMapping(value = "/regist", method = RequestMethod.GET)
    public ModelAndView regist()
    {
        return new ModelAndView("registuser");

    }

    @RequestMapping(value="/regist",method = RequestMethod.POST)
    public ModelAndView registUser(@RequestParam ("username") String username,
                                   @RequestParam ("password") String pwd,
                                   @RequestParam ("email") String email,
                                   @RequestParam ("num") String num,

                                   HttpServletResponse response
    ) throws NoSuchAlgorithmException, IOException {
        int emnum=Integer.parseInt(num);
        PrintWriter out = response.getWriter();
        if(registverify(emnum)) {
            User user = new User();
            Employee emp=userdao.getById(Employee.class,emnum);
            user.setEmp(emp);
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(pwd);
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pwd.getBytes("UTF8"));
            byte resultData[] = md.digest();
            String strresult = convertToHexString(resultData);
            user.setPassword(strresult);
            userdao.addUser(user);

            out.println("注册成功！");
            return new ModelAndView("redirect:/");
        }
        else
        {

            out.print("<script>alert('系统仅限本公司雇员使用，请输入正确的工号！');</script>");
            return new ModelAndView("registuser");
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

        }

       return url;
    }

    public boolean verify(String username,String pwd) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pwd.getBytes("UTF8"));
        byte resultData[] = md.digest();
        String password_md5=convertToHexString(resultData);
//        UserDao userdao=new UserDao();
        List<User> users = userdao.queryAllUsers();
        for (User useri : users)
        {
            if(useri.getUsername().equals(username))
            {
                if(useri.getPassword().equals(password_md5))
                    return true;

            }
        }
        return false;
    }
    private boolean registverify(int num)
    {
        List<Employee> emps = userdao.queryAllEmUsers();
        List<User> us = userdao.queryAllUsers();
        for (User u : us)
        {
            if(u.getEmp().getIdEmployee()==num)
            {
                return false;
            }
        }
        for (Employee emp : emps)
        {
            if(emp.getIdEmployee()==num)
            {
                return true;
            }
        }

        return false;
    }

    private static String convertToHexString(byte data[]) {
        StringBuffer strBuffer = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            strBuffer.append(Integer.toHexString(0xff & data[i]));
        }
        return strBuffer.toString();
    }

    private ModelAndView quit(HttpServletRequest request,HttpServletResponse response)
    {
        request.getSession().setAttribute("Login", "NO");
        Cookie[] cookies=request.getCookies();

        for(Cookie cookie: cookies)
        {
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return new ModelAndView("redirect:/");
    }
}
