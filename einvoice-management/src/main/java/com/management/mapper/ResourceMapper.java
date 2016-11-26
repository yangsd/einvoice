package com.management.mapper;

import java.util.List;

import com.management.model.Resource;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;

/**
 *
 * Resource 表数据库控制层接口
 *
 */
public interface ResourceMapper extends AutoMapper<Resource> {

    List<Resource> selectAllByTypeAndPIdNull(@Param("resourceType") Integer resourceType);

    List<Resource> selectAllByTypeAndPId(@Param("resourceType") Integer resourceType, @Param("pId") Long pId);

    List<Resource> selectAll();

}