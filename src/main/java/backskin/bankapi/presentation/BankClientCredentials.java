package backskin.bankapi.presentation;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
public class BankClientCredentials implements Serializable {
    private Long passportId;
    private String fullName;
    private String phoneNumber;

    @Builder
    public BankClientCredentials(Long passportId, String fullName, String phoneNumber) {
        this.passportId = passportId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }
}
