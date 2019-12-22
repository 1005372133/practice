package io.renren.modules.generator.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.renren.modules.sys.dao.SysUserTokenDao;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.entity.SysUserTokenEntity;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.DadilyreportEntity;
import io.renren.modules.generator.service.DadilyreportService;
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
@RequestMapping("generator/dadilyreport")
public class DadilyreportController {
    @Autowired
    private DadilyreportService dadilyreportService;

    @Autowired
    private SysUserTokenDao sysUserTokenDao;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("generator:dadilyreport:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = dadilyreportService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("generator:dadilyreport:info")
    public R info(@PathVariable("id") Integer id){
		DadilyreportEntity dadilyreport = dadilyreportService.getById(id);
        return R.ok().put("dadilyreport", dadilyreport);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("generator:dadilyreport:save")
    public R save(@RequestBody DadilyreportEntity dadilyreport,HttpServletRequest httpRequest){
        dadilyreport.setTime(new Date());
        String token = httpRequest.getHeader("token");
        SysUserTokenEntity sysUserTokenEntity = sysUserTokenDao.queryByToken(token);
        dadilyreport.setUser(String.valueOf(sysUserTokenEntity.getUserId()));
		dadilyreportService.save(dadilyreport);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("generator:dadilyreport:update")
    public R update(@RequestBody DadilyreportEntity dadilyreport){
		dadilyreportService.updateById(dadilyreport);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("generator:dadilyreport:delete")
    public R delete(@RequestBody Integer[] ids){
		dadilyreportService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
