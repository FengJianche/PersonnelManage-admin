package com.control;

import com.entity.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.StaffInfoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/staffinfo")
public class StaffInfoServlet {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    StaffInfoService staffInfoService=(StaffInfoService)context.getBean("StaffInfoService");


    @RequestMapping(value = "/allstaff",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ModelAndView allStaff(Model model) throws JsonProcessingException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("staffinfomanage/data");
        mav.addObject("allstaffinfo",staffInfoService.allStaffInfo());
        System.out.println(mav);
        return mav;
}

    @RequestMapping(value = "/getstaffinfo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String UpdateStaffInfo(@RequestParam("id") String id) throws JsonProcessingException {
        //创建一个jackson的对象映射器，用来解析数据
        ObjectMapper mapper = new ObjectMapper();
        //创建一个对象
        StaffInfo staffInfo = new StaffInfo();
        staffInfo = staffInfoService.getOneStaffInfo(id);
        //将我们的对象解析成为json格式
        String result = mapper.writeValueAsString(staffInfo);
        //由于@ResponseBody注解，这里会将str转成json格式返回；十分方便
        return result;
    }

    @RequestMapping(value = "/upstaffinfo",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getOneStaff(@RequestParam("id") String id,@RequestParam("name") String name,@RequestParam("sex") String sex,@RequestParam("idnumber") String idnumber,@RequestParam("address") String address,@RequestParam("worktime") String worktime) throws JsonProcessingException, ParseException {
        if(staffInfoService.UpdateStaffInfo(id,name,sex,idnumber,address,worktime))
            return "ok";
        else
            return "replace";
    }

    @RequestMapping(value = "/getonefiles",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ModelAndView  getStaffFiles(@RequestParam("id") String id) throws JsonProcessingException, ParseException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("staffinfomanage/staffprofile.html");
        StaffInfo_Files staffInfo_files = new StaffInfo_Files();
        staffInfo_files = staffInfoService.getOneStaffFiles(id);
        if(staffInfo_files==null){
            StaffInfo_Files staffInfo_files_n = new StaffInfo_Files();
            StaffInfo staffInfo = new StaffInfo();
            staffInfo = staffInfoService.getOneStaffInfo(id);
            staffInfo_files_n.setId(id);
            staffInfo_files_n.setName(staffInfo.getName());
            staffInfo_files_n.setSex(staffInfo.getSex());
            staffInfo_files_n.setIdnumber(staffInfo.getIdnumber());
            staffInfo_files_n.setAddress(staffInfo.getAddress());
            staffInfo_files_n.setDepartment(staffInfo.getDepartment());
            staffInfo_files_n.setPost(staffInfo.getPost());
            staffInfo_files_n.setTechpost(staffInfo.getTechpost());
            staffInfo_files_n.setWorktime(staffInfo.getWorktime());
            staffInfo_files_n.setStaff_status(staffInfo.getStaff_status());
            staffInfo_files_n.setPolitical_status("@system_none_flag");
            staffInfo_files_n.setCareer("none");
            staffInfo_files_n.setLanguage("none");
            staffInfo_files_n.setFamily("none");
            staffInfo_files_n.setSocial("none");
            staffInfo_files_n.setIfexist(0);
            staffInfo_files_n.setEducation("none");
            mav.addObject("staffinfofiles",staffInfo_files_n);
        }else
        mav.addObject("staffinfofiles",staffInfo_files);
        System.out.println(mav);
        return mav;
    }

    @RequestMapping(value = "/addstafffiles",method = RequestMethod.POST,produces = "application/json;charset=utf-8")

    public String addStaffFiles(@RequestParam("id") String id, StaffFiles staffFiles) throws JsonProcessingException, ParseException {
        staffFiles.setStaff_id(id);
        staffFiles.setIfexist(1);
        //新增
        staffInfoService.addStaffFiles(staffFiles);
        return "redirect:/staffinfo/getonefiles?id="+id;
    }


    @RequestMapping(value = "/searchstaff",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ModelAndView advancedSearch(@RequestParam("id") String id,@RequestParam("name") String name,@RequestParam("department") String department,@RequestParam("post") String post,@RequestParam("sex") String sex,@RequestParam("staff_status") String staff_status) throws JsonProcessingException, ParseException {
        ModelAndView mav = new ModelAndView();
        List<StaffInfo> staffInfos = staffInfoService.searchStaffInfo(id,name,department,post,sex,staff_status);
        mav.setViewName("staffinfomanage/data");
        mav.addObject("allstaffinfo",staffInfos);
        System.out.println(mav);
        return mav;
    }


    @RequestMapping(value = "/getalldepartment",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<String> getAllDepartmentName() throws JsonProcessingException {
        //创建一个jackson的对象映射器，用来解析数据
        List<String> result = staffInfoService.getAllDepartmentName();
        //由于@ResponseBody注解，这里会将str转成json格式返回；十分方便
        return result;
    }

    @RequestMapping(value = "/getallpost",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<String> getAllPostName() throws JsonProcessingException {
        //创建一个jackson的对象映射器，用来解析数据
        List<String> result = staffInfoService.getAllPostName();
        //由于@ResponseBody注解，这里会将str转成json格式返回；十分方便
        return result;
    }

    @RequestMapping(value = "/alldepartmentinfo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ModelAndView allDepartmentInfo(Model model) throws JsonProcessingException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("staffOnboarding/onboarding");
        mav.addObject("alldepartmentinfo",staffInfoService.getAllDepartmentInfo());
        System.out.println(mav);
        return mav;
    }

    @RequestMapping(value = "/allpostinfo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<Post> getAllPostName(@RequestParam("department_id") String department_id) throws JsonProcessingException {
        //创建一个jackson的对象映射器，用来解析数据
        List<Post> result = staffInfoService.getAllPostInfo(department_id);
        //由于@ResponseBody注解，这里会将str转成json格式返回；十分方便
        return result;
    }

    @RequestMapping(value = "/addstaffinfo",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addStaffInfo(StaffInfo staffInfo,@RequestParam("probation") String probation) throws JsonProcessingException, ParseException {
        System.out.println(probation);
        if(staffInfoService.addStaffInfo(staffInfo,probation))
            return "ok";
        else
            return "replace";
    }

    @RequestMapping(value = "/allprobation",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ModelAndView allProbation(Model model) throws JsonProcessingException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("staffOnboarding/probation");
        mav.addObject("allprobationinfo",staffInfoService.allProbation());
        System.out.println(mav);
        return mav;
    }



}
