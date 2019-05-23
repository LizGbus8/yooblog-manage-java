package com.rc.yooblog.common.condition;

import lombok.Data;

/**
 * 作者：flandre on 2019/5/17 16:51
 * 描述：
 */
@Data
public class RecordCondition {

    /**
     * 内容
     */
    private String content;

    /**
     * 作者
     */
    private String author;

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
