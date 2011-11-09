package com.example.StarCraftIIResults;

import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


public class Tabs extends FragmentActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    //	List<String> rounds = TLParser.getInstance().getRounds();
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);
        //init bar
        ActionBar bar = getActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        bar.setDisplayShowTitleEnabled(false);
        // add tab to bar
        for (int x = 0; x != 2; x++) {
        	Fragment frag = new GroupsFragment();
        //	Tab tab = bar.newTab().setText(rounds.get(x)).setTabListener(new MyTabListener(frag));
        	Tab tab = bar.newTab().setText("a").setTabListener(new MyTabListener(frag));
        	bar.addTab(tab);
        }
    }
    
    private class MyTabListener implements ActionBar.TabListener {
        private Fragment frag;

        // Called to create an instance of the listener when adding a new tab
        public MyTabListener(Fragment frag2) {
        	frag = frag2;
        }

		public void onTabSelected(Tab tab,FragmentTransaction ft) {
        	ft.add(R.id.groups, frag);  
        }

        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            ft.remove(frag);
        }

		public void onTabReselected(Tab tab, FragmentTransaction ft) {
		}

     }
}