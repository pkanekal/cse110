package com.Cse110;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.Cse110.R;
import com.facebook.Session;
import com.parse.*;


public class HomePageActivity extends ActionBarActivity {
	private String AppId = "PFeVg2evGtAWYkKlIe11myOGl2Wsw4bpygimtWcT";
    private String ClientKey = "AmbMohWlGph7KEZfZMl4LnbVBj8M20fThtOIzn7P";
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);

		// Add your initialization code here
        Parse.initialize(this, AppId, ClientKey);
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        
        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
        
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

    public static void Save(String restaurantName, String zipCode, String category)
    {
    ParseObject newRestaurant = new ParseObject("Restaurant");
    newRestaurant.put("RestaurantName", restaurantName);
    newRestaurant.put("ZipCode", zipCode);
    newRestaurant.put("Category", category);
    newRestaurant.saveInBackground();
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_page, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.fb_logout:
	        	fbLogout();
	            return true;
	        case R.id.action_settings:
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	public void fbLogout() {
		
	    final Session sessionLogOut = Session.getActiveSession();
        final Intent intentLogOut = new Intent(this, MainActivity.class);
        
		new AlertDialog.Builder(this)
		.setTitle("Logout")
		.setMessage("Do you really want to logout?")
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

		    public void onClick(DialogInterface dialog, int whichButton) {
			    sessionLogOut.closeAndClearTokenInformation();
		        startActivity(intentLogOut);		    }})
		 .setNegativeButton(android.R.string.no, null).show();
		
	}
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_home_page,
					container, false);
			return rootView;
		}
	}

}
