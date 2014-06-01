package com.zynga.devicetool;

import java.util.ArrayList;
import java.util.List;

import com.zynga.devicetool.adapter.ApkAdapter;
import com.zynga.devicetool.app.AppData;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class InstalledApp extends Activity implements OnItemClickListener{
	PackageManager packageManager;
	ListView apkList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.installedapp);
		packageManager = getPackageManager();
	    List<PackageInfo> packageList = packageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS);
	    List<PackageInfo> packageList1 = new ArrayList<PackageInfo>();
	    
	    /*To filter out System apps*/
	    for(PackageInfo pi : packageList) {
	        boolean b = isSystemPackage(pi);
	        if(!b) {
	            packageList1.add(pi);
	        }
	    }
	    apkList = (ListView) findViewById(R.id.applist);
	    apkList.setAdapter(new ApkAdapter(this, packageList1, packageManager));
	
	    apkList.setOnItemClickListener(this);
	}

	private boolean isSystemPackage(PackageInfo pi) {
		// TODO Auto-generated method stub
		return ((pi.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true: false;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		PackageInfo packageInfo = (PackageInfo) parent.getItemAtPosition(position);
	    AppData appData = (AppData) getApplicationContext();
	    appData.setPackageInfo(packageInfo);
	
	    Intent appInfo = new Intent(getApplicationContext(), ApkInfo.class);
	    startActivity(appInfo);
	}
}
