package cn.shadow.mss.provider.infrastructure.jpa.po;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class AccountPO {
    @Id
    private String bid;
    private Double amount;
}
