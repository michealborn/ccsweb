package com.magfin.ccs.webccs.dao;

import com.magfin.ccs.webccs.model.UsrRoleResource;

public interface UsrRoleResourceMapper {
    int deleteByPrimaryKey(String id);

    int insert(UsrRoleResource record);

    int insertSelective(UsrRoleResource record);

    UsrRoleResource selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UsrRoleResource record);

    int updateByPrimaryKey(UsrRoleResource record);
}