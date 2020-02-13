package com.atguigu.guli.service.edu.controller;



import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.service.edu.entity.Teacher;
import com.atguigu.guli.service.edu.entity.vo.TeacherQueryVo;
import com.atguigu.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2020-02-13
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value="所有讲师列表",notes="返回所有讲师列表，。。。")
    @GetMapping("list")
    public R listAll(){
         List<Teacher> list = teacherService.list(null);
         return R.ok().data("items",list).message("讲师列表获取成功");
    }

    @ApiOperation(value="根据id删除讲师",notes="根据id删除讲师，逻辑删除。。。")
    @DeleteMapping("remove/{id}")
    public R removeById(
            @ApiParam(name = "id",value = "讲师id",required = true)
            @PathVariable String id){
        boolean b = teacherService.removeById(id);
        return R.ok();
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("list/{page}/{limit}")
    public R index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherQueryVo", value = "讲师查询对象", required = false)
            TeacherQueryVo teacherQueryVo){

        Page<Teacher> pageParam = new Page<>(page, limit);
        IPage<Teacher> pageModel = teacherService.selectPage(pageParam, teacherQueryVo);

        List<Teacher> records = pageModel.getRecords();
        long total = pageModel.getTotal();

        return  R.ok().data("total", total).data("rows", records).message("获取讲师分页列表成功");
    }

}

