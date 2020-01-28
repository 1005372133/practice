package io.renren.modules.generator.service.impl;

import io.renren.modules.sys.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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



    @Autowired
    private SysUserServiceImpl sysUserService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttendanceEntity> page = this.page(
                new Query<AttendanceEntity>().getPage(params),
                new QueryWrapper<AttendanceEntity>()
        );

        if (page.getRecords().size()!=0){
            for (int i =0 ;i<page.getRecords().size();i++){
                page.getRecords().get(i).setUserid(sysUserService.getName(page.getRecords().get(i).getUserid()));
            }
        }

        return new PageUtils(page);
    }

}