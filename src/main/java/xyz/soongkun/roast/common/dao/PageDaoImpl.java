package xyz.soongkun.roast.common.dao;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository("pageDao")
public class PageDaoImpl<T> implements PageDao {
    @Resource
    private HibernateTemplate hibernateTemplate;

    public PageDaoImpl() {
    }

    public List getDataPages(DetachedCriteria criteria, int firstResult, int maxResults) {
        return this.hibernateTemplate.findByCriteria(criteria, firstResult, maxResults);
    }

    public long getDataPagesCount(DetachedCriteria criteria) {
        List<Long> result = (List<Long>) this.hibernateTemplate.findByCriteria(criteria);
        return (Long)result.get(0);
    }
}
