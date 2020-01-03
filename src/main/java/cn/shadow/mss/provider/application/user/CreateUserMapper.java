package cn.shadow.mss.provider.application.user;

import cn.shadow.mss.provider.domain.user.AccountNotFound;
import cn.shadow.mss.provider.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreateUserMapper {
    CreateUserMapper INSTANCE = Mappers.getMapper(CreateUserMapper.class);

    @Mapping(source = "name", target = "bid.realName")
    @Mapping(source = "identificationCardNumber", target = "bid.cardNumber")
    @Mapping(source = "mobileNumber", target = "contactInfo.mobileNumber")
    User toUser(CreateUserCommand command);

    default UserCreatedDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        return UserCreatedDTO.builder()
                .userId(user.getOid())
                .accountId(
                        Optional.ofNullable(user.getAccounts().get(0))
                                .orElseThrow(AccountNotFound::new).getBid())
                .build();
    }
}
