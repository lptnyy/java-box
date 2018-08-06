package com.wzy.server.request;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class BoxHttpRequestImpl implements BoxHttpRequest {
    Map<String,Object> requestMaps = new HashMap<>();

    @Override
    public String getParameter(String name) {
        return null;
    }

    @Override
    public void setParameter(String name, String value) {

    }

    @Override
    public String[] getParameterNames() {
        return new String[0];
    }

    @Override
    public String[] getParameterValues(String name) {
        return new String[0];
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public int getContentLength() {
        return 0;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }

    @Override
    public int getInputStreamLength() {
        return 0;
    }

    @Override
    public String uri() {
        return null;
    }

    @Override
    public boolean isCache() {
        return false;
    }

    @Override
    public String getMethod() {
        return null;
    }
}
