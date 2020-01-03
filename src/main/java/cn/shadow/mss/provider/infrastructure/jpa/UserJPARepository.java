package cn.shadow.mss.provider.infrastructure.jpa;

import cn.shadow.mss.provider.infrastructure.jpa.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPARepository extends JpaRepository<UserPO, Long> {
}
