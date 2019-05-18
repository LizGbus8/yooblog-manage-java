package com.rc.yooblog.controller;

import com.rc.yooblog.common.utils.ResultVOUtil;
import com.rc.yooblog.common.vo.ResultVO;
import com.rc.yooblog.entity.Users;
import com.rc.yooblog.exception.YooException;
import com.rc.yooblog.service.UsersServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.rc.yooblog.exception.ResultEnum.SAVE_FAIL;

/**
 * 作者：flandre on 2019/4/14 14:32
 * 描述：
 */
@RestController
@Api(value = "用户信息管理", tags = "用户信息管理")
@RequestMapping("/manage")
public class UsersController {

    @Autowired
    UsersServiceImpl usersService;

    @GetMapping("/user")
    @ApiOperation("获取用户信息")
    public ResultVO user(@RequestParam("uid") Integer uid) {
        Users user = usersService.getById(uid);
        return ResultVOUtil.success(user);
    }

    @PostMapping("/user")
    @ApiOperation("更改用户信息")
    public ResultVO save(@RequestBody Users users) throws YooException {
        //1.更新用户信息
        boolean isSuccess = usersService.saveOrUpdate(users);
        if (!isSuccess) {
            throw new YooException(SAVE_FAIL);
        }
        //返回更新后用户信息
        return ResultVOUtil.success(users);
    }
}
