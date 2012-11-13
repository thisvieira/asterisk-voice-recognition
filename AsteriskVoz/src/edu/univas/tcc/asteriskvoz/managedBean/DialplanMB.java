package edu.univas.tcc.asteriskvoz.managedBean;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import edu.univas.tcc.asteriskvoz.ejb.SipBean;
import edu.univas.tcc.asteriskvoz.entity.Sip;

@ViewScoped
@ManagedBean
@Stateful
public class DialplanMB {

	public String extensions() {

		File dir = new File("/etc/asterisk");
		File arq = new File(dir, "extensions.conf");

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
}
