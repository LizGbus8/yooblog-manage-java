package com.rc.yooblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rc.yooblog.common.condition.TagCondition;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rc.yooblog.entity.Tag;
import com.rc.yooblog.mapper.TagMapper;
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
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements IService<Tag> {

    /**
     * 条件查找
     * @param condition
     * @return
     */
    public List<Tag> findByCondition(TagCondition condition) {
        //1.拼接查询条件
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
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
