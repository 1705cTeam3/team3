package com.jk.provide.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="loginlogs")//如果不定义表名的话，会按着会按照实体类名来生成且首字母变成小写logDTO
public class LogDTO implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 5523485127319634468L;

@Id
private String logid;//主键
private String classname;//类名
private String methodname;//方法名
private String cztime;//操作时间
private String cname;//操作人
private String cip;//操作者的ip
private String status;//地位; 身份; 情形，状态
private String fileName;//文件名称
private Integer lineNumber;//行号
public String getLogid() {
	return logid;
}
public void setLogid(String logid) {
	this.logid = logid;
}
public String getClassname() {
	return classname;
}
public void setClassname(String classname) {
	this.classname = classname;
}
public String getMethodname() {
	return methodname;
}
public void setMethodname(String methodname) {
	this.methodname = methodname;
}
public String getCztime() {
	return cztime;
}
public void setCztime(String cztime) {
	this.cztime = cztime;
}
public String getCname() {
	return cname;
}
public void setCname(String cname) {
	this.cname = cname;
}
public String getCip() {
	return cip;
}
public void setCip(String cip) {
	this.cip = cip;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
public Integer getLineNumber() {
	return lineNumber;
}
public void setLineNumber(Integer lineNumber) {
	this.lineNumber = lineNumber;
}


@Override
public String toString() {
	return "LogDTO [logid=" + logid + ", classname=" + classname + ", methodname=" + methodname + ", cztime=" + cztime
			+ ", cname=" + cname + ", cip=" + cip + ", status=" + status + ", fileName=" + fileName + ", lineNumber="
			+ lineNumber + "]";
}

}
