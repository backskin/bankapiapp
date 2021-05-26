package backskin.entities;

import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;

@Getter
@Builder(builderClassName = "DebitCardBuilder")
public class DebitCardEntity extends BaseEntity {
    private final String number;
    private final BigInteger bankAccountId;
    private final String expirationDate;
    private final String cvvCode;

    public DebitCardEntity(int id, String number, BigInteger bankAccountId, String expirationDate, String cvvCode) {
        super(id);
        this.number = number;
        this.bankAccountId = bankAccountId;
        this.expirationDate = expirationDate;
        this.cvvCode = cvvCode;
    }
}
