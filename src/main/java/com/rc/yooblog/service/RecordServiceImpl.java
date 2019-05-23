package com.rc.yooblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rc.yooblog.common.condition.RecordCondition;
import com.rc.yooblog.common.dto.RecordDto;
import com.rc.yooblog.entity.Record;
import com.rc.yooblog.mapper.RecordMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * <p>
 * 归档  服务实现类
 * </p>
 *
 * @author flandre
 * @since 2019-04-04
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IService<Record> {

    /**
     * 获取归档列表
     * @param current
     * @param size
     * @return
     */
    public IPage<Record> getRecords(Integer current, Integer size) {
        //1.拼接查询条件
        Page<Record> page = new Page<>(current,size);
        //2.执行查询 返回结果
        return page(page);
    }

    /**
     * 条件查询
     * @param condition
     * @return
     */
    public IPage<Record> findByCondition(RecordCondition condition) {
        //1.拼接查询条件
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(condition.getContent())) {
            queryWrapper.like("content", condition.getContent());
        }
        if (!StringUtils.isEmpty(condition.getAuthor())) {
            queryWrapper.like("author", condition.getAuthor());
        }
        if (condition.getCreatedTimes() != null && condition.getCreatedTimes().length == 2) {
            System.out.println(new Date(condition.getCreatedTimes()[0]));
            System.out.println(new Date(condition.getCreatedTimes()[1]));
            queryWrapper.ge("created_time", new Date(condition.getCreatedTimes()[0]));
            queryWrapper.le("created_time", new Date(condition.getCreatedTimes()[1]));
        }
        Page<Record> page = new Page<>(condition.getCurrent(), 10);

        //2.执行查询
        return page(page, queryWrapper);
    }
}
