package backskin.bankapi.presentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class BankClientInfo {
    private String fullName;
    @Builder.Default
    private String phoneNumber = "8(800)555-35-35";
}
