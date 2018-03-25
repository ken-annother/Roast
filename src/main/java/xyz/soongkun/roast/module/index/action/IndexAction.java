package xyz.soongkun.roast.module.index.action;

import com.opensymphony.xwork2.ActionSupport;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import xyz.soongkun.roast.common.model.PageBean;
import xyz.soongkun.roast.module.t_poster.model.Poster;
import xyz.soongkun.roast.module.t_poster.service.PosterService;

@Controller
@Scope("prototype")
public class IndexAction extends ActionSupport {
    @Autowired
    private PosterService posterService;
    private PageBean<Poster> data;
    private int page;

    public IndexAction() {
    }

    public PageBean<Poster> getData() {
        return this.data;
    }

    public String execute() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Poster.class);
        criteria.addOrder(Order.desc("postDate"));
        this.data = this.posterService.getPosterData(criteria, this.page <= 0 ? 0 : this.page - 1);
        return super.execute();
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
