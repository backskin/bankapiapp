package backskin.bankapi.domain;

import backskin.bankapi.models.AbstractModel;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
public class BankAccount extends AbstractModel {

    @Builder(builderClassName = "BankAccountBuilder")
    public BankAccount(@NotNull Long id,
                       @NotNull String number,
                       @NotNull Long bankClientId,
                       BigDecimal balance) {
        super(id);
        this.number = number;
        this.bankClientId = bankClientId;
        this.balance = balance;
    }

    @NonNull
    private final String number;
    @Setter
    @NonNull
    private Long bankClientId;
    @Setter
    private BigDecimal balance;

    @Override
    public String fields() {
        return String.join(",",
                "id",
                "number",
                "bank_client_id",
                "balance");
    }

    @Override
    public String values() {
        return String.join(",",
                getId().toString(),
                number,
                bankClientId.toString(),
                balance.toString());
    }
}
