package com.example.StarCraftIIResults;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Groups extends ListActivity {
	
	  @Override
	  public void onCreate(Bundle savedInstanceState) {  
		  ListView lv = getListView();
		  final List<String> groups;
		  
		  super.onCreate(savedInstanceState);
		  // display groups
		  Log.d("oncreate groups", "lol");
		  groups = TLParser.getInstance().getGroups();
	      setListAdapter(new ArrayAdapter<String>(this, R.layout.groups, groups));
  	      //on click event
	      
  	      lv.setOnItemClickListener(new OnItemClickListener() {
  	    	  public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
  	    		  Intent intent = new Intent(Groups.this, Players.class);
  	    		  Bundle bundle = new Bundle();
  	    		  
  	    		  bundle.putInt("group", groups.size() - (pos + 1));
  	    		  intent.putExtras(bundle);
  	    		  startActivity(intent);
  	    	  }
          });
	  }
}
