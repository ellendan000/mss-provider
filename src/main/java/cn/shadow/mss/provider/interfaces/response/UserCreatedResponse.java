package cn.shadow.mss.provider.interfaces.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreatedResponse {
    private Long userId;
    private String accountId;
}
