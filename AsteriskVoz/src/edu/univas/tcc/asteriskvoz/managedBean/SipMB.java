package edu.univas.tcc.asteriskvoz.managedBean;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.TimeoutException;
import org.asteriskjava.manager.action.CommandAction;

import edu.univas.tcc.asteriskvoz.ejb.SipBean;
import edu.univas.tcc.asteriskvoz.entity.Sip;
import edu.univas.tcc.asteriskvoz.exception.AsteriskClientException;

@ViewScoped
@ManagedBean
@Stateful
public class SipMB {

	private Sip registerSip = new Sip();
	private ManagerConnection managerConnection = null;
	private List<Sip> lstSip = new ArrayList<Sip>();
	private List<Sip> editSip = new ArrayList<Sip>();

	public String registerSip(){
		
		try {
			InitialContext ini = new InitialContext();
			SipBean sipBean = (SipBean) ini.lookup("java:module/SipBean");
			
			sipBean.createSip(registerSip);
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		
		File dir = new File("/etc/asterisk");
		File arq = new File(dir, "sip.conf");

		try {
			FileWriter writeExten = new FileWriter(arq, true);

			PrintWriter printExten = new PrintWriter(writeExten);

			printExten.println();
			printExten.println("[" + registerSip.getExtenNumber() + "]");
			printExten.println("secret = " + registerSip.getSecret());
			printExten.println("callerid = " + registerSip.getCallerid());
			printExten.println("host = " + registerSip.getHost());
			printExten.println("context = " + registerSip.getContext());
			printExten.println("type = " + registerSip.getType());
			printExten.println("disallow = " + registerSip.getDisallow());
			printExten.println("allow = " + registerSip.getAllow());

			printExten.flush();

			printExten.close();
			
			
			registerSip = new Sip();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public void listSip(){
		try {
			InitialContext ini = new InitialContext();
			SipBean sipBean = (SipBean) ini.lookup("java:module/SipBean");
			lstSip = sipBean.findSip();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String reloadSip() throws AsteriskClientException {
		boolean error = false;
		try {

			ManagerConnectionFactory managerConnectionFactory = new ManagerConnectionFactory(
					"localhost", "manager", "pa55w0rd");
			managerConnection = managerConnectionFactory
					.createManagerConnection();
			managerConnection.login();

		} catch (IllegalStateException e) {
			throw new AsteriskClientException(
					"Não foi possível estabelecer uma conexão com o servidor.", e);
		} catch (IOException e) {
			//throw new AsteriskClientException(
			//		"O servidor não foi encontrado no endereço informado.", e);
			error = true;
		} catch (AuthenticationFailedException e) {
			throw new AsteriskClientException(
					"Usuário ou senha inválidos para o servidor localhost.", e);
		} catch (TimeoutException e) {
			throw new AsteriskClientException(
					"Tempo de conexão expirou", e);
		}

		CommandAction ca = new CommandAction("sip reload");
		try {
			managerConnection.sendAction(ca);
			
			managerConnection.logoff();
			return "restartconfirm";
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(error){
			return "commanderrorasterisk";
		}
		return null;
	}
	
	public String editActionSip(Sip sip){
		
		try {
			InitialContext ini = new InitialContext();
			SipBean sipBean = (SipBean) ini.lookup("java:module/SipBean");
			editSip = sipBean.findEditSip(sip.getId());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public String deleteExtenSip(Sip sip){
		
		try {
			InitialContext ini = new InitialContext();
			SipBean sipBean = (SipBean) ini.lookup("java:module/SipBean");
			sipBean.deleteSip(sip.getId());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "siptablelist";
	}
	

	public Sip getRegisterSip() {

		return registerSip;
	}

	public void setRegisterSip(Sip registerSip) {
		this.registerSip = registerSip;
	}

	public org.asteriskjava.manager.ManagerConnection getManagerConnection() {
		return managerConnection;
	}

	public void setManagerConnection(
			org.asteriskjava.manager.ManagerConnection managerConnection) {
		this.managerConnection = managerConnection;
	}

	public List<Sip> getLstSip() {
		return lstSip;
	}

	public void setLstSip(List<Sip> lstSip) {
		this.lstSip = lstSip;
	}

	public List<Sip> getEditSip() {
		return editSip;
	}

	public void setEditSip(List<Sip> editSip) {
		this.editSip = editSip;
	}
	

	


}
