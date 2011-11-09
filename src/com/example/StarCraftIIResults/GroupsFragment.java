package com.example.StarCraftIIResults;



import java.util.ArrayList;
import java.util.List;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GroupsFragment extends ListFragment {

	private GroupsAdapter adapter;
    private PlayerAdapter pAdapter;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("groups fragment", "test");
        String selectedTab =  getActivity().getActionBar().getSelectedTab().getText().toString();
        //List<String> groups = TLParser.getInstance().getGroups(selectedTab);
        List<String> groups = new ArrayList<String>();
        groups.add("group A");
        Log.d("groups fragment", "test");
        // wtf is r.layout.groups for
        adapter = new GroupsAdapter(getActivity(), R.layout.groups, groups);
        setListAdapter(adapter);
	 }

	private class GroupsAdapter extends ArrayAdapter<String> {

        private List<String> items;
        
        public GroupsAdapter(Context context, int textViewResourceId, List<String> items) {
                super(context, textViewResourceId, items);
                this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
	        View v = convertView;
	        if (v == null) {
	        	Log.d("convertview", "v is null");
	            LayoutInflater vi = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            v = vi.inflate(R.layout.groups, null);
	            
	      //      v = new View(getActivity());
	        }
	        Log.d("getview", "v : " + v);
	        TextView tName = (TextView) v.findViewById(R.id.group);
	        Log.d("getview", "tname : " + tName);
	        Log.d("getview", "items : " + items.get(position));
	        tName.setText(items.get(position));
	        Log.d("getview", "before return");
	        return v;
    	}
	}
	
	/**
	 * @Comment Display Players
	 */
	@Override
	public void onListItemClick(ListView l, View v, int pos, long id) {
		FrameLayout fl = (FrameLayout) getActivity().findViewById(R.id.groups);
		  String selectedTab =  getActivity().getActionBar().getSelectedTab().getText().toString();
		// List<String> players = TLParser.getInstance().getGroups(selectedTab, pos);
		 List<String> players = new ArrayList<String>();
		 players.add("LOLOLOL");
		 pAdapter = new PlayerAdapter(getActivity(), R.layout.players, players);
	 	setListAdapter(pAdapter);
	}

	private class PlayerAdapter extends ArrayAdapter<String> {

        private List<String> players;
        
        public PlayerAdapter(Context context, int textViewResourceId, List<String> players) {
                super(context, textViewResourceId, players);
                this.players = players;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
	        View v = convertView;
	        if (v == null) {
	            LayoutInflater vi = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            FrameLayout fl = (FrameLayout)getActivity().findViewById(R.id.groups);
	            v = vi.inflate(R.layout.players, parent);
	        }
	        TextView tName = (TextView) v.findViewById(R.id.player);
	        tName.setText(players.get(0));
	        return v;
    	}
	}
	
	private void Players(String pos) {
	    String selectedTab =  getActivity().getActionBar().getSelectedTab().getText().toString();
	    Log.d("players", "selectedTab : " + selectedTab);
	    Log.d("players", "pos: " + pos);
	  //  List<Player> players = TLParser.getInstance().getPlayers(selectedTab, pos);
	 //   ListView li = getListView();
	}
}
