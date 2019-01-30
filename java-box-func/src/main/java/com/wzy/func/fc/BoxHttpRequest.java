package com.wzy.func.fc;

import io.netty.channel.ChannelHandlerContext;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface BoxHttpRequest {
    public Object getParameter(String name);
    public void setParameter(String name, Object value);
    public List<String> getParameterNames();
    public List<Object> getParameterValues();
    public InputStream getInputStream();
    public void setInputStream(InputStream inputStream);
    public int getInputStreamLength();
    public String uri();
    public boolean isCache();
    public String getMethod();
    public void setMethod(String method);
    public void setUri(String uri);
    public Map<String,String> headers();
    public void setHeader(String key, String value);
    public ChannelHandlerContext getChx();
    public void setChx(ChannelHandlerContext chx);
    public long getRunTime();
    public void setRunTime(long times);
    public IConfig getConfig();
    public IConfig setConifg(IConfig config);
}
