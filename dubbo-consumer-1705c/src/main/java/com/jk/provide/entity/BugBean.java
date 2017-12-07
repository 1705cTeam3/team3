package com.jk.provide.entity;

import java.io.Serializable;
import java.util.Date;

public class BugBean implements Serializable{
    private static final long serialVersionUID = 8047225120644532121L;

	private Integer bugId;

    private String status;

    private String attachmentUrl;

    private String bugName;

    private String description;

    private Date created;

    private Date lastmod;

    private Integer creater;

    private Integer modifyer;

    public Integer getBugId() {
        return bugId;
    }

    public void setBugId(Integer bugId) {
        this.bugId = bugId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl == null ? null : attachmentUrl.trim();
    }

    public String getBugName() {
        return bugName;
    }

    public void setBugName(String bugName) {
        this.bugName = bugName == null ? null : bugName.trim();
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
		return "Bug [bugId=" + bugId + ", status=" + status + ", attachmentUrl=" + attachmentUrl + ", bugName="
				+ bugName + ", description=" + description + ", created=" + created + ", lastmod=" + lastmod
				+ ", creater=" + creater + ", modifyer=" + modifyer + "]";
	}
    
    
}