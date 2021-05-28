package backskin.bankapi.domain;

import backskin.bankapi.models.AbstractModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

/**
 * The type Bank account.
 */
@Getter
public class BankAccount extends AbstractModel {

    @NonNull
    private final String number;
    @Setter
    @NonNull
    private Long bankClientId;
    @Setter
    private BigDecimal balance;

    /**
     * Instantiates a new Bank account.
     *
     * @param id           the id
     * @param number       the number
     * @param bankClientId the bank client id
     * @param balance      the balance
     */
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

    @Override
    public String fields() {
        return String.join(",",
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
