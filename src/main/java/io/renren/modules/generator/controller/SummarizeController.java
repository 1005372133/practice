package io.renren.modules.generator.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.SummarizeEntity;
import io.renren.modules.generator.service.SummarizeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-12-22 10:23:39
 */
@RestController
@RequestMapping("generator/summarize")
public class SummarizeController {
    @Autowired
    private SummarizeService summarizeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:summarize:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = summarizeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:summarize:info")
    public R info(@PathVariable("id") Integer id){
		SummarizeEntity summarize = summarizeService.getById(id);

        return R.ok().put("summarize", summarize);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:summarize:save")
    public R save(@RequestBody SummarizeEntity summarize){
		summarizeService.save(summarize);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:summarize:update")
    public R update(@RequestBody SummarizeEntity summarize){
		summarizeService.updateById(summarize);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:summarize:delete")
    public R delete(@RequestBody Integer[] ids){
		summarizeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
