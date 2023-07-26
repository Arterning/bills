package cn.ning.money.book.common.config.filter;

import cn.ning.money.book.utils.ServletUtil;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class ReHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private final byte[] bodyBuf;

    public ReHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        bodyBuf = ServletUtil.getBodyString(request).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream stream = new ByteArrayInputStream(bodyBuf);

        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return stream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
}
