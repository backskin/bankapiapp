package backskin.bankapi.dao.mappers;

import backskin.bankapi.models.SqlModel;
import backskin.bankapi.validators.Validator;
import backskin.bankapi.models.AbstractModel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public abstract class AbstractSqlMapper<T extends SqlModel> implements SqlMapper<T> {
    @Getter
    private Validator<AbstractModel, Long> idValidator;

    @Autowired
    public void setIdValidator(Validator<AbstractModel, Long> idValidator) {
        this.idValidator = idValidator;
    }

    public abstract T map(ResultSet resultSet) throws SQLException;
}
