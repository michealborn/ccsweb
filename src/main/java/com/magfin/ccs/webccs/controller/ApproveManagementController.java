package com.magfin.ccs.webccs.controller;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        ProcessDefinitionQuery processDefinitionQuery = processEngine.getRepositoryService().createProcessDefinitionQuery();
        List<ProcessDefinition> list = processDefinitionQuery.list();
        for(ProcessDefinition p:list){
            System.out.println(p.getName());
        }
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
