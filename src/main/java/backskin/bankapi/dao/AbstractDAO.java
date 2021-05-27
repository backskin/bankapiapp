package backskin.bankapi.dao;


import backskin.bankapi.dao.mappers.AbstractMapper;
import backskin.bankapi.models.AbstractModel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Builder
@Component
@RequiredArgsConstructor
public abstract class AbstractDAO<T extends AbstractModel> implements CRUD<T,Long>, SqlDAO<T> {

    @Builder.Default
    private final String idColumnName = "ID";

    public abstract AbstractMapper<T> getMapper();

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
    public T read(Long id) throws SQLException {
        String sqlQuery = "SELECT * FROM ? WHERE ?";
        PreparedStatement statement = getConnection().prepareStatement(sqlQuery);
        statement.setString(1, getTableName());
        statement.setString(2, getMapper().getIdValidator().validationRule(id));
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        T output = getMapper().map(resultSet);
        getConnection().commit();
        return output;
    }

    @Override
    public void delete(T entity) throws SQLException {
        String sqlQuery = "DELETE FROM ? WHERE ?";
        PreparedStatement statement = getConnection().prepareStatement(sqlQuery);
        statement.setString(1, getTableName());
        statement.setString(2, getMapper().getIdValidator().validationRule(entity.getId()));
        statement.execute();
        getConnection().commit();
    }
}
