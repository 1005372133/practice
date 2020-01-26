package io.renren.modules.generator.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;

import io.renren.modules.generator.service.TaskService;
import io.renren.modules.sys.dao.SysUserTokenDao;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.entity.SysUserTokenEntity;
import io.renren.modules.sys.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.generator.entity.DadilyreportEntity;
import io.renren.modules.generator.service.DadilyreportService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import static io.renren.common.utils.ShiroUtils.getUserId;


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

    @Autowired
    private TaskService taskService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("generator:dadilyreport:list")
    public R list(@RequestParam Map<String, Object> params){
        params.put("user", getUserId());
        PageUtils page = dadilyreportService.queryPage(params);
     /*   int s = page.getList().size();
        for (int i = 0;i< page.getList().size();i++)
        {
            if (!page.getList().get(i).equals(getUserId())) {
                page.getList().remove(i);
            }
        }*/
        return R.ok().put("page", page);
    }


    //    获取发布人
    @PostMapping("/queryUser")
    @ApiOperation("获取发布人")
    // @RequiresPermissions("generator:task:delete")
    public R queryUser(){
        SysUserEntity s = sysUserService.queryUser();
        return R.ok().put("page", s);
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

    @RequestMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile files){
        String fileName = files.getOriginalFilename();
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        String localFileName = System.currentTimeMillis() + fileSuffix;
        String filePath = "D:\\\\上传" + File.separator + localFileName;
        File localFile = new File(filePath);
        File imagePath = new File("D:\\\\上传");
        if (!imagePath.exists()) {
            imagePath.mkdirs();
        }
        try {
            files.transferTo(localFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok();
    }


    //获取登陆人实习任务
    @RequestMapping("/getTask")
    public  R getTask() {
        Map<String, Object> params = new HashMap<>();
        params.put("getuser",getUserId());
        return R.ok().put("page",taskService.queryPage(params));
    }
}
