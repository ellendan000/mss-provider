package cn.shadow.mss.provider.application.user;

import cn.shadow.mss.provider.domain.user.Account;
import cn.shadow.mss.provider.domain.user.User;
import cn.shadow.mss.provider.domain.user.UserRepository;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    void createUser() {
        CreateUserCommand command = CreateUserCommand.builder()
                .name("test_real_name")
                .mobileNumber("18612345678")
                .identificationCardNumber("test_card_number")
                .build();
        Account givenAccount = new Account("test_account_id", 0.1D);
        when(userRepository.createUser(any(User.class))).thenReturn(User.builder()
                .oid(1L)
                .accounts(Lists.newArrayList(givenAccount))
                .build()
        );

        UserCreatedDTO dto = userService.createUser(command);
        assertEquals(new Long(1), dto.getUserId());
        assertEquals(givenAccount.getBid(), dto.getAccountId());
    }
}