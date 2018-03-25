package xyz.soongkun.roast.module.t_poster.service;

import org.hibernate.criterion.DetachedCriteria;
import xyz.soongkun.roast.common.model.PageBean;
import xyz.soongkun.roast.module.t_poster.model.Poster;
import xyz.soongkun.roast.module.t_user.model.User;

public interface PosterService {
    void saveContent(String var1, User var2);
    PageBean<Poster> getPosterData(DetachedCriteria var1, int var2);
}
