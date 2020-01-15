package cn.shadow.mss.provider.interfaces.api;

import cn.shadow.mss.provider.application.user.UserCreatedDTO;
import cn.shadow.mss.provider.application.user.UserService;
import cn.shadow.mss.provider.interfaces.mapper.CreateUserMapper;
import cn.shadow.mss.provider.interfaces.request.CreateUserRequest;
import cn.shadow.mss.provider.interfaces.response.UserCreatedResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserCreatedResponse createUser(@RequestBody CreateUserRequest request) {
        UserCreatedDTO dto = userService.createUser(CreateUserMapper.INSTANCE.requestToCommand(request));
        return CreateUserMapper.INSTANCE.dtoToResponse(dto);
    }

}
