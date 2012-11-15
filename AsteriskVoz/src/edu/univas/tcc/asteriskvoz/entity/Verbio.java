package edu.univas.tcc.asteriskvoz.entity;

public class Verbio {
	
	private String exten;
	private String dpts;
	
	private String primary_vox_server;
	private String backup_vox_server;
	private String default_language;
	private String default_speaker;
	private String text_prompts_path;
	private String default_config;
	private String grammar_path;
	
	private String message;
	
	public String getExten() {
		return exten;
	}
	public void setExten(String exten) {
		this.exten = exten;
	}
	public String getDpts() {
		return dpts;
	}
	public void setDpts(String dpts) {
		this.dpts = dpts;
	}
	public String getPrimary_vox_server() {
		return primary_vox_server;
	}
	public void setPrimary_vox_server(String primary_vox_server) {
		this.primary_vox_server = primary_vox_server;
	}
	public String getBackup_vox_server() {
		return backup_vox_server;
	}
	public void setBackup_vox_server(String backup_vox_server) {
		this.backup_vox_server = backup_vox_server;
	}
	public String getDefault_language() {
		return default_language;
	}
	public void setDefault_language(String default_language) {
		this.default_language = default_language;
	}
	public String getDefault_speaker() {
		return default_speaker;
	}
	public void setDefault_speaker(String default_speaker) {
		this.default_speaker = default_speaker;
	}
	public String getText_prompts_path() {
		return text_prompts_path;
	}
	public void setText_prompts_path(String text_prompts_path) {
		this.text_prompts_path = text_prompts_path;
	}
	public String getDefault_config() {
		return default_config;
	}
	public void setDefault_config(String default_config) {
		this.default_config = default_config;
	}
	public String getGrammar_path() {
		return grammar_path;
	}
	public void setGrammar_path(String grammar_path) {
		this.grammar_path = grammar_path;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
