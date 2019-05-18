package com.rc.yooblog.controller;

import com.rc.yooblog.common.condition.TabCondition;
import com.rc.yooblog.common.utils.ResultVOUtil;
import com.rc.yooblog.common.vo.ResultVO;
import com.rc.yooblog.entity.Tab;
import com.rc.yooblog.exception.YooException;
import com.rc.yooblog.service.TabServiceImpl;
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
public class TabCotroller {
    @Autowired
    TabServiceImpl tabService;

    @GetMapping("/tabs")
    @ApiOperation(value = "获取所有的标签", notes = "分类标签")
    public ResultVO tabs() {
        List<Tab> list = tabService.list();
        return ResultVOUtil.success(list);
    }

    @PostMapping("/tab")
    @ApiOperation(value = "更改标签", notes = "更改标签")
    public ResultVO tab(@RequestBody Tab tab) throws YooException {
        boolean isSuccess = tabService.saveOrUpdate(tab);
        if (!isSuccess) {
            throw new YooException(SAVE_FAIL);
        }
        return ResultVOUtil.success(tab);
    }

    @GetMapping("/tab")
    @ApiOperation(value = "更改标签", notes = "更改标签")
    public ResultVO tab(TabCondition condition) {
        log.info("condition input :{}", condition);

        List<Tab> tabs = tabService.findByCondition(condition);

        return ResultVOUtil.success(tabs);
    }
}
