package io.renren.modules.generator.service.impl;

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

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DadilyreportEntity> page = this.page(
                new Query<DadilyreportEntity>().getPage(params),
                new QueryWrapper<DadilyreportEntity>()
        );

        return new PageUtils(page);
    }

}