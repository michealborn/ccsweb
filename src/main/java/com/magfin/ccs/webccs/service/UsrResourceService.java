package com.magfin.ccs.webccs.service;

import com.magfin.ccs.webccs.dao.UsrResourceMapper;
import com.magfin.ccs.webccs.model.UsrResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsrResourceService {

    @Autowired
    private UsrResourceMapper usrResourceMapper;


    public List<UsrResource> queryListByObject(UsrResource usrResource){
//        List<UsrResource> usrResources = usrResourceMapper.queryListByObject(usrResource);
        return null;
    }
}