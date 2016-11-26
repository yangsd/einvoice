package com.management.mapper;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.management.model.EinvoiceHead;
import com.management.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by sdyang on 2016/10/23.
 */
public interface EinvoiceHeadMapper extends AutoMapper<EinvoiceHead> {

    List<EinvoiceHead> selectHeadList(Pagination page, Map<String, Object> params);
}
