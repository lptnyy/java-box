package com.wzy.server.jar.loader.config;

import java.util.List;

public class ScanJar {
    List<BoxProjectVo> boxProjectVo;
    List<BoxMoudulaVo> boxMoudulaVoList;
    List<BoxApiVo> boxApiVoList;

    public List<BoxMoudulaVo> getBoxMoudulaVoList() {
        return boxMoudulaVoList;
    }

    public void setBoxMoudulaVoList(List<BoxMoudulaVo> boxMoudulaVoList) {
        this.boxMoudulaVoList = boxMoudulaVoList;
    }

    public List<BoxProjectVo> getBoxProjectVo() {
        return boxProjectVo;
    }

    public void setBoxProjectVo(List<BoxProjectVo> boxProjectVo) {
        this.boxProjectVo = boxProjectVo;
    }

    public List<BoxApiVo> getBoxApiVoList() {
        return boxApiVoList;
    }

    public void setBoxApiVoList(List<BoxApiVo> boxApiVoList) {
        this.boxApiVoList = boxApiVoList;
    }
}
