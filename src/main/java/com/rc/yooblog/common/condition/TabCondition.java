package com.rc.yooblog.common.condition;

import lombok.Data;

/**
 * 作者：flandre on 2019/5/17 16:51
 * 描述：
 */
@Data
public class TabCondition {

    private Integer tId;

    /**
     * 说明
     */
    private String description;

    /**
     * 所属分类
     */
    private String catId;
}
