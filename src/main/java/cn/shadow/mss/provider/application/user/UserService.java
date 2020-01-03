package cn.shadow.mss.provider.application.user;

import cn.shadow.mss.provider.domain.user.User;
import cn.shadow.mss.provider.domain.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserCreatedDTO createUser(CreateUserCommand command) {
        User createdUser = repository.createUser(CreateUserMapper.INSTANCE.toUser(command));
        return CreateUserMapper.INSTANCE.toDTO(createdUser);
    }

}
