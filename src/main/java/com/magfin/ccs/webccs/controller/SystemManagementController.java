package com.magfin.ccs.webccs.controller;

import com.magfin.ccs.webccs.model.UsrUser;
import com.magfin.ccs.webccs.service.UsrUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/systemManagement")
public class SystemManagementController {
    @Autowired
    UsrUserService usrUserService;

    //用户管理
    @RequestMapping("/user")
    public String user(Model model, HttpServletRequest request){
        List<UsrUser> usrUsers = usrUserService.queryListByEntity(new UsrUser());
        model.addAttribute("usrUsers",usrUsers);
        return "/systemManagement/user/user";
    }

    //用户管理
    @RequestMapping("/gotoInsertOrUpdateUser")
    public String gotoInsertOrUpdateUser(Model model, UsrUser user){
        if(user!=null){
            user = usrUserService.selectByPrimaryKey(user.getUserCode());
        }
        model.addAttribute("usrUser",user);
        return "/systemManagement/user/userDetail";
    }

    //用户管理
    @RequestMapping("/insertOrUpdateUser")
    public String insertOrUpdateUser(Model model, UsrUser user){
        usrUserService.insertOrUpdateUser(user);
        return "redirect:/systemManagement/user";
    }

    //角色管理
    @RequestMapping("/role")
    public String role(Model model, HttpServletRequest request){
        return "/systemManagement/role";
    }

    //菜单管理
    @RequestMapping("/resources")
    public String resources(Model model, HttpServletRequest request){
        return "/systemManagement/resources";
    }
}
