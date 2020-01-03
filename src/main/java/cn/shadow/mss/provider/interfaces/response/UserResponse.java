package cn.shadow.mss.provider.interfaces.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private String oid;
    private String name;
    private String identificationCardNumber;
    private String mobileNumber;
}
