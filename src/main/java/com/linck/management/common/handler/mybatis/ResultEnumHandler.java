package com.linck.management.common.handler.mybatis;

import com.linck.management.quartz.model.entity.SysJobLog;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: linck
 * @create: 2021-11-24
 * @description ResultEnum 与数据 result 字段转换处理器
 */
public class ResultEnumHandler extends BaseTypeHandler<SysJobLog.ResultEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, SysJobLog.ResultEnum resultEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, resultEnum.getValue());
    }

    @Override
    public SysJobLog.ResultEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return SysJobLog.ResultEnum.getResultEnum(resultSet.getInt(s));
    }

    @Override
    public SysJobLog.ResultEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return SysJobLog.ResultEnum.getResultEnum(resultSet.getInt(i));
    }

    @Override
    public SysJobLog.ResultEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return SysJobLog.ResultEnum.getResultEnum(callableStatement.getInt(i));
    }
}
