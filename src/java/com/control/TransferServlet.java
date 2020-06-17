package com.control;

import com.entity.StaffInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.ProbationManageService;
import com.service.TransferService;
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
@RequestMapping("/transfer")
public class TransferServlet {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    TransferService transferService = (TransferService) context.getBean("TransferService");

//    @RequestMapping(value = "/allstaff",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
//    @ResponseBody
//    public ModelAndView allStaff(Model model) throws JsonProcessingException {
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("transfer/stafftransfer");
//        mav.addObject("allstaffinfo",transferService.allStaffInfo());
//        System.out.println(mav);
//        return mav;
//    }

    @RequestMapping(value = "/alldepartmentinfo",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ModelAndView allDepartmentInfo(Model model) throws JsonProcessingException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("transfer/stafftransfer");
        mav.addObject("alldepartmentinfo",transferService.getAllDepartmentInfo());
        System.out.println(mav);
        return mav;
    }

    @RequestMapping(value = "/searchstaff",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<StaffInfo> advancedSearch(@RequestParam("id") String id,@RequestParam("name") String name,@RequestParam("department") String department,@RequestParam("post") String post,@RequestParam("sex") String sex,@RequestParam("staff_status") String staff_status) throws JsonProcessingException, ParseException {
        List<StaffInfo> staffInfos = transferService.searchStaffInfo(id,name,department,post,sex,staff_status);
        return staffInfos;
    }

    @RequestMapping(value = "/addtransferinfo",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addTransferInfo(@RequestParam("id_info") String id_info,@RequestParam("old_department") String old_department,@RequestParam("old_post") String old_post,@RequestParam("new_department") String new_department,@RequestParam("new_post") String new_post,@RequestParam("admin_id") String admin_id,@RequestParam("reason") String reason) throws JsonProcessingException, ParseException {
    transferService.StaffTransfer(id_info,old_department,old_post,new_department,new_post,admin_id,reason);
    return  "ok";
    }

    @RequestMapping(value = "/alltransferrecord",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ModelAndView allTransferRecord(Model model) throws JsonProcessingException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("transfer/transferrecord");
        mav.addObject("alltransferinfo",transferService.allTransferRecord());
        System.out.println(mav);
        return mav;
    }

    @RequestMapping(value = "/staffdimission",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public ModelAndView staffDimission(Model model) throws JsonProcessingException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("transfer/staffdimission");
        return mav;
    }

    @RequestMapping(value = "/adddimission",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addDimission(@RequestParam("id_info") String id_info,@RequestParam("old_department") String old_department,@RequestParam("old_post") String old_post,@RequestParam("dimission") String dimission,@RequestParam("admin_id") String admin_id,@RequestParam("reason") String reason) throws JsonProcessingException, ParseException {
        transferService.StaffDimission(id_info,old_department,old_post,dimission,admin_id,reason);
        return  "ok";
    }







}