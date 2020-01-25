package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.AttendanceEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-01-25 17:05:48
 */
public interface AttendanceService extends IService<AttendanceEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

