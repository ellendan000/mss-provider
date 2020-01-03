package cn.shadow.mss.provider.infrastructure.jpa.po;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserPO {
    @Id
    private Long oid;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "identification_number")
    private String identificationNumber;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<AccountPO> accounts;
}
