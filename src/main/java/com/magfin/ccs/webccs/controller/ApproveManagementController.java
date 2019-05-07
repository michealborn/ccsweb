package com.magfin.ccs.webccs.controller;

import com.magfin.ccs.webccs.model.UsrUser;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Time 2019/4/27
 * @Author zlian
 */
@Controller
@RequestMapping("/approveManagement")
public class ApproveManagementController {

    //待办事项
    @RequestMapping("/backlog")
    public String backlog(Model model, Authentication authentication, HttpServletRequest request){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskQuery taskQuery = processEngine.getTaskService().createTaskQuery();
        User principal = (User) authentication.getPrincipal();
        taskQuery.taskAssignee(principal.getUsername());
        taskQuery.listPage(0,10);
        List<Task> list = taskQuery.list();
        model.addAttribute("taskList",list);
        return "/approveManagement/backlog";
    }

    //已办事项
    @RequestMapping("/completeProcessByTaskId")
    public String completeProcessByTaskId(Model model, HttpServletRequest request, String taskId){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        Map<String, Object> variables1 = new HashMap<>();
        UsrUser usrUser = new UsrUser();
        usrUser.setUserCode("0002");
        variables1.put("ywzj",usrUser);
        processEngine.getTaskService().complete(taskId,variables1);
        return "redirect:/approveManagement/backlog";
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
