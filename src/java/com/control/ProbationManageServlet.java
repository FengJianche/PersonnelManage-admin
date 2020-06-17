package com.control;

import com.entity.Probation;
import com.service.ProbationManageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Controller
@RequestMapping("/probationmanage")
public class ProbationManageServlet {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    ProbationManageService probationManageService = (ProbationManageService) context.getBean("ProbationManageService");

    @RequestMapping(value = "/probationreturn",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String probationReturn(@RequestParam("id") String id,@RequestParam("staff_status") String staff_status) throws ParseException {
        if(probationManageService.probationReturn(id,staff_status))
            return "ok";
        else
            return "replace";
    }

    @RequestMapping(value = "/probationcontinue",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String probationContinue(@RequestParam("staff_id") String staff_id,@RequestParam("begintime") String begintime,@RequestParam("endtime") String endtime) throws ParseException {
        if(probationManageService.upProbationInfo(staff_id,begintime,endtime))
            return "ok";
        else
            return "replace";
    }

    @RequestMapping(value = "/refuseprobation",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String refuseProbation(@RequestParam("id") String id) throws ParseException {
        if(probationManageService.refuseProbation(id))
            return "ok";
        else
            return "replace";
    }
}