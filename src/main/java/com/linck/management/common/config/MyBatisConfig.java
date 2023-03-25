package com.linck.management.common.config;

import cn.hutool.core.util.ClassUtil;
import com.linck.management.common.model.enums.NameValueInterface;
import com.linck.management.common.util.EnumUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

/**
 * 自动注册枚举转换处理
 *
 * @author linck
 */
@Configuration
@SuppressWarnings("rawtypes")
public class MyBatisConfig {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void init() {

        // 扫描当前项目下所有 NameValueInterface 实现类
        Set<Class<?>> classes = ClassUtil.scanPackage("com.linck"
                , aClass -> NameValueInterface.class.isAssignableFrom(aClass) && !NameValueInterface.class.equals(aClass));

        // 注册枚举类与数据库查询结果/参数的序列化与反序列化规则
        classes.forEach(clazz -> sqlSessionFactory.getConfiguration().getTypeHandlerRegistry().register(clazz, new BaseTypeHandler() {

            @Override
            public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
                ps.setInt(i, ((NameValueInterface) parameter).getValue());
            }

            @Override
            public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
                return EnumUtil.getEnumByValue((Class<? extends NameValueInterface>) clazz, rs.getInt(columnName));
            }

            @Override
            public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
                return EnumUtil.getEnumByValue((Class<? extends NameValueInterface>) clazz, rs.getInt(columnIndex));
            }

            @Override
            public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
                return EnumUtil.getEnumByValue((Class<? extends NameValueInterface>) clazz, cs.getInt(columnIndex));
            }
        }));
    }
}
