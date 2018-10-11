package com.wzy.server.jar.loader.config;

import com.wzy.server.jar.api.config.BoxApp;
import com.wzy.server.jar.api.config.BoxAppApi;

import java.util.List;

public class ScanJar {
    List<BoxApp> boxProjectVo;
    List<BoxAppApi> boxApiVoList;

    public List<BoxApp> getBoxProjectVo() {
        return boxProjectVo;
    }

    public void setBoxProjectVo(List<BoxApp> boxProjectVo) {
        this.boxProjectVo = boxProjectVo;
    }

    public List<BoxAppApi> getBoxApiVoList() {
        return boxApiVoList;
    }

    public void setBoxApiVoList(List<BoxAppApi> boxApiVoList) {
        this.boxApiVoList = boxApiVoList;
    }
}
