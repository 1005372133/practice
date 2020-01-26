package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.TaskEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-12-22 10:23:39
 */
public interface TaskService extends IService<TaskEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存实习任务
     * @param task
     * @return
     */
    void saveTask(TaskEntity task);

}

