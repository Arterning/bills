package cn.ning.money.book.config.filter;

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

/**
 * ReHttpServletRequestWrapper 类通过自定义的方式包装了 HttpServletRequest，重写了其中的 getInputStream 和 getReader 方法，
 * 以提供对请求体的多次读取支持。这种自定义包装器的用例通常包括对请求体进行处理、修改或记录，以及在多次读取请求体内容时的一些特定需求。
 */
public class ReHttpServletRequestWrapper extends HttpServletRequestWrapper {

    /**
     * 把请求体缓存起来，以便多次读取，而不会影响后续的处理。
     */
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
