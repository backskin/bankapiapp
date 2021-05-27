package backskin.bankapi.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;

/**
 * Debit Card Class
 * @author backskin
 * This is the model of Bank's debit cards.
 * Erery field is final, because that is so in the real bank.
 */
@Getter
@Builder(builderClassName = "DebitCardBuilder")
public class DebitCard extends AbstractModel {
    private final String number;
    private final BigInteger bankAccountId;
    private final String expirationDate;
    private final String cvvCode;
}
