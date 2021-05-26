package backskin.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@Builder(builderClassName = "BankClientBuilder")
public class BankClientEntity extends BaseEntity{
    private String fullName;
    @Builder.Default
    private String phoneNumber = "8(800)555-35-35";
    private BigInteger passportId;
}
