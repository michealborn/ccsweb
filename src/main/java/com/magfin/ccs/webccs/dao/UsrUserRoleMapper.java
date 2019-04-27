package com.magfin.ccs.webccs.dao;

import com.magfin.ccs.webccs.model.UsrUserRole;

public interface UsrUserRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(UsrUserRole record);

    int insertSelective(UsrUserRole record);

    UsrUserRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UsrUserRole record);

    int updateByPrimaryKey(UsrUserRole record);
}