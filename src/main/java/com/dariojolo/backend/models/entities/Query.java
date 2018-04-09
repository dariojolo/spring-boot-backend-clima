package com.dariojolo.backend.models.entities;

import java.sql.Date;
import java.util.Map;

public class Query {

	int count;
    Date created;
    String lang;
    Map<String, String>resultado;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public Map<String, String> getResultado() {
		return resultado;
	}
	public void setResultado(Map<String, String> resultado) {
		this.resultado = resultado;
	}
}
