package io.renren.modules.generator.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.modules.sys.entity.SysUserTokenEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.AttendanceEntity;
import io.renren.modules.generator.service.AttendanceService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import javax.servlet.http.HttpServletRequest;

import static io.renren.common.utils.ShiroUtils.getUserId;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-01-25 17:05:48
 */
@RestController
@RequestMapping("generator/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
   // @RequiresPermissions("generator:attendance:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attendanceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
   // @RequiresPermissions("generator:attendance:info")
    public R info(@PathVariable("id") Integer id){
		AttendanceEntity attendance = attendanceService.getById(id);

        return R.ok().put("attendance", attendance);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
   // @RequiresPermissions("generator:attendance:save")
    public R save(@RequestBody AttendanceEntity attendance){
        if (attendance.getType().equals("1")){
            attendance.setType("迟到早退");
        }
        else  if (attendance.getType().equals("2")){
            attendance.setType("签到");
        }
        else if (attendance.getType().equals("3")){
            attendance.setType("旷课");
        }
        else {
            attendance.setType("请假");
        }
        attendance.setUserid(getUserId().intValue());
		attendanceService.save(attendance);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
   // @RequiresPermissions("generator:attendance:update")
    public R update(@RequestBody AttendanceEntity attendance){
		attendanceService.updateById(attendance);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("generator:attendance:delete")
    public R delete(@RequestBody Integer[] ids){
		attendanceService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
