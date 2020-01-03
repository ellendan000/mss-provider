package cn.shadow.mss.provider.domain.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Account {
    private String bid;
    private Double amount;

    public Account(String bid, Double amount) {
        this.bid = bid;
        this.amount = amount;
    }
}
