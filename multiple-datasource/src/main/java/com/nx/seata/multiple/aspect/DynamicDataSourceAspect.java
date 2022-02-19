package com.nx.seata.multiple.aspect;

import com.nx.seata.multiple.annotation.DataSource;
import com.nx.seata.multiple.config.DataSourceKey;
import com.nx.seata.multiple.config.DynamicDataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DynamicDataSourceAspect {
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, DataSource ds) throws Throwable {
        DataSourceKey dsId = ds.value();
        if (DynamicDataSourceContextHolder.isContainsDataSource(dsId.name())) {
            DynamicDataSourceContextHolder.setDataSourceKey(dsId);
            logger.debug("Use DataSource :{} >", dsId, point.getSignature());
        } else {
            logger.error("数据源[{}]不存在，使用默认数据源 >{}", dsId, point.getSignature());
        }
    }

    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, DataSource ds) {
        logger.debug("Revert DataSource : " + ds.value() + " > " + point.getSignature());
        DynamicDataSourceContextHolder.clearDataSourceKey();

    }
}