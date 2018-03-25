package xyz.soongkun.roast.module.t_user.dao;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import xyz.soongkun.roast.module.t_user.model.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Resource
    private HibernateTemplate hibernateTemplate;

    public UserDaoImpl() {
    }

    public void addUser(User user) {
        hibernateTemplate.save(user);
    }

    public boolean checkEmailExist(String email) {
        List<User> users = (List<User>) hibernateTemplate.find("FROM User WHERE email = ?", email);
        return users.size() > 0;
    }

    public User getUserByEmail(String email) {
        List<User> users = (List<User>) hibernateTemplate.find("FROM User WHERE email = ?", email);
        return users.size() == 0 ? null : (User)users.get(0);
    }
}
