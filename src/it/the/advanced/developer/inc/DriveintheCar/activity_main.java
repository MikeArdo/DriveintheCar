package it.the.advanced.developer.inc.DriveintheCar;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;



public class activity_main extends ActionBarActivity implements SensorEventListener, OnClickListener,
        OnInitListener {
    private Toolbar toolbar;
    ImageButton ImageButton1;
    ImageButton ImageButton2;
    ImageButton ImageButton3;
    ImageButton ImageButton4;
    ImageButton ImageButton5;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private int MY_DATA_CHECK_CODE = 0;
    public static TextToSpeech textSpeech;
    public static final String ACTION =
            "android.provider.Telephony.SMS_RECEIVED";
    protected static final int REQUEST_CODE = 1234;
    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;


    private SMSReceiver receiver;
    private static final String AD_UNIT_ID = "REMOVED_SECRET";
    private static final String TAG = "ExampleActivity";
    private InterstitialAd iAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        receiver = new SMSReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION);
        registerReceiver(receiver, filter);
        Intent findIntent = new Intent();
        findIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(findIntent, MY_DATA_CHECK_CODE);
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_IN_CALL);
        audioManager.setSpeakerphoneOn(true);

        getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);

        iAd = new InterstitialAd(this);
        iAd.setAdUnitId(AD_UNIT_ID);

        iAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.d(TAG, "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                String errorMessage = String.format("Failed to load add : "+ getErrorReason(errorCode));
                Log.d(TAG, errorMessage);
            }
        });

        loadInterstitial();

    }




    private void addListenerOnButton5() {

        ImageButton5 = (ImageButton) findViewById(R.id.ImageButton5);
        ImageButton5.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View arg0) {

                final Intent intent = new Intent(Intent.ACTION_MAIN, null);

                intent.addCategory(Intent.CATEGORY_LAUNCHER);

                try {
                    final ComponentName cn = new ComponentName("com.google.android.googlequicksearchbox", "com.google.android.googlequicksearchbox.VoiceSearchActivity");


                    intent.setComponent(cn);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(intent);
                } catch (Exception a) {
                    Intent i = new Intent(Intent.ACTION_MAIN);
                    PackageManager manager = getPackageManager();
                    i = manager.getLaunchIntentForPackage("com.google.android.voicesearch");
                    i.addCategory(Intent.CATEGORY_LAUNCHER);
                    startActivity(i);
                }
            }
        });
    }

    public void addListenerOnButton1() {
        ImageButton1 = (ImageButton) findViewById(R.id.ImageButton1);
        ImageButton1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {


                Intent opencall = new Intent(activity_main.this, call.class);
                startActivity(opencall);

            }
        });
    }


    public void addListenerOnButton3() {
        ImageButton3 = (ImageButton) findViewById(R.id.ImageButton3);
        ImageButton3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {



                final Intent intent = new Intent(Intent.ACTION_MAIN, null);

                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                final ComponentName cn = new ComponentName("com.google.android.apps.maps", "com.google.android.maps.driveabout.app.DestinationActivity");


                intent.setComponent(cn);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);


            }
        });
    }

    public void addListenerOnButton4() {
        ImageButton4 = (ImageButton) findViewById(R.id.ImageButton4);
        ImageButton4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent openmediaplayer = new Intent(activity_main.this, mediaplayer.class);
                startActivity(openmediaplayer);


            }
        });
    }

    public void addListenerOnButton2() {
        ImageButton2 = (ImageButton) findViewById(R.id.ImageButton2);
        ImageButton2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent opensms = new Intent(activity_main.this, sms.class);
                startActivity(opensms);


            }
        });
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub
        if (event.values[0] == 0) {
            final Intent intent = new Intent(Intent.ACTION_MAIN, null);

            intent.addCategory(Intent.CATEGORY_LAUNCHER);

            try {
                final ComponentName cn = new ComponentName("com.google.android.googlequicksearchbox", "com.google.android.googlequicksearchbox.VoiceSearchActivity");


                intent.setComponent(cn);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
            } catch (Exception a) {
                Intent i = new Intent(Intent.ACTION_MAIN);
                PackageManager manager = getPackageManager();
                i = manager.getLaunchIntentForPackage("com.google.android.voicesearch");
                i.addCategory(Intent.CATEGORY_LAUNCHER);
                startActivity(i);
            }
        }


    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onInit(int status) {
        // TODO Auto-generated method stub
        /*Success. Engine loaded.*/
        if (status == TextToSpeech.SUCCESS) {

        } else
		  /*Failure. Unable to load Engine.*/
            if (status == TextToSpeech.ERROR) {

            }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.layout.activity_main:
                registerSMS();
                break;

        }
    }

    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
			/*
			MY_DATA_CHECK_CODE indicates availability of TTS Engine else if not Available then notify user to install the 
			relevant feature using TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA action
			*/
        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
						/*Initialize the TTS engine*/
                textSpeech = new TextToSpeech(this, this);
            } else {
                Toast.makeText(getApplicationContext(), "Installa TTS, voice data",
                        Toast.LENGTH_LONG).show();
                Intent installIntent = new Intent();
                installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }
    }

    public void registerSMS() {
        receiver = new SMSReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION);
        registerReceiver(receiver, filter);

    }

    protected void onActivityResult1(int requestCode, int resultCode, Intent data) {
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {

        }

        super.onActivityResult(requestCode, resultCode, data);
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

                Intent opendescrizione = new Intent(activity_main.this, descrizione.class);
                startActivity(opendescrizione);
                break;
            case R.id.item2:
                // Single menu item is selected do something
                // Ex: launching new activity/screen or show alert message

                Intent openinfo = new Intent(activity_main.this, info.class);
                startActivity(openinfo);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    public void loadInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        iAd.loadAd(adRequest);
    }

    public void showInterstitial() {
        if (iAd.isLoaded()) {
            iAd.show();
        } else {
            Log.d(TAG, "Interstitial ad is not loaded yet");
        }
    }

    /**
     * Gets a string error reason from an error code
     *
     * @param errorCode
     * @return
     */
    private String getErrorReason(int errorCode) {

        String errorReason = "unknown error";

        switch(errorCode) {
            case AdRequest.ERROR_CODE_INTERNAL_ERROR:
                errorReason = "internal error";
                break;
            case AdRequest.ERROR_CODE_INVALID_REQUEST:
                errorReason = "invalid request";
                break;
            case AdRequest.ERROR_CODE_NETWORK_ERROR:
                errorReason = "network Error";
                break;
            case AdRequest.ERROR_CODE_NO_FILL:
                errorReason = "no fill";
                break;
        }
        return errorReason;
    }

    @Override
    protected void onDestroy() {
        mSensorManager.unregisterListener(this);
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_NORMAL);
        audioManager.setSpeakerphoneOn(false);
        showInterstitial();
        super.onDestroy();
    }

}