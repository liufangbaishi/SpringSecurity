package com.cheng.springsecurity.mapper;

import com.cheng.springsecurity.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-03-22
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<String> getRoleCodeByUserName(String username);
}
