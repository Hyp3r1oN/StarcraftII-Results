package com.example.StarCraftIIResults;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import android.util.Log;

public class TLParser {
	public String url;
	public String title;
	public Document html;
	public Elements groupsHTML;
	public Elements playersHTML;
	private static volatile TLParser instance = null;

	// init Parser
	TLParser() {
		url = "http://wiki.teamliquid.net/starcraft2/2011_Global_StarCraft_II_League_November/Code_S";
		Log.d("init parser", "init parser");
		try {
			html = Jsoup.connect(url).get();
			groupsHTML = html.getElementsByAttributeValue("style", "width: 300px;margin: 0px;");	
		} catch (IOException e) {
            Log.e("GETINFOS_PROC", e.getMessage());
			e.printStackTrace();
		}
		Log.d("init parser", "after");
	}
	
    public static TLParser getInstance() {
        if (TLParser.instance == null) {
        	synchronized(TLParser.class) {
        		if (TLParser.instance == null) {
        			TLParser.instance = new TLParser();
        		}
        	}
        }
        return TLParser.instance;
    }
    
	// get Tournament Name
	public String getTournaments() {
		String[] title = html.title().split("-");
		return title[0];
	}
	
	// get Tournament Groups
	public List<String> getGroups() {
		List<String> groups = new ArrayList<String>();
		// get only groups table
		for (Element e : groupsHTML) {			
			Element hasResult = e.select("tr").get(1);
			// check if the table has results
			if (hasResult.hasAttr("style")) {
				groups.add(e.select("tr").first().text());
			}	
		}
		return revert(groups);
	}
	
	public List<Player> getPlayers(int group) {
		List<Player> players = new ArrayList<Player>();
		Elements playersHTML = groupsHTML.get(group).select("tr[style]");
		Hashtable<String, Integer> icons = new Hashtable<String, Integer>();
		
		icons.put("Protoss", R.drawable.picon);
		icons.put("Terran", R.drawable.ticon);
		icons.put("Zerg", R.drawable.zicon);
		for (Element e: playersHTML) {
			Player x = new Player();
			x.setName(e.text());
			x.setRace(e.select("a").get(1).attr("title"));
			x.setIcon(icons.get(x.getRace()));
			players.add(x);
		}	
		return players;
	}
	
	static List<String> revert(List<String> list) {
		List<String> result = new ArrayList<String>();
		for(int i=list.size()-1; i>=0; i--)
		    result.add(list.get(i));
		return result;
	}
	 
}

