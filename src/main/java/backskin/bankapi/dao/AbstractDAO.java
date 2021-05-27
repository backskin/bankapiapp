package backskin.bankapi.dao;


import backskin.bankapi.domain.AbstractModel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Builder
@Component
@RequiredArgsConstructor
public abstract class AbstractDAO<T extends AbstractModel> implements DAO<T,Long>{

    private Connection connection;
    protected abstract String getTableName();
    protected abstract RowMapper<T> getMapper();
    @Builder.Default
    private final String idColumnName = "ID";

    @Builder.Default
    protected final CreatorWithSQLException<Statement> statementCreator =
            () -> connection.createStatement();

    @Override
    public void create(T entity) throws SQLException {
        Statement statement = statementCreator.create();
        statement.execute(
                "INSERT INTO "+getTableName()+"("+entity.fields()+")"
                        +" VALUES("+entity.values()+");"
        );
        connection.commit();
    }

    @Override
    public T read(Long id) throws SQLException {
        Statement statement = statementCreator.create();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM "+getTableName()
                +" WHERE "+idColumnName+" = "+id.toString()+";");
        resultSet.next();
        T output = getMapper().mapRow(resultSet,resultSet.getRow());
        connection.commit();
        return output;
    }

    @Override
    public void delete(T entity) throws SQLException {
        Statement statement = statementCreator.create();
        statement.execute("DELETE FROM "+getTableName()
                +" WHERE "+idColumnName+" = "+entity.getId()+";");
    }
}
