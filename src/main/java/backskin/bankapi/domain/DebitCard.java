package backskin.bankapi.domain;

import backskin.bankapi.models.AbstractModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Debit Card Class
 * @author backskin
 * This is the model of Bank's debit cards.
 * Erery field is final, because that is so in the real bank.
 */
@Getter
public class DebitCard extends AbstractModel {
    private final String number;
    @Setter
    private Long bankAccountId;
    private final String expirationDate;
    private final String cvvCode;

    @Builder
    public DebitCard(Long id, String number, Long bankAccountId, String expirationDate, String cvvCode) {
        super(id);
        this.number = number;
        this.bankAccountId = bankAccountId;
        this.expirationDate = expirationDate;
        this.cvvCode = cvvCode;
    }

    @Override
    public String fields() {
        return String.join(",", "number", "bank_account_id", "expiration_date", "cvv_code");
    }

    @Override
    public String values() {
        return String.join(",", number, bankAccountId.toString(), expirationDate, cvvCode);
    }
}
