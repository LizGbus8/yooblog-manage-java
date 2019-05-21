package com.rc.yooblog.common.condition;

import lombok.Data;

/**
 * 作者：flandre on 2019/5/17 16:51
 * 描述：
 */
@Data
public class ArticleCondition {

    /**
     * 标题
     */
    private String title;

    /**
     * 标签id
     */
    private Integer tId;

    /**
     * 公开状态
     */
    private Integer pub;

    /**
     * 创建时间[0] start
     * 创建时间[1] end
     */
    private Long[] createdTimes;

    /**
     * 当前页数
     */
    private Integer current = 1;
}
