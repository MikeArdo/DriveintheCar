package it.the.advanced.developer.inc.DriveintheCar;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class call extends ActionBarActivity {
    ImageButton button1;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call);

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


    }




    public void addListenerOnButton1() {
        button1 = (ImageButton) findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {


            }
        });
    }

    public void Chiama(View view) {
        EditText txtnumero = (EditText) this.findViewById(R.id.txtnumero);
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + txtnumero.getText().toString()));
        startActivity(intent);
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_IN_CALL);
        audioManager.setSpeakerphoneOn(true);
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

                Intent opendescrizione = new Intent(call.this, descrizione.class);
                startActivity(opendescrizione);
                break;
            case R.id.item2:
                // Single menu item is selected do something
                // Ex: launching new activity/screen or show alert message

                Intent openinfo = new Intent(call.this, info.class);
                startActivity(openinfo);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
