package cn.shadow.mss.provider.interfaces.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {
    private String name;
    private String identificationCardNumber;
    private String mobileNumber;
}
