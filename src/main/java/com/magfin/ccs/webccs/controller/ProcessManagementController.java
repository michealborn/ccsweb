package com.magfin.ccs.webccs.controller;

import com.magfin.ccs.webccs.model.UsrUser;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Time 2019/5/4
 * @Author zlian
 */
@Controller
@RequestMapping("/processManagement")
public class ProcessManagementController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/deployProcess")
    @ResponseBody
    public String deployProcess(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        DeploymentBuilder deployment = processEngine.getRepositoryService().createDeployment();
        //方式一：读取单个的流程定义文件
        deployment.addClasspathResource("processes/firstProcess.bpmn");
        deployment.addClasspathResource("processes/firstProcess.png");
        Deployment deploy = deployment.deploy();
        return deploy.getId();
    }

    //流程列表
    @RequestMapping("/gotoProcessList")
    public String gotoProcessList(Model model, HttpServletRequest request){
        //获取流程接口
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //流程定义查询接口
        ProcessDefinitionQuery processDefinitionQuery = processEngine.getRepositoryService().createProcessDefinitionQuery();
        //流程定义列表
        List<ProcessDefinition> list = processDefinitionQuery.list();
        model.addAttribute("processList",list);
        return "/processManagement/processList";
    }

    @RequestMapping("/readyProcess")
    public String readyProcess(Model model, HttpServletRequest request,String processKey){
        model.addAttribute("processKey",processKey);
        return "/processManagement/readyProcess";
    }

    @RequestMapping("/runProcess")
    public String runProcess(Model model, Authentication authentication, HttpServletRequest request, String processKey, String result, String comment){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //设置客户经理开启流程的变量
        Map<String, Object> variables1 = new HashMap<>();
        UsrUser usrUser = new UsrUser();
        String username = ((User) authentication.getPrincipal()).getUsername();
        usrUser.setUserCode(username);
        variables1.put("khjl",usrUser);
        //启动流程，并放入流程变量
        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey(processKey,variables1);
        //根据流程实例id和用户定位唯一一笔任务
        TaskQuery taskQuery = processEngine.getTaskService().createTaskQuery();
        Task task = taskQuery.processInstanceId(pi.getId()).taskAssignee(username).singleResult();
        //执行该任务并放入下一审批人的流程变量
        Map<String, Object> variables2 = new HashMap<>();
        usrUser.setUserCode("0001");
        variables2.put("cgsfzr",usrUser);
        processEngine.getTaskService().complete(task.getId(),variables2);
        logger.info(pi.getName()+"流程启动，实例id："+pi.getId());
        return "redirect:/processManagement/gotoProcessList";
    }
}
/*
 * assignee #{}
 *
 */
