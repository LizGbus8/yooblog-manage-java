package com.rc.yooblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rc.yooblog.entity.Tool;
import com.rc.yooblog.mapper.ToolMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章  服务实现类
 * </p>
 *
 * @author flandre
 * @since 2019-04-25
 */
@Service
public class ToolServiceImpl extends ServiceImpl<ToolMapper, Tool> implements IService<Tool> {


}
