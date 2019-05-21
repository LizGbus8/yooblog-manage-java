package com.rc.yooblog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rc.yooblog.common.condition.ArticleCondition;
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

import static com.rc.yooblog.exception.ResultEnum.SAVE_FAIL;


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
    public ResultVO article(@PathVariable("aId") String aId) {
        Article article = articleService.getById(aId);
        return ResultVOUtil.success(article);
    }

    @PostMapping("/article")
    @ApiOperation(value = "保存或更新文章")
    public ResultVO saveOrUpdateArticle(@RequestBody Article article) throws YooException {
        boolean isSuccess = articleService.saveOrUpdate(article);
        if (!isSuccess) {
            throw new YooException(SAVE_FAIL);
        }
        //返回保存后文章信息
        Article afterSave = articleService.getById(article.getAId());
        return ResultVOUtil.success(afterSave);
    }

    @GetMapping("/article")
    @ApiOperation(value = "条件查询文章")
    public ResultVO selectArticle(ArticleCondition condition){
        log.info("condition input : {}", condition);

        IPage<Article> articlePage = articleService.findByContition(condition);
        return ResultVOUtil.success(articlePage);
    }
}
