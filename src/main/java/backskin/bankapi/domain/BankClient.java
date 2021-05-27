package backskin.bankapi.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@Builder(builderClassName = "BankClientBuilder")
public class BankClient extends AbstractModel {
    private String fullName;
    @Builder.Default
    private String phoneNumber = "8(800)555-35-35";
    private BigInteger passportId;
}
