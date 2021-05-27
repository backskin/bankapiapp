package backskin.bankapi.dao;

import backskin.bankapi.dao.mappers.SqlMapper;
import backskin.bankapi.models.SqlModel;

import java.sql.Connection;

public interface SqlDAO<T extends SqlModel> extends CRUD<T, Long>{
    String getTableName();
    Connection getConnection();
    SqlMapper<T> getMapper();
}
