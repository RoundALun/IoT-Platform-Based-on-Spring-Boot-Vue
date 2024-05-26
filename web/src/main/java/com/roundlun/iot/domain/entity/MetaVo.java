package com.roundlun.iot.domain.entity;

public class MetaVo {
    private String title;
    private Boolean auth;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getAuth() {
        return auth;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    public MetaVo()
    {
    }
    public MetaVo(String title)
    {
        this.title = title;
    }
    public MetaVo(String title, boolean auth)
    {
        this.title = title;
        this.auth = auth;
    }

}
