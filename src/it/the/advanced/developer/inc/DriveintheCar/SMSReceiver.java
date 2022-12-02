package it.the.advanced.developer.inc.DriveintheCar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.PhoneLookup;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsMessage;

public class SMSReceiver extends BroadcastReceiver {
    private Context mcontext;
  
  

 /*Check for the intent action and if it matches the one defined in the application, process it*/

    @Override
    public void onReceive(Context context, Intent intent) {
        this.mcontext = context;
        if (intent.getAction().equals(activity_main.ACTION)) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                readSMS(bundle);
            }
        }
    }


    /*
    Method will get sms data from bundle
    */
    public void readSMS(Bundle bundle) {
        SmsMessage[] msgs = null;

        try {

/*It will get pdus object array*/
            Object[] pdus = (Object[]) bundle.get("pdus");

		/*if the pdus data is not null*/
            if (pdus != null) {
  
  /*Create an SmsMessage array of pdus length*/
                msgs = new SmsMessage[pdus.length];

                String smsBodyStr = null;
                String phoneNoStr = null;

		  /* Loop through the length and create SmsMessage object*/
                for (int k = 0; k < msgs.length; k++) {
                    msgs[k] = SmsMessage.createFromPdu((byte[]) pdus[k]);
			   /* Get sms data*/
                    smsBodyStr = msgs[k].getMessageBody().trim();
			   /* Get contact phonenumber*/

                    phoneNoStr = msgs[k].getOriginatingAddress().trim();

		/*provide the SMS data to TTS engine for speaking*/
                    speakSMS(smsBodyStr, phoneNoStr);
                }
            }
        } catch (Exception exe) {
            exe.printStackTrace();
        }
    }

    /*
    speakSMS method will speak the data provided in the speak() method.
    It will get the contact name from the phonebook*/
    public void speakSMS(String smsBodyStr, String phoneNoStr) {

	/*if the phone number is not null*/
        if (phoneNoStr != null) {

/*Get name of the contact from phonebook*/
            String displayName = getContactName(phoneNoStr);

	   /*if unable to find the name of the contact then consider phone number as  
		 Name of the contact*/
            if (displayName == null) {
                displayName = phoneNoStr;
            }

            if (smsBodyStr != null && smsBodyStr.length() > 0) {
                activity_main.textSpeech.speak("SMS ricevuto da, " + displayName
                        + ".Il messaggio è " + smsBodyStr, TextToSpeech.QUEUE_ADD, null);
            }
        }
    }

    /*
    Method will search ih the phonebook database and return the name of the contact
    */
    public String getContactName(String mobileno) {
	   /*Create URI with phone number */
        Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(mobileno));
/* Query database and get back the cursor*/
        Cursor cr = mcontext.getContentResolver().query(uri,
                new String[]{PhoneLookup.DISPLAY_NAME}, null, null, null);
        String displayName = null;

	   /*If cursor count is greater than zero then contact name is found*/
        if (cr.getCount() > 0) {
            cr.moveToFirst();
            displayName = cr.getString(cr.getColumnIndex(PhoneLookup.DISPLAY_NAME));
        }
        return displayName;
    }

}