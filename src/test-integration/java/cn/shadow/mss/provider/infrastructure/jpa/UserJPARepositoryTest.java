package cn.shadow.mss.provider.infrastructure.jpa;

import cn.shadow.mss.provider.infrastructure.jpa.po.AccountPO;
import cn.shadow.mss.provider.infrastructure.jpa.po.UserPO;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserJPARepositoryTest extends JPABaseTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserJPARepository userJPARepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void should_save_user_with_one_account_correctly() {
        AccountPO givenAccountPO = AccountPO.builder()
                .bid("test_account_id")
                .amount(0.5D)
                .build();
        UserPO givenUserPO = UserPO.builder()
                .realName("test_real_name")
                .identificationNumber("test_card_number")
                .mobileNumber("18612345678")
                .accounts(Lists.newArrayList(givenAccountPO))
                .build();
        userJPARepository.save(givenUserPO);

        UserPO userPO = entityManager.find(UserPO.class, givenUserPO.getOid());
        assertEquals(1, userPO.getAccounts().size());
    }

}