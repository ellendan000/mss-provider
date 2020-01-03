package cn.shadow.mss.provider.application.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class UserCreatedDTO {
    private Long userId;
    private String accountId;

    public UserCreatedDTO(Long userId, String accountId) {
        this.userId = userId;
        this.accountId = accountId;
    }
}
