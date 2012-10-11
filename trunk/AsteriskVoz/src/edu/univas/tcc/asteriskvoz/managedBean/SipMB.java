package edu.univas.tcc.asteriskvoz.managedBean;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import edu.univas.tcc.asteriskvoz.entity.Sip;

@ManagedBean
@ViewScoped
@Stateless
public class SipMB {

	private Sip registerSip = new Sip();
	
	public String registerSip() {
		
			System.out.println(registerSip.getName());
		
		return null;
	}

	public Sip getRegisterSip() {
		return registerSip;
	}

	public void setRegisterSip(Sip registerSip) {
		this.registerSip = registerSip;
	}
	
	

}
