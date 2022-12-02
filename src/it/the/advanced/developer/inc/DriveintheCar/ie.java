package it.the.advanced.developer.inc.DriveintheCar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ie extends ActionBarActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ie);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Drive in the Car");
            setSupportActionBar(toolbar);
        }
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.item1:
                // Single menu item is selected do something
                // Ex: launching new activity/screen or show alert message
                Intent opendescrizione = new Intent(ie.this, descrizione.class);
                startActivity(opendescrizione);
                break;
            case R.id.item2:
                // Single menu item is selected do something
                // Ex: launching new activity/screen or show alert message
                Intent openinfo = new Intent(ie.this, info.class);
                startActivity(openinfo);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
