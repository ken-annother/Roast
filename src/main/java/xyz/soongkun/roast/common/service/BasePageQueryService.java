package xyz.soongkun.roast.common.service;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;

public interface BasePageQueryService<T> {
    int MAX_PAGE_ITEMS = 5;

    List<T> getDataPages(DetachedCriteria criteria, int page);

    long getDataPagesCount(DetachedCriteria criteria);
}
