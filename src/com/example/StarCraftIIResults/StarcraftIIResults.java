package com.example.StarCraftIIResults;

import java.util.ArrayList;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class StarcraftIIResults extends ListActivity {
	
    private ProgressDialog m_ProgressDialog = null;
    private ArrayList<Tournament> m_Tournaments = null;
    private TournamentAdapter tAdapter;
    private Runnable viewTournaments;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
  
        m_Tournaments = new ArrayList<Tournament>();
        this.tAdapter = new TournamentAdapter(this, R.layout.tournaments, m_Tournaments);
        setListAdapter(this.tAdapter);
        
        
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);

        lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
              // When clicked, show a toast with the TextView text
                Intent intent = new Intent(StarcraftIIResults.this, Tabs.class);
                startActivity(intent);

            }
          });

        viewTournaments = new Runnable(){
        	public void run() {
        		getInfos();	
            }
        };
        Thread thread =  new Thread(null, viewTournaments, "MagentoBackground");
        thread.start();
        m_ProgressDialog = ProgressDialog.show(StarcraftIIResults.this,    
             "Please wait...", "Retrieving data ...", true);
    }
    
    private Runnable returnRes = new Runnable() {
    	public void run() {
            if(m_Tournaments != null && m_Tournaments.size() > 0) {
                tAdapter.notifyDataSetChanged();
                for(int i = 0; i < m_Tournaments.size(); i++) {
                	tAdapter.add(m_Tournaments.get(i));
                }
            }
            m_ProgressDialog.dismiss();
            tAdapter.notifyDataSetChanged();
        }
    };
   
	
	private class TournamentAdapter extends ArrayAdapter<Tournament> {

        private ArrayList<Tournament> items;
        
        public TournamentAdapter(Context context, int textViewResourceId, ArrayList<Tournament> items) {
                super(context, textViewResourceId, items);
                this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
	        View v = convertView;
	        if (v == null) {
	            LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            v = vi.inflate(R.layout.tournaments, null);
	        }
	        Tournament t = items.get(position);
	        if (t != null) {
	        	TextView tName = (TextView) v.findViewById(R.id.tournament);
	        	if (tName != null) 
	        		tName.setText(t.getName());
	        }
	        return v;
    	}
	}
    
    private void getInfos(){
        try{
            m_Tournaments = new ArrayList<Tournament>();
            Tournament t = new Tournament();
     //   	t.setName(TLParser.getInstance().getTournaments());
            t.setName("TOURNAMENT");
        	m_Tournaments.add(t);
        	Thread.sleep(1000);
          } catch (Exception e) {
            Log.e("GETINFOS_PROC", e.getMessage());
            e.printStackTrace();
          }
          runOnUiThread(returnRes);
      }
}