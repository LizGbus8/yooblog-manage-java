package com.rc.yooblog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rc.yooblog.common.dto.CommentDto;
import com.rc.yooblog.common.dto.ReplyDto;
import com.rc.yooblog.common.proxy.IpUtils;
import com.rc.yooblog.common.utils.ResultVOUtil;
import com.rc.yooblog.common.vo.ResultVO;
import com.rc.yooblog.entity.CommentsInfo;
import com.rc.yooblog.entity.CommentsReply;
import com.rc.yooblog.service.CommentsInfoServiceImpl;
import com.rc.yooblog.service.CommentsReplyServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 作者：flandre on 2019/4/6 23:15
 * 描述：
 */
@RestController
@RequestMapping("/manage")
@Api(value = "评论留言相关API", tags = "评论&留言管理")
public class CommentController {

    @Autowired
    CommentsInfoServiceImpl commentsInfoService;

    @Autowired
    CommentsReplyServiceImpl commentsReplyService;


    @GetMapping("/comments")
    @ApiOperation(value = "根据评论Id获取评论列表")
    public ResultVO comments(@RequestParam("ownerId") String ownerId, @RequestParam(value = "currentPage", defaultValue = "1", required = false) Integer current, @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        List<CommentDto> commentDtos = commentsInfoService.getComments(ownerId, current, size);
        return ResultVOUtil.success(commentDtos);
    }

    @GetMapping("/talks")
    @ApiOperation(value = "获取留言列表")
    public ResultVO talks(@RequestParam(value = "current", defaultValue = "1", required = false) Integer current, @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        IPage<CommentsInfo> talks = commentsInfoService.getTalks(current, size);
        return ResultVOUtil.success(talks);
    }

    @GetMapping("/replies")
    @ApiOperation(value = "获取子回复列表")
    public ResultVO replies(@RequestParam("commentsId") String commentsId) {
        List<CommentsReply> replies = commentsReplyService.getReplies(commentsId);
        return ResultVOUtil.success(replies);
    }
}
