package com.rc.yooblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rc.yooblog.common.dto.ReplyDto;
import com.rc.yooblog.common.proxy.IpUtils;
import com.rc.yooblog.common.utils.KeyUtil;
import com.rc.yooblog.entity.CommentsReply;
import com.rc.yooblog.mapper.CommentsReplyMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论回复表  服务实现类
 *
 * @author flandre
 * @since 2019-04-04
 */
@Service
public class CommentsReplyServiceImpl extends ServiceImpl<CommentsReplyMapper, CommentsReply> implements IService<CommentsReply> {

    /**
     * 获取回复列表
     * @param commentsId
     * @return
     */
    public List<CommentsReply> getReplies(String commentsId) {
        //1.拼接查询条件
        QueryWrapper<CommentsReply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comments_id", commentsId);
        queryWrapper.orderByAsc("created_time");

        //2.执行查询 返回结果
        return list(queryWrapper);
    }
}
