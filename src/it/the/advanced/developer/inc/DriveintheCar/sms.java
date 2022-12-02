package it.the.advanced.developer.inc.DriveintheCar;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;


public class sms extends ActionBarActivity implements OnClickListener {


    private static final int RESULT_SPEECH = 1;
    private EditText txttext;
    EditText txtnumber;
    Button btninvia;
    private Button btnSpeak;
    Button button2;
    private Toolbar toolbar;

	  /* called when the activity is first created. */

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms);
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
        txttext = (EditText) findViewById(R.id.txttext);
        txtnumber = (EditText) findViewById(R.id.txtnumber);
        btnSpeak = (Button) findViewById(R.id.btnSpeak);
        btninvia = (Button) findViewById(R.id.btninvia);


        btnSpeak.setOnClickListener(new View.OnClickListener() {

                                        @Override
                                        public void onClick(View v) {

                                            Intent intent = new Intent(
                                                    RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                                            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
                                            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "it-IT");
                                            try {
                                                startActivityForResult(intent, RESULT_SPEECH);
                                                txttext.setText("");
                                            } catch (ActivityNotFoundException a) {
                                                Toast t = Toast.makeText(getApplicationContext(),
                                                        "Ops! Your device doesn't support Speech to Text",
                                                        Toast.LENGTH_SHORT);
                                                t.show();
                                            }
                                        }
                                    }
        );
        btninvia.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String phoneNo = txtnumber.getText().toString();
                String sms = txttext.getText().toString();
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                    Toast.makeText(getApplicationContext(), "SMS Inviato!",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "SMS fallito, si prega di riprovare!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();


                }
            }
        })
        ;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    txttext.setText(text.get(0));
                }
                break;
            }
        }

        btninvia.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String phoneNo = txtnumber.getText().toString();
                String sms = txttext.getText().toString();
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                    Toast.makeText(getApplicationContext(), "SMS Inviato!",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "SMS fallito, si prega di riprovare!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();


                }
            }
        })
                ;
    }



    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

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
                Intent opendescrizione = new Intent(sms.this, descrizione.class);
                startActivity(opendescrizione);
                break;
            case R.id.item2:
                // Single menu item is selected do something
                // Ex: launching new activity/screen or show alert message
                Intent openinfo = new Intent(sms.this, info.class);
                startActivity(openinfo);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}



