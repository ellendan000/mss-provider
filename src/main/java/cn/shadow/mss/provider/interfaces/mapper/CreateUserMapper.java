package cn.shadow.mss.provider.interfaces.mapper;

import cn.shadow.mss.provider.application.user.CreateUserCommand;
import cn.shadow.mss.provider.application.user.UserCreatedDTO;
import cn.shadow.mss.provider.interfaces.request.CreateUserRequest;
import cn.shadow.mss.provider.interfaces.response.UserCreatedResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface CreateUserMapper {
    CreateUserMapper INSTANCE = Mappers.getMapper(CreateUserMapper.class);

    CreateUserCommand requestToCommand(CreateUserRequest request);

    UserCreatedResponse dtoToResponse(UserCreatedDTO dto);
}
