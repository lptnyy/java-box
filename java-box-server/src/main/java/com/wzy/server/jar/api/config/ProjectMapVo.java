package com.wzy.server.jar.api.config;

import java.util.List;

public class ProjectMapVo {
    BoxProjectVo projectVo;
    List<BoxMoudulaVo> moudulaVos;

    public BoxProjectVo getProjectVo() {
        return projectVo;
    }

    public void setProjectVo(BoxProjectVo projectVo) {
        this.projectVo = projectVo;
    }

    public List<BoxMoudulaVo> getMoudulaVos() {
        return moudulaVos;
    }

    public void setMoudulaVos(List<BoxMoudulaVo> moudulaVos) {
        this.moudulaVos = moudulaVos;
    }
}
