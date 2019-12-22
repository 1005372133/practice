package io.renren.modules.generator.dao;

import io.renren.modules.generator.entity.TaskEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-12-22 10:23:39
 */
@Mapper
public interface TaskDao extends BaseMapper<TaskEntity> {
	
}
