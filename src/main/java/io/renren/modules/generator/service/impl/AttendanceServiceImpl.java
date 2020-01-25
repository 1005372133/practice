package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.AttendanceDao;
import io.renren.modules.generator.entity.AttendanceEntity;
import io.renren.modules.generator.service.AttendanceService;


@Service("attendanceService")
public class AttendanceServiceImpl extends ServiceImpl<AttendanceDao, AttendanceEntity> implements AttendanceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttendanceEntity> page = this.page(
                new Query<AttendanceEntity>().getPage(params),
                new QueryWrapper<AttendanceEntity>()
        );

        return new PageUtils(page);
    }

}