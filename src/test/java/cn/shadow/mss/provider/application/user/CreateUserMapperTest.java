package cn.shadow.mss.provider.application.user;

import cn.shadow.mss.provider.domain.user.Account;
import cn.shadow.mss.provider.domain.user.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CreateUserMapperTest {
    private final CreateUserMapper mapper = CreateUserMapper.INSTANCE;

    @Test
    void should_return_null_when_no_exists_user() {
        assertNull(mapper.toDTO(null));
    }

    @Test
    void should_return_dto_correctly(){
        Account givenAccount = new Account("test_account_id", 0.1D);
        User givenUser = User.builder()
                .oid(1L)
                .accounts(Lists.newArrayList(givenAccount))
                .build();

        UserCreatedDTO dto = mapper.toDTO(givenUser);
        assertEquals(givenUser.getOid(), dto.getUserId());
        assertEquals(givenAccount.getBid(), dto.getAccountId());
    }

}