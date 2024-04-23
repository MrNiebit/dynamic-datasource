package com.lacknb.dynamicdatasource;

import com.lacknb.dynamicdatasource.demos.config.DynamicDataSourceContextHolder;
import com.lacknb.dynamicdatasource.demos.mapper.CommonMapper;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DynamicDatasourceApplicationTests {

    @Autowired
    private CommonMapper commonMapper;

    @Test
    void contextLoads() {

        DynamicDataSourceContextHolder.setDataSource("primary");
        List<Map<String, Object>> maps = commonMapper.selectList("select * from a_test");
        maps.forEach(System.out::println);

        DynamicDataSourceContextHolder.setDataSource("secondary");
        List<Map<String, Object>> datas = commonMapper.selectList("select * from a_test");
        datas.forEach(System.out::println);

    }

    @Test
    void threadLocal() {
        DynamicDataSourceContextHolder.setDataSource("secondary");
        System.out.println(DynamicDataSourceContextHolder.getDataSource());
        new Thread(() -> {
            System.out.println(DynamicDataSourceContextHolder.getDataSource());
        }).start();

        // secondary
        // primary
    }

}
