package com.magfin.ccs.webccs.service;

import com.magfin.ccs.webccs.dao.UsrUserMapper;
import com.magfin.ccs.webccs.model.UsrUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Time 2019/4/27
 * @Author zlian
 */
@Service
public class UsrUserService {
    @Autowired
    UsrUserMapper usrUserMapper;

    @Autowired
//    WebSecurityConfig里配置的密码加解密方法
    private PasswordEncoder passwordEncoder;

    public List<UsrUser> queryListByEntity(UsrUser usrUser){
        List<UsrUser> userList = new ArrayList<>();
        userList = usrUserMapper.queryListByEntity(usrUser);
        return userList;
    }

    public UsrUser selectByPrimaryKey(String userCode){
        UsrUser usrUser = usrUserMapper.selectByPrimaryKey(userCode);
        return usrUser;
    }

    public boolean insertOrUpdateUser(UsrUser usrUser){
        //加密密码
        String encodePassword = passwordEncoder.encode(usrUser.getPassword());
        usrUser.setPassword(encodePassword);
        int result = usrUserMapper.updateByPrimaryKeySelective(usrUser);
        if(result<1){//更新小于0，开始插入
            result = usrUserMapper.insert(usrUser);
        }
        return result>0?true:false;
    }
}
