package com.linck.management.common.handler.mybatis;

import com.linck.management.common.model.constant.StateEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: linck
 * @create: 2021-11-24
 * @description StateEnum 与数据 state 字段转换处理器
 */
public class StateEnumHandler extends BaseTypeHandler<StateEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, StateEnum stateEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, stateEnum.getValue());
    }

    @Override
    public StateEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return StateEnum.getStateEnum(resultSet.getInt(s));
    }

    @Override
    public StateEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return StateEnum.getStateEnum(resultSet.getInt(i));
    }

    @Override
    public StateEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return StateEnum.getStateEnum(callableStatement.getInt(i));
    }
}
