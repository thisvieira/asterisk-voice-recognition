package edu.univas.tcc.asteriskvoz.managedBean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import edu.univas.tcc.asteriskvoz.entity.Extensions;

@ViewScoped
@ManagedBean
@Stateful
public class DialplanMB {
	
	private Extensions exten = new Extensions();

	@SuppressWarnings("resource")
	public String registerExtensions() {

		File dir = new File("/etc/asterisk");
		File arq = new File(dir, "extensions.conf");
		

		try {
			BufferedReader buffer = new BufferedReader(new FileReader(arq));
			List<String> b = new ArrayList<String>();
			String dialConf;
			FileWriter writeExten = new FileWriter(arq, true);
			PrintWriter printExten = new PrintWriter(writeExten);
			
			while ((dialConf = buffer.readLine()) != null) {
				b.add(dialConf);
			}
			RandomAccessFile r = new RandomAccessFile(arq, "rw");
			r.setLength(0);
			for (String line : b) {
				if(line.equals("["+ exten.getContext() + "]")){
					printExten.println("["+ exten.getContext() + "]");
					printExten.println("exten => " + exten.getExtensions() + "," + exten.getPriorit() + 
							"," + exten.getApp() + "(" + exten.getAppData() + 
							"/" + exten.getExtensions() + ")");
				}
				if(!line.equals("["+ exten.getContext() + "]")){
					printExten.println(line);
				}
			}
//			printExten.println();
//			printExten.println("[" + exten.getContext() + "]");
//			printExten.println("secret = " + exten.getExtensions());
//			printExten.println("callerid = " + exten.getPriorit());
//			printExten.println("host = " + exten.getApp());
//			printExten.println("context = " + exten.getAppData());

			
			printExten.flush();

			printExten.close();

			exten = new Extensions();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Extensions getExten() {
		return exten;
	}

	public void setExten(Extensions exten) {
		this.exten = exten;
	}
	
	
}
