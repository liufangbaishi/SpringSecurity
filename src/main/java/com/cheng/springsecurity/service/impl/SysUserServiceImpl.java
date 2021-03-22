package com.cheng.springsecurity.service.impl;

import com.cheng.springsecurity.entity.SysUser;
import com.cheng.springsecurity.mapper.SysUserMapper;
import com.cheng.springsecurity.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-03-22
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
