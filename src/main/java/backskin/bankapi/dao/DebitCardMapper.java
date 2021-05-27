package backskin.bankapi.dao;

import backskin.bankapi.domain.DebitCard;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DebitCardMapper implements RowMapper<DebitCard> {
    @Override
    public DebitCard mapRow(@NotNull ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
