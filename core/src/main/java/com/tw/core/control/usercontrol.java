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
public class usercontrol {

@Autowired
    private UserDao userdao;

//主界面
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView getHome() {
        List<User> users = userdao.queryAllUsers();
        return new ModelAndView("home","userList",users);
    }

//用户管理
    @RequestMapping(value = "/home/user", method = RequestMethod.GET)
    public ModelAndView getUser() {
        List<User> users = userdao.queryAllUsers();

        return new ModelAndView("user","userList",users);
    }


    @RequestMapping(value="/home/user/{id}")
    public ModelAndView deluser(@PathVariable Integer id)
    {
        User user = new User();
        user.setIdUser(id);

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
                                  @RequestParam ("role") String role,
                                  @RequestParam ("num") String num
                                  ){
        Employee emp=new Employee();

        emp.setName(name);
        emp.setRole(role);
        emp.setIdEmployee(Integer.parseInt(num));

        userdao.addEmp(emp);
        getAllEmUser();
        return new ModelAndView("redirect:/home/regist_emp");
    }

    @RequestMapping(value = "/home/regist_emp/{id}")
    public ModelAndView delEmUser(@PathVariable Integer id) {

//
        Employee emp=new Employee();
//        User user=userdao.getBynum(User.class,id);
//        userdao.deleteUser(user);
//        Employee emp=new Employee();
        emp.setIdEmployee(id);
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
        ModelAndView modeandview=new ModelAndView();

        List<Classinfo> cls = userdao.queryAllCls();
        List<Employee> coach=userdao.queryCoach();
        modeandview.setViewName("class");
        modeandview.addObject("clsList", cls);
        modeandview.addObject("coach",coach);


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

    @RequestMapping(value = "/home/class/{id}")
    public ModelAndView delCls(@PathVariable Integer id) {
        Classinfo cls=new Classinfo();
        cls.setIdClass(id);
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

    @RequestMapping(value = "/home/customer/{id}")
    public ModelAndView delCus(@PathVariable Integer id) {
        Customer cus=new Customer();
        cus.setIdCustomer(id);
        userdao.delCus(cus);
        getAllCus();

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
            out.println("注册失败！");
            return new ModelAndView("registuser");
        }
    }

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
    public ModelAndView selectCoach(@RequestParam ("coach")String coach){
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
        modeandview.addObject("clsList",tpricls);
        modeandview.addObject("coach",tcoach);
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

    @RequestMapping("/home/private/quit")
    public ModelAndView quitprivate(HttpServletRequest request,HttpServletResponse response)
    {
        return quit(request,response);

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
