package backskin.bankapi.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Builder(builderClassName = "LocalTransactionBuilder")
public class AccountLocalTransaction extends AbstractModel {
    private final BigDecimal transactionDifference;
    private final BigInteger bankAccountId;
    @Builder.Default
    private final Timestamp date = Timestamp.from(Instant.now());
    @Setter
    @Builder.Default
    private String details = "[{}]";

    @Override
    public String fields() {
        return String.join(",",
                "id",
                "bank_account_id",
                "difference",
                "date",
                "details");
    }

    @Override
    public String values() {
        return String.join(",",
                getId().toString(),
                bankAccountId.toString(),
                transactionDifference.toString(),
                date.toString(),
                details);
    }
}
