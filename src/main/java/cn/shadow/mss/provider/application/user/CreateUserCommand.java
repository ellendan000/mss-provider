package cn.shadow.mss.provider.application.user;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserCommand {
    private String name;
    private String identificationCardNumber;
    private String mobileNumber;
}
