package com.magfin.ccs.webccs.controller;

import com.magfin.ccs.webccs.model.UsrResource;
import com.magfin.ccs.webccs.service.UsrResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
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
public class LoginController {

    @Autowired
    UsrResourceService usrResourceService;

    @RequestMapping("/goLogin")
    public String goLogin(Model model, HttpServletRequest request){
        RuntimeException ex = (RuntimeException)request.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        //如果已经登陆跳转到个人首页
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null
                &&!authentication.getPrincipal().equals("anonymousUser")
                &&authentication.isAuthenticated()){
            return "redirct:/index";
        }
        if(ex!=null){
            model.addAttribute("error",ex.getMessage());
        }
        return "login";
    }

    @RequestMapping("/index")
    public String hello(Model model, HttpServletRequest request){
        return "index";
    }

    @RequestMapping("/signOut")
    public String signOut(Model model, HttpServletRequest request){
        String logout = request.getParameter("logout");
        System.out.println("登出结果:"+logout);
        return "login";
    }


}
