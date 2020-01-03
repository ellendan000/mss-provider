package cn.shadow.mss.provider.domain.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Identification {
    private String realName;
    private String cardNumber;

    public Identification(String realName, String cardNumber) {
        this.realName = realName;
        this.cardNumber = cardNumber;
    }
}
