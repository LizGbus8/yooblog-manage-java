package com.rc.yooblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rc.yooblog.common.condition.TabCondition;
import com.rc.yooblog.entity.Tab;
import com.rc.yooblog.mapper.TabMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 标签 文章标签 服务实现类
 * </p>
 *
 * @author flandre
 * @since 2019-04-04
 */
@Service
public class TabServiceImpl extends ServiceImpl<TabMapper, Tab> implements IService<Tab> {

    /**
     * 条件查找
     * @param condition
     * @return
     */
    public List<Tab> findByCondition(TabCondition condition) {
        //1.拼接查询条件
        QueryWrapper<Tab> queryWrapper = new QueryWrapper<>();
        //ID
        if (condition.getTId() != null) {
            queryWrapper.eq("t_id", condition.getTId());
        }
        //分类
        if (!StringUtils.isEmpty(condition.getCatId())) {
            queryWrapper.eq("cat_id", condition.getCatId());
        }
        //说明
        if (!StringUtils.isEmpty(condition.getDescription())) {
            queryWrapper.like("description", condition.getDescription());
        }

        //2.执行查询
        return list(queryWrapper);
    }
}
