package com.magfin.ccs.webccs.controller;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Time 2019/5/4
 * @Author zlian
 */
@Controller
@RequestMapping("/processManagement")
public class ProcessManagementController {
    private Logger logger = LoggerFactory.getLogger(getClass());

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
    public String runProcess(Model model, HttpServletRequest request,String processKey,String comment){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        ProcessInstance ywsq = processEngine.getRuntimeService().startProcessInstanceByKey("ywsq");
        logger.debug(ywsq.getName()+"流程启动，实例id："+ywsq.getId());
        return "/processManagement/processList";
    }
}
