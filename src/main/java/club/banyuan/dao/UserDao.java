package club.banyuan.dao;

import club.banyuan.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    User selectUserByName(String name);
}
