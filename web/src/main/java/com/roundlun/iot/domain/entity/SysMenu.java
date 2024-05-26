package com.roundlun.iot.domain.entity;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SysMenu {
    private static final long serialVersionUID = 1L;

    private Integer menuId;
    private String component;
    private String path;
    private String redirect;
    private String name;
    private String title;
    private String icon;
    private Integer parentId;
    private String isLeaf;
    private Boolean hidden;
    private Boolean noCache;

    private Boolean link;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<SysMenu> children;

    public List<SysMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }

    private Map<String,Object> meta;
    public Map<String,Object> getMeta(){
        meta = new HashMap<>();
        meta.put("title",title);
        meta.put("noCache",noCache);
        meta.put("link",link);
        meta.put("icon",icon);
        return meta;
    }


    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }



    public void setMeta(Map<String, Object> meta) {
        this.meta = meta;
    }

    public Boolean getNoCache() {
        return noCache;
    }

    public void setNoCache(Boolean noCache) {
        this.noCache = noCache;
    }

    public Boolean getLink() {
        return link;
    }

    public void setLink(Boolean link) {
        this.link = link;
    }

    //    @Override
//    public String toString() {
//        return "SysMenu{" +
//                "menuId=" + menuId +
//                ", component='" + component + '\'' +
//                ", path='" + path + '\'' +
//                ", redirect='" + redirect + '\'' +
//                ", name='" + name + '\'' +
//                ", title='" + title + '\'' +
//                ", icon='" + icon + '\'' +
//                ", parentId=" + parentId +
//                ", isLeaf='" + isLeaf + '\'' +
//                ", hidden=" + hidden +
//                '}';
//    }
}
