package com.wzy.server.request;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;

public interface BoxHttpRequest {
    public String getParameter(String name);
    public void setParameter(String name,String value);
    public String[] getParameterNames();
    public String[] getParameterValues(String name);
    public String getCharacterEncoding();
    public int getContentLength();
    public String getContentType();
    public InputStream getInputStream() throws IOException;
    public int getInputStreamLength();
    public String uri();
    public boolean isCache();
    public String getMethod();
}
