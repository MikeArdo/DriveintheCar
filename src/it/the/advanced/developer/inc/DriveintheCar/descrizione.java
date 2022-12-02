package it.the.advanced.developer.inc.DriveintheCar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class descrizione extends ActionBarActivity {
    ImageButton ImageButton1;
    ImageButton ImageButton2;
    ImageButton ImageButton3;
    ImageButton ImageButton4;
    ImageButton ImageButton5;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descrizione);
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
        addListenerOnButton1();
        addListenerOnButton2();
        addListenerOnButton3();
        addListenerOnButton4();
        addListenerOnButton5();

    }

    public void addListenerOnButton1() {
        ImageButton1 = (ImageButton) findViewById(R.id.ImageButton1);
        ImageButton1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent openia = new Intent(descrizione.this, ia.class);
                startActivity(openia);

            }
        });
    }

    public void addListenerOnButton2() {
        ImageButton2 = (ImageButton) findViewById(R.id.ImageButton2);
        ImageButton2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent openib = new Intent(descrizione.this, ib.class);
                startActivity(openib);

            }
        });
    }

    public void addListenerOnButton5() {
        ImageButton5 = (ImageButton) findViewById(R.id.ImageButton5);
        ImageButton5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent openic = new Intent(descrizione.this, ic.class);
                startActivity(openic);

            }
        });
    }

    public void addListenerOnButton3() {
        ImageButton3 = (ImageButton) findViewById(R.id.ImageButton3);
        ImageButton3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent openid = new Intent(descrizione.this, id.class);
                startActivity(openid);

            }
        });
    }

    public void addListenerOnButton4() {
        ImageButton4 = (ImageButton) findViewById(R.id.ImageButton4);
        ImageButton4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent openie = new Intent(descrizione.this, ie.class);
                startActivity(openie);

            }
        });
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

                Intent opendescrizione = new Intent(descrizione.this, descrizione.class);
                startActivity(opendescrizione);
                break;
            case R.id.item2:
                // Single menu item is selected do something
                // Ex: launching new activity/screen or show alert message
                Intent openinfo = new Intent(descrizione.this, info.class);
                startActivity(openinfo);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
