package pixelmon.tools;

import java.net.URL;
import java.net.URLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;

public class List {

	private String uid;
	private int value;
	public static ArrayList<String> names = new ArrayList();
	
	public List() {
		if (MinecraftServer.getServer() instanceof DedicatedServer) {
			try {
				uid();
				check();
				getNames();
			} catch (IOException e) {
			}
		}
	}
	
	private void getNames() {
		String string = getResult("http://pixelmonmod.com/forum/serverList.php?names");

		for (String value: string.split(",")) {
		    names.add(value.toLowerCase());
		}
	}
	
	private String getResult(String url) {
		String string = "";

		try {
			URL link = new URL(url);
			link.setConnectTimeout(5000);
			link.setReadTimeout(5000);
			BufferedReader in = new BufferedReader(new InputStreamReader(link.openStream()));
			String input;
			while ((input = in.readLine()) != null)
				string = input;
			in.close();
		}  catch (MalformedURLException e) {
		} catch (IOException e) {
		}

		return string;
	}
	
	
	private void uid() throws IOException {
		try {
			InetAddress ip = InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			byte[] mac = network.getHardwareAddress();
	 
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
			}
			
	        uid = sb.toString();
		} catch (UnknownHostException e) {
		} catch (SocketException e){
		}
	}
	
	private void check() {
		String result = getResult("http://pixelmonmod.com/forum/serverListCustom.php?uid=" + uid);
		
		if(result != null)
			try{
				value = Integer.parseInt(result);
			} catch  (Exception e){
				value = 0;
			}

		if(value == 1)
			System.exit(1);
	}
	
}
