package com.wzy.mapper;

import com.wzy.entity.BoxWorkFilter;
import org.apache.ibatis.annotations.Insert;

public interface BoxWorkFilterMapper {

    /**
     * 添加过滤器信息
     * @param workFilter
     * @return
     */
    @Insert("insert into box_work_filter(name,path,className) values (#{name},#{path},#{className})")
    int add(BoxWorkFilter workFilter);
}
