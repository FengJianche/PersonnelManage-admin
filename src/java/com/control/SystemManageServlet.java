package com.control;

import com.entity.Department;
import com.entity.Post;
import com.entity.StaffInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.ProbationManageService;
import com.service.SystemManageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/system")
public class SystemManageServlet {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    SystemManageService systemManageService = (SystemManageService) context.getBean("SystemManageService");


    @RequestMapping(value = "/alldepartmentpostinfo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ModelAndView allDepartmentInfo(Model model) throws JsonProcessingException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("systemmanage/departmentPostManage");
        mav.addObject("alldepartmentpost",systemManageService.allDepartmentPost());
        return mav;
    }

    @RequestMapping(value = "/staffinpost",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<StaffInfo> staffInPost(@RequestParam("postName") String postName) throws JsonProcessingException {
        List<StaffInfo> staffInfos = systemManageService.getPostStaff(postName);
        return staffInfos;
    }

    @RequestMapping(value = "/uppostinfo",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String upPostInfo(@RequestParam("id") String id,@RequestParam("name") String name,@RequestParam("department_id") String department_id,@RequestParam("old_post") String old_post,@RequestParam("old_department") String old_department,@RequestParam("new_department_name") String new_department_name) throws JsonProcessingException, ParseException {
        //封装post
        Post post = new Post();
        post.setId(id);
        post.setName(name);
        post.setDepartment_id(department_id);
        if(systemManageService.upPostInfo(post,old_post,old_department,new_department_name))
            return "ok";
        else
            return "error";
    }

    @RequestMapping(value = "/departmentinfo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<Department> upPostInfo() throws JsonProcessingException, ParseException {
        List<Department> departments = systemManageService.departmentInfo();
        return departments;
    }

    @RequestMapping(value = "/adddepartment",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addDepartment(@RequestParam("name") String name) throws JsonProcessingException, ParseException {

        if(systemManageService.addDepartment(name))
            return "ok";
        else
            return "error";
    }

    @RequestMapping(value = "/addpost",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addPost(@RequestParam("name") String name,@RequestParam("department_id") String department_id) throws JsonProcessingException, ParseException {
        if(systemManageService.addPost(name,department_id))
            return "ok";
        else
            return "error";
    }

    @RequestMapping(value = "/deldepartment",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String delDepartment(@RequestParam("department_id") String department_id) throws JsonProcessingException, ParseException {
        if(systemManageService.delDepartment(department_id))
            return "ok";
        else
            return "error";
    }
    @RequestMapping(value = "/delpost",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String delPost(@RequestParam("post") String post) throws JsonProcessingException, ParseException {
        if(systemManageService.delPost(post))
            return "ok";
        else
            return "error";
    }
}