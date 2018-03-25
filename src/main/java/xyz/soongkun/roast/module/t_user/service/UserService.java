package xyz.soongkun.roast.module.t_user.service;

import xyz.soongkun.roast.module.t_user.model.User;

public interface UserService {
    void registerByEmail(User var1) throws Exception;

    User getUserByEmail(String var1);
}
