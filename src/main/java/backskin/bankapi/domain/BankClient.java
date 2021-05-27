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

    @Override
    public String fields() {
        return String.join(",",
                "id",
                "full_name",
                "phone_number",
                "passport_id");
    }

    @Override
    public String values() {
        return String.join(",",
                getId().toString(),
                fullName,
                phoneNumber,
                passportId.toString());
    }
}
