package com.example.StarCraftIIResults;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Players extends Activity {
	
	private class PlayerAdapter extends BaseAdapter {
		
		private LayoutInflater inf;
		List<Player> players;
		
		public PlayerAdapter(Context context, List<Player> players) {
			inf = LayoutInflater.from(context);
			this.players = players;
		}
	
		public View getView(int pos, View convertView, ViewGroup parent) {
			ViewHolder holder;

			if (convertView == null) {
				convertView = inf.inflate(R.layout.players_design, null);
				holder = new ViewHolder();
				holder.player = (TextView) convertView.findViewById(R.id.player);
				convertView.setTag(holder);
			} else 
				holder = (ViewHolder) convertView.getTag();
			holder.player.setText(players.get(pos).getName());
			if (pos <= 1)
				holder.player.setBackgroundColor(Color.GREEN);
			else
				holder.player.setBackgroundColor(Color.RED);
			holder.player.setCompoundDrawablesWithIntrinsicBounds(0, 0, players.get(pos).getIcon(), 0);
			return convertView;
		}

		public int getCount() {
			return players.size();
		}

		public Object getItem(int pos) {
			return players.get(pos);
		}

		public long getItemId(int pos) {
			return pos;
		}
		
		class ViewHolder {
            TextView player;
        }
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		List<Player> players;
		Bundle bundle = this.getIntent().getExtras();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.players);
		ListView lv = (ListView)findViewById(R.id.lvPlayers);
		players = TLParser.getInstance().getPlayers(bundle.getInt("group"));
		PlayerAdapter adapter = new PlayerAdapter(this, players);	
		lv.setAdapter(adapter);
	}
}