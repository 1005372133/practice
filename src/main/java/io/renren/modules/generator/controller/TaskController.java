package io.renren.modules.generator.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.dao.SysUserTokenDao;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.entity.SysUserTokenEntity;
import io.renren.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.generator.entity.TaskEntity;
import io.renren.modules.generator.service.TaskService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import javax.servlet.http.HttpServletRequest;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-12-22 10:23:39
 */
@RestController
@RequestMapping("generator/task")
@Api("任务处理")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private SysUserTokenDao sysUserTokenDao;

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserService sysUserService;



    /**
     * 列表
     */
    @RequestMapping("/list")
   // @RequiresPermissions("generator:task:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = taskService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("generator:task:info")
    public R info(@PathVariable("id") Integer id){
		TaskEntity task = taskService.getById(id);

        return R.ok().put("task", task);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
   // @RequiresPermissions("generator:task:save")
    public R save(@RequestBody TaskEntity task, HttpServletRequest httpRequest){
//        task.setGetuser(task.getGetuser().toArray().toString());
        task.setCreatetime(new Date());
        String token = httpRequest.getHeader("token");
        SysUserTokenEntity sysUserTokenEntity = sysUserTokenDao.queryByToken(token);
        task.setCreateuser(String.valueOf(sysUserTokenEntity.getUserId()));
        task.setFlag("0");
		taskService.saveTask(task);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
   // @RequiresPermissions("generator:task:update")
    public R update(@RequestBody TaskEntity task){
		taskService.updateById(task);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // @RequiresPermissions("generator:task:delete")
    public R delete(@RequestBody Integer[] ids){
		taskService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }



//    获取所有学生
    @PostMapping("/queryAllStuName")
    @ApiOperation("获取所有学生")
    // @RequiresPermissions("generator:task:delete")
    public R queryAllStuName(){
       List<SysUserEntity> s = sysUserService.queryAllStuName();
       return R.ok().put("page", s);
    }


    //    获取所有成绩
    @PostMapping("/queryAllStuNameScore")
    @ApiOperation("获取所有成绩")
    // @RequiresPermissions("generator:task:delete")
    public R queryAllStuNameScore(){
        List<Map<String,Object>> s = sysUserService.queryAllStuNameScore();
        return R.ok().put("page", s);
    }



}
