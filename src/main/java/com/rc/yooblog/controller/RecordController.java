package com.rc.yooblog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rc.yooblog.common.condition.RecordCondition;
import com.rc.yooblog.common.dto.RecordDto;
import com.rc.yooblog.common.utils.ResultVOUtil;
import com.rc.yooblog.common.vo.ResultVO;
import com.rc.yooblog.entity.Record;
import com.rc.yooblog.exception.YooException;
import com.rc.yooblog.service.RecordServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.rc.yooblog.exception.ResultEnum.SAVE_FAIL;

/**
 * 作者：flandre on 2019/4/12 10:24
 * 描述：
 */
@RestController
@RequestMapping("/manage")
@Slf4j
@Api(value = "归档管理", tags = "归档管理")
public class RecordController {

    @Autowired
    RecordServiceImpl recordService;

    @GetMapping("/records")
    @ApiOperation("获取归档列表")
    public ResultVO records(@RequestParam(value = "current", defaultValue = "1", required = false) Integer current, @RequestParam(value = "size", defaultValue = "8", required = false) Integer size){
        IPage<Record> recordPage = recordService.getRecords(current, size);
        return ResultVOUtil.success(recordPage);
    }

    @PostMapping("/record")
    @ApiOperation("更新或保存归档")
    public ResultVO updateOrSave(@RequestBody Record record) throws YooException {
        boolean isSuccess = recordService.saveOrUpdate(record);
        if (!isSuccess){
            throw new YooException(SAVE_FAIL);
        }

        //返回保存结果
        Record afterSave = recordService.getById(record.getRecId());
        return ResultVOUtil.success(afterSave);
    }

    @GetMapping("/record")
    @ApiOperation(value = "条件查询文章")
    public ResultVO selectRecord(RecordCondition condition) {
        log.info("condition input : {}", condition);

        IPage<Record> articlePage = recordService.findByCondition(condition);
        return ResultVOUtil.success(articlePage);
    }
}
