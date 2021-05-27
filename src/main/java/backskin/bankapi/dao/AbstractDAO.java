package backskin.bankapi.dao;


import backskin.bankapi.dao.mappers.AbstractSqlMapper;
import backskin.bankapi.models.AbstractModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public abstract class AbstractDAO<T extends AbstractModel> implements SqlDAO<T> {

    public abstract AbstractSqlMapper<T> getMapper();

    @Override
    public T create(T entity) throws SQLException {
        String sqlQuery = "INSERT INTO ? (?) VALUES(?)";
        PreparedStatement statement = getConnection().prepareStatement(sqlQuery);
        statement.setString(1, getTableName());
        statement.setString(2, entity.fields());
        statement.setString(3, entity.values());
        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()){
            entity.setId(generatedKeys.getLong(1));
            getConnection().commit();
            return entity;
        } else {
            throw new SQLException("Entity creation failed");
        }
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
                    String.format("Entity with id=%d in table='%s' not found", id, getTableName()));
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
        if (i == 0) throw new NullPointerException("Entity deletion failed");
        getConnection().commit();
    }
}
