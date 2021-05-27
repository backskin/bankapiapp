package backskin.bankapi.dao;


import backskin.bankapi.dao.mappers.AbstractMapper;
import backskin.bankapi.models.AbstractModel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.*;

@Builder
@Component
@Getter
@RequiredArgsConstructor
public abstract class AbstractDAO<T extends AbstractModel> implements DAO<T,Long>{

    private Connection connection;
    @Builder.Default
    private final String idColumnName = "ID";

    protected abstract String getTableName();
    public abstract AbstractMapper<T> getMapper();
    @Autowired
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(T entity) throws SQLException {
        String sqlQuery = "INSERT INTO ? (?) VALUES(?)";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setString(1, getTableName());
        statement.setString(2, entity.fields());
        statement.setString(3, entity.values());
        statement.execute();
        connection.commit();
    }

    @Override
    public T read(Long id) throws SQLException {
        String sqlQuery = "SELECT * FROM ? WHERE ?";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setString(1, getTableName());
        statement.setString(2, AbstractMapper.getIdValidator().validationRule(id));
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        T output = getMapper().mapRow(resultSet,resultSet.getRow());
        connection.commit();
        return output;
    }

    @Override
    public void delete(T entity) throws SQLException {
        String sqlQuery = "DELETE FROM ? WHERE ?";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setString(1, getTableName());
        statement.setString(2, AbstractMapper.getIdValidator().validationRule(entity.getId()));
        statement.execute();
        connection.commit();
    }
}
