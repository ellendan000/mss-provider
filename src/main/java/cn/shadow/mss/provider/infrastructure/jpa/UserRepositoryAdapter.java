package cn.shadow.mss.provider.infrastructure.jpa;

import cn.shadow.mss.provider.domain.user.User;
import cn.shadow.mss.provider.domain.user.UserRepository;
import cn.shadow.mss.provider.infrastructure.jpa.mapper.UserMapper;
import cn.shadow.mss.provider.infrastructure.jpa.po.UserPO;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final UserJPARepository jpaRepository;
    private static final UserMapper USER_MAPPER = UserMapper.INSTANCE;

    public UserRepositoryAdapter(UserJPARepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User createUser(User user) {
        UserPO userPO = USER_MAPPER.toUserPO(user);
        return USER_MAPPER.toUser(jpaRepository.save(userPO));
    }
}
