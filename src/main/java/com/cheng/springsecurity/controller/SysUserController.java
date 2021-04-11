package com.cheng.springsecurity.controller;


import com.cheng.springsecurity.entity.SysUser;
import com.cheng.springsecurity.response.Result;
import com.cheng.springsecurity.service.SysRoleService;
import com.cheng.springsecurity.service.SysUserService;
import com.cheng.springsecurity.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wei
 * @since 2021-04-11
 */
@CrossOrigin
@RestController
@RequestMapping("/users")
@Api(value = "系统用户模块", tags = "系统用户接口")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 获取用户列表
     * @return
     */
    @ApiOperation(value = "用户列表", notes = "模糊查询用户列表")
    @PostMapping("/getUserList")
    public Result getUserList(@RequestParam(value = "current", defaultValue = "1") Integer current,
                              @RequestParam(value = "size", defaultValue = "7") Integer size,
                              @RequestBody UserVo userVO){
        return Result.ok();
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @ApiOperation(value = "用户信息", notes = "用户登入信息")
    @GetMapping("/getUserInfo")
    public Result info() {
        return Result.ok();
    }

    /**
     * 分配角色
     *
     * @param id 用户id
     * @param rids 角色id的数组
     * @return
     */
    @ApiOperation(value = "分配角色", notes = "给用户分配角色")
    @PreAuthorize("hasAuthority('user:assign')")
    @PostMapping("/assignRoles/{id}")
    public Result assignRoles(@PathVariable Long id, @RequestBody Long[] rids) {
        return Result.ok();
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return
     */
    @PreAuthorize("hasAuthority('user:delete')")
    @ApiOperation(value = "删除用户", notes = "删除用户信息，根据用户ID")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {

        return Result.ok();
    }

    /**
     * 更新状态
     *
     * @param id
     * @param status
     * @return
     */
    @ApiOperation(value = "用户状态", notes = "禁用和启用这两种状态")
    @PreAuthorize("hasAuthority('user:status')")
    @PutMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id, @PathVariable Boolean status) {
        return Result.ok();
    }

    /**
     * 更新用户
     *
     * @param id
     * @param sysUser
     * @return
     */
    @ApiOperation(value = "更新用户", notes = "更新用户信息")
    @PreAuthorize("hasAuthority('user:update')")
    @PutMapping("/update/{id}")
    public Result update(@PathVariable Long id, @RequestBody SysUser sysUser) {
        return Result.ok();
    }

    /**
     * 查询单个用户信息
     * @param id
     * @return
     */
    @ApiOperation(value = "编辑用户", notes = "获取用户的详情，编辑用户信息")
    @PreAuthorize("hasAuthority('user:edit')")
    @GetMapping("/edit/{id}")
    public Result edit(@PathVariable Long id) {
        return Result.ok();
    }

    /**
     * 添加用户信息
     * @param sysUser
     * @return
     */
    @ApiOperation(value = "添加用户", notes = "添加用户信息")
    //@PreAuthorize("hasAuthority('user:add')")
    @PostMapping("/add")
    public Result add(@RequestBody SysUser sysUser) {
        return Result.ok();
    }

    /**
     * 用户角色信息
     * @param id 用户ID
     * @return
     */
    @ApiOperation(value = "已有角色", notes = "根据用户id，获取用户已经拥有的角色")
    @GetMapping("/roles/{id}")
    public Result roles(@PathVariable Long id) {

        return Result.ok();
    }

    /**
     * 导出excel
     * @param response
     */
    @ApiOperation(value = "导出excel", notes = "导出所有用户的excel表格")
    @PreAuthorize("hasAuthority('user:export')")
    @PostMapping("/excel")
    public Result export(HttpServletResponse response) {
        return Result.ok();
    }
}

