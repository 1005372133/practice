package io.renren.modules.generator.service.impl;

import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.service.impl.SysUserServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.DadilyreportDao;
import io.renren.modules.generator.entity.DadilyreportEntity;
import io.renren.modules.generator.service.DadilyreportService;


@Service("dadilyreportService")
public class DadilyreportServiceImpl extends ServiceImpl<DadilyreportDao, DadilyreportEntity> implements DadilyreportService {

    @Autowired
    private SysUserServiceImpl sysUserService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String user = String.valueOf(params.get("user"));
        IPage<DadilyreportEntity> page = this.page(
                new Query<DadilyreportEntity>().getPage(params),
                new QueryWrapper<DadilyreportEntity>()
                        .like(StringUtils.isNotBlank(user),"user", user)
        );
        if (page.getRecords().size()!=0){
            for (int i =0 ;i<page.getRecords().size();i++){
                page.getRecords().get(i).setUser(sysUserService.getName(user));
            }
        }

        return new PageUtils(page);
    }

}