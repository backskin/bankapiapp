package backskin.bankapi.domain;

import backskin.bankapi.models.AbstractModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * The type Debit card.
 */
@Getter
@Setter
public class DebitCard extends AbstractModel implements Serializable {
    private String number;
    private Long bankAccountId;
    private String expirationDate;
    private String cvvCode;

    /**
     * Instantiates a new Debit card.
     *
     * @param id             the id
     * @param number         the number
     * @param bankAccountId  the bank account id
     * @param expirationDate the expiration date
     * @param cvvCode        the cvv code
     */
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
