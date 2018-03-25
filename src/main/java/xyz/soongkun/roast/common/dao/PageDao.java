package xyz.soongkun.roast.common.dao;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;

public interface PageDao<T> {
    List<T> getDataPages(DetachedCriteria criteria, int firstResult, int maxResults);
    long getDataPagesCount(DetachedCriteria criteria);
}
