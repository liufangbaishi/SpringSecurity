package com.cheng.springsecurity.controller;


import com.cheng.springsecurity.entity.SysMenu;
import com.cheng.springsecurity.response.Result;
import com.cheng.springsecurity.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author wei
 * @since 2021-04-11
 */
@CrossOrigin
@RestController
@RequestMapping("/menu")
@Api(value = "系统菜单模块", tags = "菜单权限接口")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 加载菜单树
     *
     * @return
     */
    @ApiOperation(value = "加载菜单树", notes = "获取所有菜单树，以及展开项")
    @GetMapping("/getTree")
    public Result tree() {
        return Result.ok();
    }

    /**
     * 新增菜单/按钮
     *
     * @return
     */
    @ApiOperation(value = "新增菜单")
    @PreAuthorize("hasAuthority('menu:add')")
    @PostMapping("/addMenu")
    public Result addMenu(@RequestBody SysMenu sysMenu) {
        return Result.ok();
    }

    /**
     * 删除菜单/按钮
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除菜单", notes = "根据id删除菜单节点")
    @PreAuthorize("hasAuthority('menu:delete')")
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
    @ApiOperation(value = "菜单详情", notes = "根据id编辑菜单，获取菜单详情")
    @PreAuthorize("hasAuthority('menu:edit')")
    @GetMapping("/edit/{id}")
    public Result edit(@PathVariable Long id) {
        return Result.ok();
    }

    /**
     * 更新菜单
     *
     * @param id
     * @param sysMenu
     * @return
     */
    @ApiOperation(value = "更新菜单", notes = "根据id更新菜单节点")
    @PreAuthorize("hasAuthority('menu:update')")
    @PutMapping("/update/{id}")
    public Result update(@PathVariable Long id, @RequestBody SysMenu sysMenu) {
        return Result.ok();
    }

    /**
     * 导出excel
     * @param response
     */
    @ApiOperation(value = "导出excel", notes = "导出所有菜单的excel表格")
    @PreAuthorize("hasAuthority('menu:export')")
    @PostMapping("excel")
    public Result export(HttpServletResponse response) {
        return Result.ok();
    }
}

