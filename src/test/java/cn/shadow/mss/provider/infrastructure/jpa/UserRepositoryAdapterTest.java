package cn.shadow.mss.provider.infrastructure.jpa;

import cn.shadow.mss.provider.domain.user.Account;
import cn.shadow.mss.provider.domain.user.ContactInfo;
import cn.shadow.mss.provider.domain.user.Identification;
import cn.shadow.mss.provider.domain.user.User;
import cn.shadow.mss.provider.infrastructure.jpa.mapper.UserMapper;
import cn.shadow.mss.provider.infrastructure.jpa.po.UserPO;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRepositoryAdapterTest {
    private UserRepositoryAdapter userRepositoryAdapter;
    @Mock
    private UserJPARepository jpaRepository;

    @BeforeEach
    void setUp() {
        userRepositoryAdapter = new UserRepositoryAdapter(jpaRepository);
        when(jpaRepository.save(any(UserPO.class))).thenAnswer(i -> i.getArgument(0));
    }

    @Test
    void createUser() {
        User givenUser = User.builder()
                .bid(new Identification("test_real_name", "test_card_number"))
                .contactInfo(new ContactInfo("18612345678"))
                .accounts(Lists.newArrayList(new Account("test_account_id", 0D)))
                .build();
        User savedUser = userRepositoryAdapter.createUser(givenUser);
        assertEquals(givenUser, savedUser);
    }
}