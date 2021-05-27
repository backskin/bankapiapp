package backskin.bankapi.dao;


import backskin.bankapi.dao.mappers.AbstractSqlMapper;
import backskin.bankapi.models.SqlModel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.*;

@Builder
@Component
@RequiredArgsConstructor
public abstract class AbstractDAO<T extends SqlModel> implements SqlDAO<T> {

    @Builder.Default
    private final String idColumnName = "ID";
    public abstract AbstractSqlMapper<T> getMapper();

    @Override
    public void create(T entity) throws SQLException {
        String sqlQuery = "INSERT INTO ? (?) VALUES(?)";
        PreparedStatement statement = getConnection().prepareStatement(sqlQuery);
        statement.setString(1, getTableName());
        statement.setString(2, entity.fields());
        statement.setString(3, entity.values());
        statement.execute();
        getConnection().commit();
    }

    @Override
    public T read(Long id) throws SQLException, NullPointerException {
        String sqlQuery = "SELECT * FROM ? WHERE ?";
        PreparedStatement statement = getConnection().prepareStatement(sqlQuery);
        statement.setString(1, getTableName());
        statement.setString(2, getMapper().getIdValidator().validationRule(id));
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()){
            throw new NullPointerException(
                    String.format("Объект с (id=%d) в таблице '%s' не найден", id, getTableName()));
        };
        T output = getMapper().map(resultSet);
        getConnection().commit();
        return output;
    }

    @Override
    public void delete(T entity) throws SQLException, NullPointerException {
        String sqlQuery = "DELETE FROM ? WHERE ?";
        PreparedStatement statement = getConnection().prepareStatement(sqlQuery);
        statement.setString(1, getTableName());
        statement.setString(2, getMapper().getIdValidator().validationRule(entity.getId()));
        int i = statement.executeUpdate();
        if (i == 0) throw new NullPointerException();
        getConnection().commit();
    }
}
