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

import io.renren.modules.generator.entity.SuggestEntity;
import io.renren.modules.generator.service.SuggestService;
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
@RequestMapping("generator/suggest")
public class SuggestController {
    @Autowired
    private SuggestService suggestService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:suggest:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = suggestService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:suggest:info")
    public R info(@PathVariable("id") Integer id){
		SuggestEntity suggest = suggestService.getById(id);

        return R.ok().put("suggest", suggest);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:suggest:save")
    public R save(@RequestBody SuggestEntity suggest){
		suggestService.save(suggest);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:suggest:update")
    public R update(@RequestBody SuggestEntity suggest){
		suggestService.updateById(suggest);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:suggest:delete")
    public R delete(@RequestBody Integer[] ids){
		suggestService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
