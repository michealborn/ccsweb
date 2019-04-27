package com.magfin.ccs.webccs.dao;

import com.magfin.ccs.webccs.model.UsrRole;

public interface UsrRoleMapper {
    int deleteByPrimaryKey(String roleCode);

    int insert(UsrRole record);

    int insertSelective(UsrRole record);

    UsrRole selectByPrimaryKey(String roleCode);

    int updateByPrimaryKeySelective(UsrRole record);

    int updateByPrimaryKey(UsrRole record);
}