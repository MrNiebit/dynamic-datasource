package com.lacknb.dynamicdatasource.demos.config;

/**
 * @author gitsilence
 * @date 2024-04-23
 */
public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "primary";
        }
    };

    public static void setDataSource(String dataSource) {
        contextHolder.set(dataSource);
    }

    public static String getDataSource() {
        return contextHolder.get();
    }

    public static void clearDataSource() {
        contextHolder.remove();
    }

}
