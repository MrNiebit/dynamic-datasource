package com.lacknb.dynamicdatasource.demos.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * @author gitsilence
 * @date 2024-04-23
 */
public interface CommonMapper {


    List<Map<String, Object>> selectList(@Param("sql") String sql);

}
