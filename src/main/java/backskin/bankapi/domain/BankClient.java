package backskin.bankapi.domain;

import backskin.bankapi.models.AbstractModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Bank client.
 */
@Getter
@Setter
public class BankClient extends AbstractModel {
    private String fullName;
    @Builder.Default
    private String phoneNumber = "8(800)555-35-35";
    private Long passportId;

    /**
     * Instantiates a new Bank client.
     *
     * @param id          the id
     * @param fullName    the full name
     * @param phoneNumber the phone number
     * @param passportId  the passport id
     */
    @Builder
    public BankClient(Long id, String fullName, String phoneNumber, Long passportId) {
        super(id);
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.passportId = passportId;
    }

    @Override
    public String fields() {
        return String.join(",",
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
