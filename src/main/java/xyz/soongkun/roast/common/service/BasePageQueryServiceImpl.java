package xyz.soongkun.roast.common.service;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.soongkun.roast.common.dao.PageDao;

@Service("basePageQueryService")
public class BasePageQueryServiceImpl<T> implements BasePageQueryService {
    @Autowired
    private PageDao pageDao;

    public BasePageQueryServiceImpl() {
    }

    public List<T> getDataPages(DetachedCriteria criteria, int page) {
        return this.pageDao.getDataPages(criteria, page * BasePageQueryService.MAX_PAGE_ITEMS, BasePageQueryService.MAX_PAGE_ITEMS);
    }

    public long getDataPagesCount(DetachedCriteria criteria) {
        return this.pageDao.getDataPagesCount(criteria);
    }
}
