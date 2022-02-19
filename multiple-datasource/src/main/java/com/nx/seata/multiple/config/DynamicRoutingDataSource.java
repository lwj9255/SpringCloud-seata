package com.nx.seata.multiple.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 基于AbstractRoutingDataSource的多数据源动态切换
 */
@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    /**
     * 该方法的返回值就是项目中所要用的DataSource的key值，
     * 拿到该key后就可以在resolvedDataSource中取出对应的DataSource，
     * 如果key找不到对应的DataSource就使用默认的数据源。
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        log.info("当前数据源 [{}]", DynamicDataSourceContextHolder.getDataSourceKey());
        return DynamicDataSourceContextHolder.getDataSourceKey();

    }

    public static void main(String[] args) {
        System.out.println(DynamicDataSourceContextHolder.getDataSourceKey());
    }
}