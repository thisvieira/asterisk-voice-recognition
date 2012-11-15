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

import edu.univas.tcc.asteriskvoz.entity.Verbio;

@ViewScoped
@ManagedBean
@Stateful
public class VerbioMB {

	private Verbio verbioTxt = new Verbio();

	public String resgisterVerbioTxt() {

		File dir = new File("/var/lib/asterisk/verbio/gram/");
		File arq = new File(dir, "vdemo.txt");

		try {
			// BufferedReader buffer = new BufferedReader(new FileReader(arq));
			// List<String> b = new ArrayList<String>();
			// String dialConf;
			FileWriter writeExten = new FileWriter(arq, true);
			PrintWriter printExten = new PrintWriter(writeExten);

			printExten
					.println(verbioTxt.getExten() + " " + verbioTxt.getDpts());

			// while ((dialConf = buffer.readLine()) != null) {
			// b.add(dialConf);
			// }
			// RandomAccessFile r = new RandomAccessFile(arq, "rw");
			// r.setLength(0);
			// for (String line : b) {
			// if(line.equals("["+ exten.getContext() + "]")){
			// printExten.println("["+ exten.getContext() + "]");
			// printExten.println("exten => " + exten.getExtensions() + "," +
			// exten.getPriorit() +
			// "," + exten.getApp() + "(" + exten.getAppData() +
			// "/" + exten.getExtensions() + ")");
			// }
			// if(!line.equals("["+ exten.getContext() + "]")){
			// printExten.println(line);
			// }
			// }
			// printExten.println();
			// printExten.println("[" + exten.getContext() + "]");
			// printExten.println("secret = " + exten.getExtensions());
			// printExten.println("callerid = " + exten.getPriorit());
			// printExten.println("host = " + exten.getApp());
			// printExten.println("context = " + exten.getAppData());

			printExten.flush();

			printExten.close();

			verbioTxt = new Verbio();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("resource")
	public void verbioConf() {
		File dir = new File("/etc/asterisk/");
		File arq = new File(dir, "verbio.conf");

		try {
			BufferedReader buffer = new BufferedReader(new FileReader(arq));
			List<String> b = new ArrayList<String>();
			String dialConf;

			while ((dialConf = buffer.readLine()) != null) {
				b.add(dialConf);
			}
			for (String line : b) {
				if (line.contains("primary_vox_server")) {
					verbioTxt.setPrimary_vox_server(line);
				}

			}
			verbioTxt = new Verbio();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public void verbioDialplan() {
		File dir = new File("/etc/asterisk/");
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
				
					if (line.contains("exten => s,n,Set(TESTE=")) {
						printExten.println("exten => s,n,Set(TESTE=\"" + verbioTxt.getMessage()+ "\")");
					}else{
						printExten.println(line);
					}
				}
			
			printExten.flush();

			printExten.close();

			verbioTxt = new Verbio();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Verbio getVerbioTxt() {
		return verbioTxt;
	}

	public void setVerbioTxt(Verbio verbioTxt) {
		this.verbioTxt = verbioTxt;
	}

}
