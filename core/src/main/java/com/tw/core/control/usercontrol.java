package com.tw.core.control;

import com.tw.core.UserDao;
import com.tw.core.entity.Classinfo;
import com.tw.core.entity.Customer;
import com.tw.core.entity.Employee;
import com.tw.core.entity.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class usercontrol {

@Autowired
    private UserDao userdao;

//主界面
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView getHome() {
        List<User> users = userdao.queryAllUsers();
        return new ModelAndView("home","userList",users);
    }

    @RequestMapping(value = "/home/user", method = RequestMethod.GET)
    public ModelAndView getUser() {
        List<User> users = userdao.queryAllUsers();
        return new ModelAndView("user","userList",users);
    }

//用户管理
    @RequestMapping("/home/user/del")
    public ModelAndView deluser(@RequestParam ("username") String username)
    {
        User user = new User();
        user.setUsername(username);

        userdao.deleteUser(user);
        getUser();
        return new ModelAndView("redirect:/home/user");

    }

    @RequestMapping("/home/user/quit")
    public ModelAndView quitlogin(HttpServletRequest request,HttpServletResponse response)
    {
        return quit(request,response);

    }

//雇员登记
    @RequestMapping(value = "/home/regist_emp", method = RequestMethod.GET)
    public ModelAndView getAllEmUser() {
        List<Employee> emps = userdao.queryAllEmUsers();
        return new ModelAndView("regist_employee","empList",emps);
    }

    @RequestMapping(value = "/home/regist_emp/add", method = RequestMethod.POST)
    public ModelAndView addEmUser(@RequestParam ("name") String name,
                                  @RequestParam ("role") String role){
        Employee emp=new Employee();
        emp.setName(name);
        emp.setRole(role);
        getAllEmUser();
        userdao.addEmp(emp);
        return new ModelAndView("redirect:/home/regist_emp");
    }

    @RequestMapping(value = "/home/regist_emp/del", method = RequestMethod.POST)
    public ModelAndView delEmUser(@RequestParam ("name") String name) {
        Employee emp=new Employee();
        emp.setName(name);
        userdao.delEmp(emp);
        getAllEmUser();

        return new ModelAndView("redirect:/home/regist_emp");
    }


    @RequestMapping("/home/regist_emp/quit")
    public ModelAndView quitregistlogin(HttpServletRequest request,HttpServletResponse response)
    {
        return quit(request,response);

    }

//课程信息
    @RequestMapping(value = "/home/class", method = RequestMethod.GET)
    public ModelAndView getAllCls() {
    List<Classinfo> cls = userdao.queryAllCls();
    return new ModelAndView("class","clsList",cls);
}


    @RequestMapping(value = "/home/class/add", method = RequestMethod.POST)
    public ModelAndView addCls(@RequestParam ("classname") String clsname,
                                  @RequestParam ("time") String time,
                                  @RequestParam ("coach") String coach){
        Classinfo cls=new Classinfo();
        cls.setClassname(clsname);
        cls.setTime(time);
        cls.setCoach(coach);
        getAllCls();
        userdao.addCls(cls);
        return new ModelAndView("redirect:/home/class");
    }

    @RequestMapping(value = "/home/class/del", method = RequestMethod.POST)
    public ModelAndView delCls(@RequestParam ("classname") String clsname) {
        Classinfo cls=new Classinfo();
        cls.setClassname(clsname);
        userdao.delCls(cls);
        getAllCls();

        return new ModelAndView("redirect:/home/class");
    }


    @RequestMapping("/home/class/quit")
    public ModelAndView quitcls(HttpServletRequest request,HttpServletResponse response)
    {
       return quit(request,response);

    }

//顾客信息
    @RequestMapping(value = "/home/customer", method = RequestMethod.GET)
    public ModelAndView getAllCus() {
    List<Customer> cus = userdao.queryAllCus();
    return new ModelAndView("customer","cusList",cus);
}


    @RequestMapping(value = "/home/customer/add", method = RequestMethod.POST)
    public ModelAndView addCus(@RequestParam ("customer") String cusname,
                               @RequestParam ("coach") String coach){
        Customer cus=new Customer();
        cus.setCustomer(cusname);
        cus.setCoach(coach);
        getAllCus();
        userdao.addCus(cus);
        return new ModelAndView("redirect:/home/customer");
    }

    @RequestMapping(value = "/home/customer/del", method = RequestMethod.POST)
    public ModelAndView delCus(@RequestParam ("classname") String clsname) {
        Classinfo cls=new Classinfo();
        cls.setClassname(clsname);
        userdao.delCls(cls);
        getAllCls();

        return new ModelAndView("redirect:/home/customer");
    }


    @RequestMapping("/home/customer/quit")
    public ModelAndView quitcus(HttpServletRequest request,HttpServletResponse response)
    {
        return quit(request,response);

    }

//登录
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView start()
    {
        return new ModelAndView("index");

    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request,
                              HttpServletResponse response,
                              @RequestParam ("loginname") String username,
                              @RequestParam ("password") String password)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {

        if(verify(username,password))//做一些数据库验证
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

            return new ModelAndView("redirect:/home");

        }
        else {
            return new ModelAndView("index");
        }

    }


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
                                   HttpServletResponse response
    ) throws NoSuchAlgorithmException, IOException {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(pwd);
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pwd.getBytes("UTF8"));
        byte resultData[] = md.digest();
        String strresult=convertToHexString(resultData);
        user.setPassword(strresult);
        userdao.addUser(user);
        PrintWriter out = response.getWriter();
        out.println("注册成功！");
        return new ModelAndView("redirect:/");
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
