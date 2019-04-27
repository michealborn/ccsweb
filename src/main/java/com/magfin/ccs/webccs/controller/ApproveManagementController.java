package com.magfin.ccs.webccs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Time 2019/4/27
 * @Author zlian
 */
@Controller
@RequestMapping("/approveManagement")
public class ApproveManagementController {

    //待办事项
    @RequestMapping("/backlog")
    public String backlog(Model model, HttpServletRequest request){
        return "/approveManagement/backlog";
    }

    //已办事项
    @RequestMapping("/workingDone")
    public String workingDone(Model model, HttpServletRequest request){
        return "/approveManagement/workingDone";
    }

    //办结事项
    @RequestMapping("/doneProcess")
    public String doneProcess(Model model, HttpServletRequest request){
        return "/approveManagement/workingDone";
    }
}
