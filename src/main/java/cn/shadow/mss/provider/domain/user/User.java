package cn.shadow.mss.provider.domain.user;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    private Long oid;

    private Identification bid;

    private ContactInfo contactInfo;

    private List<Account> accounts;
}
