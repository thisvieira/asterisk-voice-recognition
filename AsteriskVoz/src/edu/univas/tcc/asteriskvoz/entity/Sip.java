package edu.univas.tcc.asteriskvoz.entity;

public class Sip {
	
	private String name;
	private String extenNumber;
	private String secret;
	private String callerid;
	private String context;
	private String host;
	private String DefaultIP;
	private String Type;
	private String port;
	private String language;
	private String disallow;
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
		return callerid;
	}
	public void setCallerid(String callerid) {
		this.callerid = callerid;
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
		return DefaultIP;
	}
	public void setDefaultIP(String defaultIP) {
		DefaultIP = defaultIP;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
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

}
