package com.management.service;

import com.baomidou.framework.service.ISuperService;
import com.management.commons.utils.PageInfo;
import com.management.model.User;
import com.management.model.vo.UserVo;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IUserService extends ISuperService<User> {

    User selectByLoginName(String loginName);

    void insertByVo(UserVo userVo);

    UserVo selectVoById(Long id);

    void updateByVo(UserVo userVo);

    void updatePwdByUserId(Long userId, String md5Hex);

    void selectDataGrid(PageInfo pageInfo);

    void deleteUserById(Long id);
}