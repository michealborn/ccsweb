package com.magfin.ccs.webccs.dao;

import com.magfin.ccs.webccs.model.UsrResource;

public interface UsrResourceMapper {
    int deleteByPrimaryKey(String resCode);

    int insert(UsrResource record);

    int insertSelective(UsrResource record);

    UsrResource selectByPrimaryKey(String resCode);

    int updateByPrimaryKeySelective(UsrResource record);

    int updateByPrimaryKey(UsrResource record);
}