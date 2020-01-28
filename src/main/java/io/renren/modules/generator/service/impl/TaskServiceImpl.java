package io.renren.modules.generator.service.impl;

import com.alibaba.fastjson.JSON;
import io.renren.modules.sys.service.impl.SysUserServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.TaskDao;
import io.renren.modules.generator.entity.TaskEntity;
import io.renren.modules.generator.service.TaskService;


@Service("taskService")
public class TaskServiceImpl extends ServiceImpl<TaskDao, TaskEntity> implements TaskService {


    @Autowired
    private SysUserServiceImpl sysUserService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TaskEntity> page = this.page(
                new Query<TaskEntity>().getPage(params),
                new QueryWrapper<TaskEntity>()
        );
        if (page.getRecords().size()!=0){
            for (int i =0 ;i<page.getRecords().size();i++){

                if (StringUtils.isNotBlank(page.getRecords().get(i).getGetuser())){
                    String[] users =page.getRecords().get(i).getGetuser().substring(1,page.getRecords().get(i).getGetuser().length()-1).split(",");
                    String user = "";

                    for (int j = 0; j < users.length; j++) {
                        if (StringUtils.isBlank(user)){
                            user=sysUserService.getName(users[j]);
                        }else {
                            user=user+ ","+ sysUserService.getName(users[j]);
                        }
                    }

                    page.getRecords().get(i).setGetuser(user);
                }
            }

                for (int i =0 ;i<page.getRecords().size();i++){
                    page.getRecords().get(i).setCreateuser(sysUserService.getName(page.getRecords().get(i).getCreateuser()));
                }
        }

        return new PageUtils(page);
    }

    @Override
    public void saveTask(TaskEntity task) {
        //List<Integer> userList = JSON.parseArray(JSON.parseObject(task.getUserList()).getString("task"), TaskEntity.class);
       this.save(task);
    }

}