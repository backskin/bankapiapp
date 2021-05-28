package backskin.bankapi.presentation;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Bank client credentials.
 */
@Getter
@Setter
@NoArgsConstructor
public class BankClientCredentials implements Credentials {
    private Long passportId;
    private String fullName;
    private String phoneNumber;

    /**
     * Instantiates a new Bank client credentials.
     *
     * @param passportId  the passport id
     * @param fullName    the full name
     * @param phoneNumber the phone number
     */
    @Builder
    public BankClientCredentials(Long passportId, String fullName, String phoneNumber) {
        this.passportId = passportId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }
}
