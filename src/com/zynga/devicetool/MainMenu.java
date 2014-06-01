package com.zynga.devicetool;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainMenu extends ListActivity{
//	String classes[] = {"Device Information", "List of Installed Applications", "View Logcat", "Send Logcat", "About Us"};
	String classes[] = {"Device Information", "List of Installed Applications", "View Logcat", "Send Logcat"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(MainMenu.this, android.R.layout.simple_list_item_1, classes));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		String startClass;
		super.onListItemClick(l, v, position, id);
		if (classes[position].equals("Device Information")){
			startClass = "DeviceInfo";
			openClassAct(startClass);
		} else if (classes[position].equals("List of Installed Applications")) {
			startClass = "InstalledApp";
			openClassAct(startClass);
		} else if (classes[position].equals("View Logcat")) {
			startClass = "ViewLogcat";
			openClassAct(startClass);
		} else if (classes[position].equals("Send Logcat")) {
			startClass = "SendLogcat";
			openClassAct(startClass);
		} /*else if (classes[position].equals("About Us")) {
			startClass = "AboutUs";
			openClassAct(startClass);
		} */
	}

	private void openClassAct(String startClass) {
		// TODO Auto-generated method stub
		try {
			Class myClass = Class.forName("com.zynga.devicetool." + startClass);
			Intent myIntent = new Intent(MainMenu.this, myClass);
			startActivity(myIntent);
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	//Menu 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.device__info, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()){
		case R.id.aboutMe:
			Intent i = new Intent(this, AboutMe.class);
			startActivity(i);
			break;
		}
		return false;
	}
}
