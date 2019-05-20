package com.rc.yooblog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rc.yooblog.common.utils.ResultVOUtil;
import com.rc.yooblog.common.vo.ResultVO;
import com.rc.yooblog.entity.Article;
import com.rc.yooblog.exception.YooException;
import com.rc.yooblog.service.ArticleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 作者：flandre on 2019/4/4 17:15
 * 描述：
 */
@RestController
@Api(value = "文章管理", tags = "文章管理")
@RequestMapping("/manage")
@Slf4j
public class ArticleController {

    @Autowired
    ArticleServiceImpl articleService;

    @GetMapping("/articles")
    @ApiOperation(value = "获取文章列表")
    public ResultVO articles(@RequestParam("current") Integer current, @RequestParam("size") Integer size) {
        //1.分页设置
        IPage<Article> page = new Page<>(current,size);
        //2.查询
        IPage<Article> articlePage = articleService.page(page);

        return ResultVOUtil.success(articlePage);
    }

    @GetMapping("/article/{aId}")
    @ApiOperation(value = "获取文章详情")
    public ResultVO article(@PathVariable("aId") String aId) throws YooException {
        Article article = articleService.getById(aId);
        return ResultVOUtil.success(article);
    }
}
