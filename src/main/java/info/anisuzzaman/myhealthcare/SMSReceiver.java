package info.anisuzzaman.myhealthcare;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Babla on 8/4/2016.
 */
public class SMSReceiver extends BroadcastReceiver {
    private ArrayList<String> numbersArrayList = new ArrayList<>();
    String fromNumber = "";
    String MyFromNumber = "+8801993953509"; // GSM Module or device sim number

    @Override
    public void onReceive(Context context, Intent intent) {
        // Get Bundle object contained in the SMS intent passed in

        numbersArrayList.add("+8801974069899");
        numbersArrayList.add("+8801680729854");
        numbersArrayList.add("+8801829894159");
        numbersArrayList.add("+8801671186877");
        numbersArrayList.add("+8801717286919");
        numbersArrayList.add("+8801628300840");
        Bundle bundle = intent.getExtras();
        SmsMessage[] smsm = null;
        String sms_str = "";
        if (bundle != null) {
            // Get the SMS message
            Object[] pdus = (Object[]) bundle.get("pdus");
            smsm = new SmsMessage[pdus.length];
            for (int i = 0; i < smsm.length; i++) {
                smsm[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                sms_str += "Sent From: " + smsm[i].getOriginatingAddress();
                fromNumber = smsm[i].getOriginatingAddress();
                sms_str += "\r\nMessage: ";
                sms_str += smsm[i].getMessageBody().toString();
                sms_str += "\r\n";
            }

            if (fromNumber.equalsIgnoreCase(MyFromNumber)) {
                Log.i("Babla", sms_str);
                String message = sms_str + "\nLatitude: " + MyApplication.getInstance().latitude + "\nLongitude: " + MyApplication.getInstance().longitude;
                SmsManager sms = SmsManager.getDefault();
                for (String number : numbersArrayList) {
                    sms.sendTextMessage(number, null, message, null, null);
                }
            }


        }
    }
}