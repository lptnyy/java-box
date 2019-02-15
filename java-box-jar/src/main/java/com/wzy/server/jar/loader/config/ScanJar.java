package com.wzy.server.jar.loader.config;

import com.wzy.server.jar.api.config.BoxApp;
import com.wzy.server.jar.api.config.BoxAppApi;
import com.wzy.server.jar.api.config.BoxDataSource;
import com.wzy.server.jar.api.config.BoxFilter;

import java.util.List;
import java.util.Map;

public class ScanJar {
    List<BoxApp> boxProjectVo;
    List<BoxAppApi> boxApiVoList;
    List<BoxFilter> boxFilters;
    List<BoxDataSource> boxDataSources;
    Map<String,String> configs;
    public List<BoxApp> getBoxProjectVo() {
        return boxProjectVo;
    }

    public void setBoxProjectVo(List<BoxApp> boxProjectVo) {
        this.boxProjectVo = boxProjectVo;
    }

    public List<BoxFilter> getBoxFilters() {
        return boxFilters;
    }

    public void setBoxFilters(List<BoxFilter> boxFilters) {
        this.boxFilters = boxFilters;
    }

    public List<BoxAppApi> getBoxApiVoList() {
        return boxApiVoList;
    }

    public List<BoxDataSource> getBoxDataSources() {
        return boxDataSources;
    }

    public Map<String, String> getConfigs() {
        return configs;
    }

    public void setConfigs(Map<String, String> configs) {
        this.configs = configs;
    }

    public void setBoxDataSources(List<BoxDataSource> boxDataSources) {
        this.boxDataSources = boxDataSources;
    }

    public void setBoxApiVoList(List<BoxAppApi> boxApiVoList) {
        this.boxApiVoList = boxApiVoList;
    }
}
