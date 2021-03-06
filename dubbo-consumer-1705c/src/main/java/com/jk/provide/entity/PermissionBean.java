package com.jk.provide.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * <pre>项目名称：dubbo-consumer-1705c    
 * 类名称：PermissionBean    
 * 类描述：    
 * 创建人：lengXiaXi
 * 创建时间：2017年11月23日 下午2:09:54    
 * 修改人：lengXiaXi   
 * 修改时间：2017年11月23日 下午2:09:54    
 * 修改备注：       
 * @version </pre>
 */
public class PermissionBean implements Serializable{
	
    private static final long serialVersionUID = 999549035768592948L;

	private Integer permissionId;

    private Integer pid;

    private String name;

    private String pname;

    private Integer sort;

    private String myid;

    private String type;

    private String isused;

    private String state;

    private String url;

    private String iconcls;

    private String status;

    private String description;

    private Date created;

    private Date lastmod;

    private Integer creater;

    private Integer modifyer;

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getMyid() {
        return myid;
    }

    public void setMyid(String myid) {
        this.myid = myid == null ? null : myid.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getIsused() {
        return isused;
    }

    public void setIsused(String isused) {
        this.isused = isused == null ? null : isused.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIconcls() {
        return iconcls;
    }

    public void setIconcls(String iconcls) {
        this.iconcls = iconcls == null ? null : iconcls.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastmod() {
        return lastmod;
    }

    public void setLastmod(Date lastmod) {
        this.lastmod = lastmod;
    }

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    public Integer getModifyer() {
        return modifyer;
    }

    public void setModifyer(Integer modifyer) {
        this.modifyer = modifyer;
    }

	@Override
	public String toString() {
		return "PermissionBean [permissionId=" + permissionId + ", pid=" + pid + ", name=" + name + ", pname=" + pname
				+ ", sort=" + sort + ", myid=" + myid + ", type=" + type + ", isused=" + isused + ", state=" + state
				+ ", url=" + url + ", iconcls=" + iconcls + ", status=" + status + ", description=" + description
				+ ", created=" + created + ", lastmod=" + lastmod + ", creater=" + creater + ", modifyer=" + modifyer
				+ "]";
	}
    
    
}