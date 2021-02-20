package com.dql.demo.datasource.mapper;


import com.dql.demo.datasource.MyMapper;
import com.dql.demo.datasource.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户 Mapper
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019-09-04 16:49
 */
@Mapper
public interface UserMapper extends MyMapper<User> {
}
