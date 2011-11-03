package com.example.StarCraftIIResults;

import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Text;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.NodeVisitor;


import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.Text;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.NodeVisitor;


public class StarcraftIIResults extends ListActivity {
	
	  private ProgressDialog m_ProgressDialog = null;
	  private ArrayList<Player> m_Players = null;
	  private PlayerAdapter m_adapter;
	  private Runnable viewPlayers;
	  private MyVisitor visitor;
	    
	    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       	Log.d("StarcraftIIResults", "TLPARSER BEFORE");
        setContentView(R.layout.main);

       	Log.d("StarcraftIIResults", "TLPARSER BEFORE");
        m_Players = new ArrayList<Player>();
        this.m_adapter = new PlayerAdapter(this, R.layout.row, m_Players);
        setListAdapter(this.m_adapter);
       	Log.d("StarcraftIIResults", "TLPARSER BEFORE");
    	try {
			visitor.parse();
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Log.d("StarcraftIIResults", "TLPARSER AFTER");
    /*	for (int x = 0; x != Player.size(); x++) {
    		Log.d("StarCraftIIResults",  Player.get(x));
    	}*/
/*
        Thread thread =  new Thread(null, viewPlayers, "MagentoBackground");
        thread.start();
        m_ProgressDialog = ProgressDialog.show(StarcraftIIResults.this,    
              "Please wait...", "Retrieving data ...", true);*/
    }
	
   /* private void getPlayers(){
        try {
        	m_Players = new ArrayList<Player>();
            Player o1 = new Player();
            o1.setPlayerName("SF services");
            o1.setPlayerStatus("Pending");
            Player o2 = new Player();
            o2.setPlayerName("SF Advertisement");
            o2.setPlayerStatus("Completed");
            m_Players.add(o1);
            m_Players.add(o2);
            Thread.sleep(5000);
            Log.i("ARRAY", ""+ m_Players.size());
        } catch (Exception e) {
        	Log.e("BACKGROUND_PROC", e.getMessage());
        }
        runOnUiThread(returnRes);
    }
   
	private Runnable returnRes = new Runnable() {
	    public void run() {
	        if(m_Players != null && m_Players.size() > 0){
	            m_adapter.notifyDataSetChanged();
	            for(int i=0;i<m_Players.size();i++)
	            m_adapter.add(m_Players.get(i));
	        }
	        m_ProgressDialog.dismiss();
	        m_adapter.notifyDataSetChanged();
	    }
	};*/
	
	private class PlayerAdapter extends ArrayAdapter<Player> {
		
	    private ArrayList<Player> items;
	
	    public PlayerAdapter(Context context, int textViewResourceId, ArrayList<Player> items) {
	            super(context, textViewResourceId, items);
	            this.items = items;
	    }
	
	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	            View v = convertView;
	            if (v == null) {
	                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	                v = vi.inflate(R.layout.row, null);
	            }
	            Player Player = items.get(position);
	            if (Player != null) {
	                    TextView name = (TextView) v.findViewById(R.id.name);
	                    TextView score = (TextView) v.findViewById(R.id.score);
	                    if (name != null) 
	                    	name.setText("Name: "+Player.getName());
	                    if(score != null)
	                    	score.setText("Status: "+ Player.getScore());

	            }
	            return v;
	    }
	
	}
	
	public class MyVisitor extends NodeVisitor
	{
		private List <String> Players = new ArrayList<String>();
		private int i = 0;
		

		public void visitStringNode (Text string)
	    {
			if (string.getText().trim().compareToIgnoreCase("&nbsp;") != 0 && 
				string.getText().trim().compareToIgnoreCase("") != 0 
				&& string.getText().trim().length() > 2
				&& i > 1
				)
			{
				if (i%2 == 0) {
					Players.add(string.getText().trim());
				}
			}
			i++;
	    }
		
	    public void parse() throws ParserException
	    {
	        try
	        {
	        	Log.d("StarcraftIIResults", "LOL");
	            Parser parser = new Parser ("http://wiki.teamliquid.net/starcraft2/2011_Global_StarCraft_II_League_November/Code_S");
	            NodeList list = parser.extractAllNodesThatMatch(new AndFilter(new TagNameFilter("table"),new HasAttributeFilter("class", "prettytable")));
	            Log.d("StarcraftIIResults", list.toHtml());
	        /*    for (int x=0; x!=1; x++) {
	            	Node group = list.elementAt(x);
	               	NodeVisitor visitor = new MyVisitor ();
	            	group.accept(visitor);
	            }
	            for (int i=0; i != Players.size(); i++) {
	            	Log.d("StarcraftIIResults", Players.get(i));
	            }*/
	        }
	        catch (ParserException pe)
	        {
	            pe.printStackTrace ();
	        }

	        
	    }
	}
}