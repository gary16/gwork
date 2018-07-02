package com.gwork.app.springdemo.basic.repository;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class PhoneTypeHandler implements TypeHandler<String> {

	@Override
	public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, "123" + parameter);
	}

	@Override
	public String getResult(ResultSet rs, String columnName) throws SQLException {
		return rs.getString(columnName).replace("123", "");
	}

	@Override
	public String getResult(ResultSet rs, int columnIndex) throws SQLException {
		return rs.getString(columnIndex).replace("123", "");
	}

	@Override
	public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return cs.getString(columnIndex).replace("123", "");
	}

}
