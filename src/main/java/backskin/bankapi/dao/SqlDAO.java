package backskin.bankapi.dao;

import backskin.bankapi.dao.mappers.SqlMapper;
import backskin.bankapi.models.SqlModel;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The interface Sql dao.
 *
 * @param <T> the type parameter
 */
public interface SqlDAO<T extends SqlModel> extends CRUD<T, Long>{
    /**
     * Gets table name.
     *
     * @return the table name
     */
    String getTableName();

    /**
     * Gets connection.
     *
     * @return the connection
     */
    Connection getConnection();

    /**
     * Gets mapper.
     *
     * @return the mapper
     */
    SqlMapper<T> getMapper();
    @Override
    T create(T entity) throws SQLException;
    @Override
    T read(Long id) throws SQLException;
    @Override
    void update(T entity, Long id) throws SQLException;
    @Override
    void delete(T entity) throws SQLException;
}
