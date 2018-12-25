package com.xz.platform.mapper.auto;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xz.platform.model.auto.TestGen;
import com.xz.platform.model.auto.TestGenExample;

public interface TestGenMapper {
    long countByExample(TestGenExample example);

    int deleteByExample(TestGenExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TestGen record);

    int insertSelective(TestGen record);

    List<TestGen> selectByExample(TestGenExample example);

    TestGen selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TestGen record, @Param("example") TestGenExample example);

    int updateByExample(@Param("record") TestGen record, @Param("example") TestGenExample example);

    int updateByPrimaryKeySelective(TestGen record);

    int updateByPrimaryKey(TestGen record);
}