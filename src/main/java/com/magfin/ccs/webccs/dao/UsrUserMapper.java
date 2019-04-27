package com.magfin.ccs.webccs.dao;

import com.magfin.ccs.webccs.model.UsrUser;

import java.util.List;

public interface UsrUserMapper {
    int deleteByPrimaryKey(String userCode);

    int insert(UsrUser record);

    int insertSelective(UsrUser record);

    UsrUser selectByPrimaryKey(String userCode);

    int updateByPrimaryKeySelective(UsrUser record);

    int updateByPrimaryKey(UsrUser record);

    List<UsrUser> queryListByEntity(UsrUser record);
}