package backskin.bankapi.presentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Bank client info.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
public class BankClientInfo implements Info{
    private String fullName;
    @Builder.Default
    private String phoneNumber = "8(800)555-35-35";
}
