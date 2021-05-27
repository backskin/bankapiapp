package backskin.bankapi.dao;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Builder
@RequiredArgsConstructor
@Repository
public abstract class AbstractDAO<T, Id> implements DAO<T,Id>, Transactional{

    private final JdbcTemplate jdbcTemplate;
    @Builder.Default
    @Getter
    private boolean transactionFinished = true;

    @Override
    public void beginTransaction() {
        transactionFinished = false;
    }

    @Override
    public void commitTransaction() {
        transactionFinished = true;
    }

    @Override
    public void rollbackTransaction() {

        transactionFinished = true;
    }
}
