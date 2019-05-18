package com.rc.yooblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 作者：flandre on 2019/4/14 14:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Users {
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid ;
    /** 名字 */
    private String nickname ;
    /** 说说 */
    private String saying ;
    /** 头像 */
    private String avatar ;
    /** github */
    private String github ;
    /** 掘金 */
    private String juejin ;
    /** 关于博主 */
    private String about ;
    /** 创建时间 */
    private LocalDateTime createdTime ;
    /** 更新时间 */
    private LocalDateTime updatedTime ;
}
