package cn.shadow.mss.provider.domain.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ContactInfo {
    private String mobileNumber;

    public ContactInfo(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
