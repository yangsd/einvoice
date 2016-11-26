package com.management.mapper;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.management.model.EinvoiceBody;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by sdyang on 2016/10/23.
 */
public interface EinvoiceBodyMapper extends AutoMapper<EinvoiceBody> {

    List<EinvoiceBody> selectListByHeadId(Pagination page, Map<String, Object> params);
}
