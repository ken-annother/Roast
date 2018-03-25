package xyz.soongkun.roast.module.t_poster.dao;

import org.hibernate.criterion.DetachedCriteria;
import xyz.soongkun.roast.module.t_poster.model.Poster;

import java.util.List;

public interface PosterDao {
    void saveContent(Poster var1);
    List<Poster> query(DetachedCriteria detachedCriteria, Integer start, Integer pageCount);
    Integer queryCount();
}
