package backskin.bankapi.domain;

import backskin.bankapi.models.AbstractModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@Getter
public class LocalTransaction extends AbstractModel {
    private final BigDecimal transactionDifference;
    @NonNull
    private final Long bankAccountId;
    private final Timestamp date;
    @Setter
    private String details;

    @Builder
    public LocalTransaction(@NonNull Long id,
                            BigDecimal transactionDifference,
                            Timestamp date,
                            @NonNull Long bankAccountId,
                            String details) {
        super(id);
        this.transactionDifference = transactionDifference;
        this.bankAccountId = bankAccountId;
        this.date = date;
        this.details = details;
    }
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
