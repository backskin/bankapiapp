package backskin.bankapi.dao;


import backskin.bankapi.domain.AbstractModel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Builder
@Component
@RequiredArgsConstructor
public abstract class AbstractDAO<T extends AbstractModel, Id> implements DAO<T,Id>{

    private Connection connection;
    protected abstract String getTableName();
    protected abstract RowMapper<T> getMapper();

    @Override
    public void create(T entity) throws SQLException {
        connection.createStatement().execute(
                "UPDATE "+getTableName()+"("+entity.fields()+")"
                        +" VALUES("+entity.values()+");");
    }

    @Override
    public T read(Id id) throws Exception {
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        statement.execute("SELECT * FROM "+getTableName());
        ResultSet resultSet = statement.getResultSet();
        T output = getMapper().mapRow(resultSet,resultSet.getRow());
        connection.commit();
        return output;
    }

    @Override
    public void update(T entity, Id id) throws Exception {

    }

    @Override
    public void delete(T entity) throws Exception {

    }
}
