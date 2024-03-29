package edu.univas.tcc.asteriskvoz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sip {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	@Column
	private String extenNumber;
	@Column
	private String secret;
	@Column
	private String callid;
	@Column
	private String context;
	@Column
	private String host;
	@Column
	private String defaultip;
	@Column
	private String type;
	@Column
	private String port;
	@Column
	private String language;
	@Column
	private String disallow;
	@Column
	private String allow;
	
	public Sip(){
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExtenNumber() {
		return extenNumber;
	}
	public void setExtenNumber(String extenNumber) {
		this.extenNumber = extenNumber;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getCallerid() {
		return callid;
	}
	public void setCallerid(String callerid) {
		this.callid = callerid;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getDefaultIP() {
		return defaultip;
	}
	public void setDefaultIP(String defaultIP) {
		this.defaultip = defaultIP;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getDisallow() {
		return disallow;
	}
	public void setDisallow(String disallow) {
		this.disallow = disallow;
	}
	public String getAllow() {
		return allow;
	}
	public void setAllow(String allow) {
		this.allow = allow;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
