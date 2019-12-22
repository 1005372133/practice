package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.SummarizeDao;
import io.renren.modules.generator.entity.SummarizeEntity;
import io.renren.modules.generator.service.SummarizeService;


@Service("summarizeService")
public class SummarizeServiceImpl extends ServiceImpl<SummarizeDao, SummarizeEntity> implements SummarizeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SummarizeEntity> page = this.page(
                new Query<SummarizeEntity>().getPage(params),
                new QueryWrapper<SummarizeEntity>()
        );

        return new PageUtils(page);
    }

}