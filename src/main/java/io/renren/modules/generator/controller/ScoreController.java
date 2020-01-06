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

import io.renren.modules.generator.entity.ScoreEntity;
import io.renren.modules.generator.service.ScoreService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-01-06 22:11:32
 */
@RestController
@RequestMapping("generator/score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("generator:score:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = scoreService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
   // @RequiresPermissions("generator:score:info")
    public R info(@PathVariable("id") Integer id){
		ScoreEntity score = scoreService.getById(id);

        return R.ok().put("score", score);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("generator:score:save")
    public R save(@RequestBody ScoreEntity score){
		scoreService.save(score);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("generator:score:update")
    public R update(@RequestBody ScoreEntity score){
		scoreService.updateById(score);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // @RequiresPermissions("generator:score:delete")
    public R delete(@RequestBody Integer[] ids){
		scoreService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
