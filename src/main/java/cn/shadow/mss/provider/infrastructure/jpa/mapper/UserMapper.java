package cn.shadow.mss.provider.infrastructure.jpa.mapper;

import cn.shadow.mss.provider.domain.user.Account;
import cn.shadow.mss.provider.domain.user.User;
import cn.shadow.mss.provider.infrastructure.jpa.po.AccountPO;
import cn.shadow.mss.provider.infrastructure.jpa.po.UserPO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "realName", source = "bid.realName")
    @Mapping(target = "identificationNumber", source = "bid.cardNumber")
    @Mapping(target = "mobileNumber", source = "contactInfo.mobileNumber")
    UserPO toUserPO(User user);

    @InheritInverseConfiguration
    User toUser(UserPO userPO);

    List<AccountPO> toAccountPOList(List<Account> accounts);

    List<Account> toAccountList(List<AccountPO> accounts);

    AccountPO toAccountPO(Account account);

    Account toAccount(AccountPO accountPO);
}
