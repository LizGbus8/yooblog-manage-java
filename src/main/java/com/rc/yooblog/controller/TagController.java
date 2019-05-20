package com.rc.yooblog.controller;

import com.rc.yooblog.common.condition.TagCondition;
import com.rc.yooblog.common.utils.ResultVOUtil;
import com.rc.yooblog.common.vo.ResultVO;
import com.rc.yooblog.entity.Tag;
import com.rc.yooblog.exception.YooException;
import com.rc.yooblog.service.TagServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.rc.yooblog.exception.ResultEnum.SAVE_FAIL;

/**
 * 作者：flandre on 2019/4/5 15:43
 * 描述：
 */
@RestController
@Api(value = "标签管理",tags = "标签管理")
@RequestMapping("/manage")
@Slf4j
public class TagController {
    @Autowired
    TagServiceImpl tagService;

    @GetMapping("/tags")
    @ApiOperation(value = "获取所有的标签", notes = "分类标签")
    public ResultVO Tags() {
        List<Tag> list = tagService.list();
        return ResultVOUtil.success(list);
    }

    @PostMapping("/tag")
    @ApiOperation(value = "更改标签", notes = "更改标签")
    public ResultVO Tag(@RequestBody Tag Tag) throws YooException {
        boolean isSuccess = tagService.saveOrUpdate(Tag);
        if (!isSuccess) {
            throw new YooException(SAVE_FAIL);
        }
        return ResultVOUtil.success(Tag);
    }

    @GetMapping("/tag")
    @ApiOperation(value = "更改标签", notes = "更改标签")
    public ResultVO Tag(TagCondition condition) {
        log.info("condition input :{}", condition);

        List<Tag> Tags = tagService.findByCondition(condition);

        return ResultVOUtil.success(Tags);
    }
}
