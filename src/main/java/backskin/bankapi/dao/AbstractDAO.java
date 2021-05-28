package backskin.bankapi.dao;


import backskin.bankapi.dao.mappers.AbstractSqlMapper;
import backskin.bankapi.models.AbstractModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public abstract class AbstractDAO<T extends AbstractModel> implements SqlDAO<T> {

    public abstract AbstractSqlMapper<T> getMapper();

    @Override
    public T create(T entity) throws SQLException {
        System.out.println("3");
        String sqlQuery = String.format("INSERT INTO %s (%s) VALUES(%s)",
                getTableName(), entity.fields(), entity.values());
        PreparedStatement statement = getConnection().prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
        int row = statement.executeUpdate();
        if (row < 1){
            throw new SQLException("execution of query failed");
        }
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()){
            entity.setId(rs.getObject("id", Long.class));
            getConnection().commit();
            return entity;
        } else {
            getConnection().rollback();
            throw new SQLException("Entity creation failed");
        }
    }

    @Override
    public T read(Long id) throws SQLException, NullPointerException {
        String sqlQuery = String.format("SELECT * FROM %s WHERE %s",
                getTableName(), getMapper().getIdValidator().validationRule(id));
        PreparedStatement statement = getConnection().prepareStatement(sqlQuery);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()){
            getConnection().rollback();
            throw new NullPointerException("ENTITY_NOT_FOUND");
        };
        T output = getMapper().map(resultSet);
        getConnection().commit();
        return output;
    }

    @Override
    public void delete(T entity) throws SQLException, NullPointerException {
        String sqlQuery = String.format("DELETE FROM %s WHERE %s",
                getTableName(), getMapper().getIdValidator().validationRule(entity.getId()));
        PreparedStatement statement = getConnection().prepareStatement(sqlQuery);
        int i = statement.executeUpdate();
        if (i == 0) {
            getConnection().rollback();
            throw new NullPointerException("Entity deletion failed");
        }
        getConnection().commit();
    }
}
