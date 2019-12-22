package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.SuggestDao;
import io.renren.modules.generator.entity.SuggestEntity;
import io.renren.modules.generator.service.SuggestService;


@Service("suggestService")
public class SuggestServiceImpl extends ServiceImpl<SuggestDao, SuggestEntity> implements SuggestService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SuggestEntity> page = this.page(
                new Query<SuggestEntity>().getPage(params),
                new QueryWrapper<SuggestEntity>()
        );

        return new PageUtils(page);
    }

}