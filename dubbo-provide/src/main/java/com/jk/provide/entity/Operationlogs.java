package com.jk.provide.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.jk.util.Page;

@Document(collection="operationlogs")
public class Operationlogs extends Page implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 17905552356189433L;
	
	@Id
	private String operlogid;//主键
	private String operloginId;//操作者
	private String operlognsolr;//操作者的角色
	private String opertime;//操作时间
	private String opercontroller;//全类名
	private String operMethod;//方法名
	private Integer tomcatport;//方法所在的行
	private String operParams;//参数
	private String operURI;//方法的请求路径
	private String   haoshi;//耗时
	private String computerIp;//访问使用的设备ip
	private String computerName;//访问使用的设备编号
	
	
	public String getOperlogid() {
		return operlogid;
	}
	public void setOperlogid(String operlogid) {
		this.operlogid = operlogid;
	}
	public String getOperloginId() {
		return operloginId;
	}
	public void setOperloginId(String operloginId) {
		this.operloginId = operloginId;
	}
	public String getOperlognsolr() {
		return operlognsolr;
	}
	public void setOperlognsolr(String operlognsolr) {
		this.operlognsolr = operlognsolr;
	}
	public String getOpertime() {
		return opertime;
	}
	public void setOpertime(String opertime) {
		this.opertime = opertime;
	}
	public String getOpercontroller() {
		return opercontroller;
	}
	public void setOpercontroller(String opercontroller) {
		this.opercontroller = opercontroller;
	}
	public String getOperMethod() {
		return operMethod;
	}
	public void setOperMethod(String operMethod) {
		this.operMethod = operMethod;
	}
	public String getOperParams() {
		return operParams;
	}
	public void setOperParams(String operParams) {
		this.operParams = operParams;
	}
	public String getOperURI() {
		return operURI;
	}
	public void setOperURI(String operURI) {
		this.operURI = operURI;
	}
	public String getComputerIp() {
		return computerIp;
	}
	public void setComputerIp(String computerIp) {
		this.computerIp = computerIp;
	}
	public String getComputerName() {
		return computerName;
	}
	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}
	public Integer getTomcatport() {
		return tomcatport;
	}
	public void setTomcatport(Integer tomcatport) {
		this.tomcatport = tomcatport;
	}
	public String getHaoshi() {
		return haoshi;
	}
	public void setHaoshi(String haoshi) {
		this.haoshi = haoshi;
	}
	@Override
	public String toString() {
		return "Operationlogs [operlogid=" + operlogid + ", operloginId=" + operloginId + ", operlognsolr="
				+ operlognsolr + ", opertime=" + opertime + ", opercontroller=" + opercontroller + ", operMethod="
				+ operMethod + ", tomcatport=" + tomcatport + ", operParams=" + operParams + ", operURI=" + operURI
				+ ", haoshi=" + haoshi + ", computerIp=" + computerIp + ", computerName=" + computerName + "]";
	}
	
	
	
}
