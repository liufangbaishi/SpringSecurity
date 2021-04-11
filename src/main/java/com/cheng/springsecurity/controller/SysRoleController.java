package com.cheng.springsecurity.controller;


import com.cheng.springsecurity.entity.SysRole;
import com.cheng.springsecurity.response.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author wei
 * @since 2021-04-11
 */
@CrossOrigin
@RestController
@RequestMapping("/role")
@Api(value = "系统角色模块", tags = "系统角色接口")
public class SysRoleController {

    @ApiOperation(value = "角色授权")
    @PreAuthorize("hasAuthority('role:authority')")
    @PostMapping("/authority/{id}")
    public Result authority(@PathVariable Long id, @RequestBody Long[] mids){

        return Result.ok();
    }

    /**
     * 角色拥有的菜单权限id和菜单树
     * @return
     */
    @ApiOperation(value = "角色菜单")
    @GetMapping("/findRoleMenu/{id}")
    public Result findRoleMenu(){


        return Result.ok();
    }

    /**
     * 分页查询角色列表
     *
     * @return
     */
    @ApiOperation(value = "角色列表",notes = "分页查询角色列表信息")
    @GetMapping("/findRoleList")
    public Result findRoleList(@RequestParam(value = "current", defaultValue = "1") Integer current,
                               @RequestParam(value = "size", defaultValue = "7") Integer size,
                               SysRole sysRole){

        return Result.ok();
    }

    /**
     * 添加角色信息
     *
     * @param sysRole
     * @return
     */
    @ApiOperation(value = "添加角色")
    @PreAuthorize("hasAuthority('role:add')")
    @PostMapping("/add")
    public Result add(@RequestBody SysRole sysRole) {
        return Result.ok();
    }

    /**
     * 删除角色
     *
     * @param id 角色ID
     * @return
     */
    @ApiOperation(value = "删除角色", notes = "根据id删除角色信息")
    @PreAuthorize("hasAuthority('role:delete')")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        return Result.ok();
    }

    /**
     * 编辑角色信息(根据角色ID查询角色信息)
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "编辑角色", notes = "根据id更新角色信息")
    @PreAuthorize("hasAuthority('role:edit')")
    @GetMapping("/edit/{id}")
    public Result edit(@PathVariable Long id) {
        return Result.ok();
    }

    /**
     * 更新角色
     *
     * @param id
     * @param sysRole
     * @return
     */
    @ApiOperation(value = "更新角色", notes = "根据id更新角色信息")
    @PreAuthorize("hasAuthority('role:update')")
    @PutMapping("/update/{id}")
    public Result update(@PathVariable Long id, @RequestBody SysRole sysRole) {
        return Result.ok();
    }

    /**
     * 更新角色状态
     *
     * @param id
     * @param status
     * @return
     */
    @ApiOperation(value = "更新状态", notes = "禁用和更新两种状态")
    @PreAuthorize("hasAuthority('role:status')")
    @PutMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id, @PathVariable Boolean status) {
        return Result.ok();
    }

    /**
     * 导出excel
     * @param response
     */
    @ApiOperation(value = "导出excel", notes = "导出所有角色的excel表格")
    @PostMapping("/excel")
    @PreAuthorize("hasAuthority('role:export')")
    public Result export(HttpServletResponse response) {
        return Result.ok();
    }
}



