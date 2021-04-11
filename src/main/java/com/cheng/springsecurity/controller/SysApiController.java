package com.cheng.springsecurity.controller;


import com.cheng.springsecurity.entity.SysApi;
import com.cheng.springsecurity.response.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 后台接口表 前端控制器
 * </p>
 *
 * @author wei
 * @since 2021-04-11
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
@Api(value = "系统API模块", tags = "API权限接口")
public class SysApiController {

    /**
     * 加载菜单树
     *
     * @return
     */
    @ApiOperation(value = "加载API树", notes = "获取所有菜单树，以及展开项")
    @GetMapping("/getTree")
    public Result tree() {
        return Result.ok();
    }

    /**
     * 新增菜单/按钮
     *
     * @return
     */
    @ApiOperation(value = "新增API")
    @PreAuthorize("hasAuthority('api:add')")
    @PostMapping("/addApi")
    public Result addApi(@RequestBody SysApi sysApi) {
        return Result.ok();
    }

    /**
     * 删除菜单/按钮
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除API", notes = "根据id删除API节点")
    @PreAuthorize("hasAuthority('api:delete')")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        return Result.ok();
    }

    /**
     * 根据id编辑菜单，获取菜单详情
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "API详情", notes = "根据id编辑API，获取API详情")
    @PreAuthorize("hasAuthority('api:edit')")
    @GetMapping("/edit/{id}")
    public Result edit(@PathVariable Long id) {
        return Result.ok();
    }

    /**
     * 更新菜单
     *
     * @param id
     * @param sysApi
     * @return
     */
    @ApiOperation(value = "更新API", notes = "根据id更新API节点")
    @PreAuthorize("hasAuthority('api:update')")
    @PutMapping("/update/{id}")
    public Result update(@PathVariable Long id, @RequestBody SysApi sysApi) {
        return Result.ok();
    }

    /**
     * 导出excel
     * @param response
     */
    @ApiOperation(value = "导出excel", notes = "导出所有API的excel表格")
    @PreAuthorize("hasAuthority('api:export')")
    @PostMapping("excel")
    public Result export(HttpServletResponse response) {
        return Result.ok();
    }
}

