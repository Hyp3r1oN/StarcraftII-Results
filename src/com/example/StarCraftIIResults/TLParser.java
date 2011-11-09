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
	public String title, url;
	public Document html;
	public Elements groupsHTML, playersHTML, roundsHTML;
	private static volatile TLParser instance = null;

	// init Parser
	TLParser() {
		url = "http://wiki.teamliquid.net/starcraft2/2011_Global_StarCraft_II_League_November/Code_S";
		Log.d("init parser", "init parser");
		try {
			html = Jsoup.connect(url).get();
			roundsHTML = html.getElementsByTag("h2").select("span");
			groupsHTML = html.getElementsByAttributeValueMatching("class", "prettytable|mw-headline");
			Log.d("init parser", "GOT ALL VALUES");
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
	
	/**
	 * @return a Rounds List<String>
	 */
	public List<String> getRounds() {
		List<String> rounds = new ArrayList<String>();
		boolean add = false;
		for (Element e : roundsHTML) {
			String title = e.text();
			if (title.compareToIgnoreCase("match summary playoffs") == 0)
				add = false;
			if (add == true) {
				if (title.lastIndexOf("(") != -1 && title.lastIndexOf(")") != -1) {
					rounds.add(title.substring(title.lastIndexOf("(") + 1, title.lastIndexOf(")")));
				} else 
				rounds.add(title);
			}
			if (title.compareToIgnoreCase("format") == 0)
				add = true;
		}
		return rounds;
	}
	
	/**
	 * @return a Groups List<String>
	 */
	public List<String> getGroups(String ro) {
		List<String> groups = new ArrayList<String>();
		List<String> groupsDone = new ArrayList<String>();
		boolean show = false;
		// get only groups table
		Log.d("getgroups", "beginning");
		for (Element e : groupsHTML) {
			String title = e.text();
			// break when we get all groups of a specific round
			if (e.tagName().compareTo("span") == 0 && show == true && e.attr("id").matches("(?i).*group_stage.*"))
				break;
			// check if title matches with ro
			if (title.matches("(?i).*" + ro + ".*"))
				show = true;
			// get groups
			if (show == true) {
				Elements tables = e.getElementsByAttributeValue("style", "width: 300px;margin: 0px;");
				for (Element x : tables) {
					// if there is result, add in groupsDone List
					if (x.select("tr").get(1).hasAttr("style"))
						groupsDone.add(x.select("tr").first().text());
					else
						groups.add(x.select("tr").first().text());
				}
			}
		}
		Log.d("getgroups", "end");
		for (int x = 0; x != groupsDone.size(); x++) {
			groups.add(0, groupsDone.get(x));
		}
		Log.d("getgroups", "return");
		return groups;
	}
	
	public List<Player> getPlayers(String round, String group) {
		List<Player> players = new ArrayList<Player>();
		Hashtable<String, Integer> icons = new Hashtable<String, Integer>();
		boolean add = true;
		
		icons.put("Protoss", R.drawable.picon);
		icons.put("Terran", R.drawable.ticon);
		icons.put("Zerg", R.drawable.zicon);
		for (Element e: groupsHTML) {
			String title = e.text();
			if (add == true) {
				if (e.select("tr").size() >= 1) {
					String groupName = e.select("tr").first().text();
					if (groupName.compareToIgnoreCase(group) == 0) {
						for (int i = 0; i != e.select("tr").size(); i++) {
							Element x = e.select("tr").get(i);
							if (i > 0) {
								Player p = new Player();
								p.setName(x.text());
								p.setRace(x.select("a").get(1).attr("title"));
								p.setIcon(icons.get(p.getRace()));
								players.add(p);
							}
						}
						break;
					}
				}
				
			}
			if (title.matches("(?i).*"+round+".*"))
				add = true;
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

