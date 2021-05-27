package backskin.bankapi.dao.mappers;

import backskin.bankapi.dao.Validator;
import backskin.bankapi.models.AbstractModel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public abstract class AbstractMapper<T extends AbstractModel> {
    @Qualifier("byId")
    @Getter
    private static Validator<AbstractModel, Long> idValidator;
    public abstract T map(ResultSet resultSet) throws SQLException;
}
