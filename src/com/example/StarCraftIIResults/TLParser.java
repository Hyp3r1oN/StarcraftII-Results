package com.example.StarCraftIIResults;



import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.util.Log;

public class TLParser {
	public String url;
	public Document html;
	
	// init Parser
	TLParser() {
		url = "http://wiki.teamliquid.net/starcraft2/2011_Global_StarCraft_II_League_November/Code_S";
		try {
			html = Jsoup.connect(url).get();
		} catch (IOException e) {
            Log.e("GETINFOS_PROC", e.getMessage());
			e.printStackTrace();
		}
	}
	
	// get Tournament Name
	public String tName() {
		return html.title();
	}

	// TO DO GET GROUPS 
	//GET PLAYERS
	/*		Log.d("tNames", "BEFORE LIST ITERATOR");
			ListIterator<Element> listTname = table.select("th a").listIterator();
			 Log.d("tNames", listTname.next().text());
			 tNames.add(listTname.next().text());*/
}

