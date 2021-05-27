package backskin.bankapi.dao;

import backskin.bankapi.dao.mappers.SqlMapper;

import java.sql.Connection;
import java.sql.ResultSet;

public interface SqlDAO<T>{
    String getTableName();
    Connection getConnection();
    SqlMapper<T, ResultSet> getMapper();
}
