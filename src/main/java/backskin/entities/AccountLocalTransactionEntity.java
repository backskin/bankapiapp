package backskin.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

@Getter
@Builder(builderClassName = "LocalTransactionBuilder")
public class AccountLocalTransactionEntity extends BaseEntity{
    private final BigDecimal transactionDifference;
    private final BigInteger bankAccountId;
    private final Timestamp date;
    @Setter
    private String details;
}
