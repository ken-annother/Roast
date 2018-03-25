package xyz.soongkun.roast.common.fillter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class MyHttpServletResponseWrapper extends HttpServletResponseWrapper{

    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private PrintWriter printWriter = new PrintWriter(byteArrayOutputStream);

    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response
     * @throws IllegalArgumentException if the response is null
     */
    public MyHttpServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }


    @Override
    public PrintWriter getWriter() throws IOException {
        return printWriter;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new ServletOutputStream(){

            @Override
            public void write(int b) throws IOException {
                byteArrayOutputStream.write(b);
            }
        };
    }


    public void flush(){
        try {
            printWriter.flush();
            printWriter.close();
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取字节流
     * @return
     */
    public ByteArrayOutputStream getByteArrayOutputStream(){
        return byteArrayOutputStream;
    }

    /**
     * 将换出区内容转为文本输出
     * @return
     */
    public String getTextContent() {
        flush();
        return byteArrayOutputStream.toString();
    }
}
