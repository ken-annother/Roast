package xyz.soongkun.roast.common.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.Map;

public class BaseJsonActionSupport extends ActionSupport {
    protected static final int REQUEST_SUCCESS = 200;
    protected static final int REQUEST_ERROR = 400;
    protected static final int REQUEST_ERROR_TYPE = 4001;
    private Map<String, Object> data;

    public BaseJsonActionSupport() {
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    protected void setJsonResult(int code, String msg) {
        this.data = new HashMap();
        this.data.put("code", code);
        this.data.put("msg", msg);
    }
}