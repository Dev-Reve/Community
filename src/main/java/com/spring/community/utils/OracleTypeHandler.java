package com.spring.community.utils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.StringTypeHandler;

import oracle.sql.ARRAY;

public class OracleTypeHandler extends BaseTypeHandler<String[]> {
	private static final StringTypeHandler STRING_TYPE_HANDLER = new StringTypeHandler();
	private ARRAY array;
	
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType)
			throws SQLException {
		if(parameter != null) ps.setArray(i, ps.getConnection().createArrayOf("VARCHAR2", parameter));
		else ps.setNull(i, Types.ARRAY);
	}

	@Override
	public String[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
		array = (ARRAY)rs.getArray(columnName);
		if(array != null) return (String[]) array.getArray();
		else return null;
	}

	@Override
	public String[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		array = (ARRAY) rs.getArray(columnIndex);
		if(array != null) return (String[]) array.getArray();
		else return null;
	}

	@Override
	public String[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		array = (ARRAY) cs.getArray(columnIndex);
		if(array != null) return (String[]) array.getArray();
		else return null;
	}

}
