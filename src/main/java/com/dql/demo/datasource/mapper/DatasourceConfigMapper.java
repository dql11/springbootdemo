package com.dql.demo.datasource.mapper;


import com.dql.demo.datasource.MyMapper;
import com.dql.demo.datasource.model.DatasourceConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 数据源配置 Mapper
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019-09-04 16:20
 */
@Mapper
public interface DatasourceConfigMapper extends MyMapper<DatasourceConfig> {
}
