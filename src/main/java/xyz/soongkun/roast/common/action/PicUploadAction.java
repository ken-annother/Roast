package xyz.soongkun.roast.common.action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import xyz.soongkun.roast.util.TextUtil;

@Controller
@Scope("prototype")
public class PicUploadAction extends ActionSupport {
    private File file;
    private String[] fileFileName;
    private String[] fileContentType;
    private Map<String, Object> data;

    public PicUploadAction() {
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public String upload() {
        this.data = new HashMap();
        ServletContext servletContext = ServletActionContext.getServletContext();
        String realPath = servletContext.getRealPath("/static/upload/pics");

        try {
            File fileDest = new File(realPath);
            if (!fileDest.exists()) {
                fileDest.mkdirs();
            }

            String newFileName = TextUtil.getRandomFileName(this.fileFileName[0]);
            this.file.renameTo(new File(realPath, newFileName));
            this.data.put("code", 0);
            this.data.put("msg", "上传成功");
            Map<String, String> dataInfo = new HashMap();
            dataInfo.put("src", servletContext.getContextPath() + "/static/upload/pics/" + newFileName);
            dataInfo.put("title", null);
            this.data.put("data", dataInfo);
        } catch (Exception var6) {
            this.data.put("code", 400);
            this.data.put("msg", "上传错误");
        }

        return "success";
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String[] getFileFileName() {
        return this.fileFileName;
    }

    public void setFileFileName(String[] fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String[] getFileContentType() {
        return this.fileContentType;
    }

    public void setFileContentType(String[] fileContentType) {
        this.fileContentType = fileContentType;
    }
}
