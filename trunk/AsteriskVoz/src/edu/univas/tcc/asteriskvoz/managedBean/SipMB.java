package edu.univas.tcc.asteriskvoz.managedBean;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.EventTimeoutException;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.ManagerConnectionState;
import org.asteriskjava.manager.ManagerEventListener;
import org.asteriskjava.manager.ManagerEventListenerProxy;
import org.asteriskjava.manager.ResponseEvents;
import org.asteriskjava.manager.SendActionCallback;
import org.asteriskjava.manager.TimeoutException;
import org.asteriskjava.manager.action.CommandAction;
import org.asteriskjava.manager.action.EventGeneratingAction;
import org.asteriskjava.manager.action.ManagerAction;
import org.asteriskjava.manager.response.CommandResponse;
import org.asteriskjava.manager.response.ManagerResponse;

import edu.univas.tcc.asteriskvoz.entity.Sip;

@ManagedBean
@ViewScoped
@Stateless
public class SipMB {

	private Sip registerSip = new Sip();

	public String registerSip() {

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

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

		
	}

	public String sipReload() {
		
		
		ManagerConnectionFactory mfactory = new ManagerConnectionFactory(
				"localhost", "rafael", "leitao");
		ManagerConnection ma = mfactory.createManagerConnection();

		CommandAction cmmAct = new CommandAction("sip reload");
		try {
			ManagerResponse managerResponse = ma.sendAction(cmmAct);
			String response = managerResponse.getResponse()
					+ managerResponse.getMessage();
			System.out.println(response);
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
		return null;
	}

	public Sip getRegisterSip() {
		return registerSip;
	}

	public void setRegisterSip(Sip registerSip) {
		this.registerSip = registerSip;
	}

}
