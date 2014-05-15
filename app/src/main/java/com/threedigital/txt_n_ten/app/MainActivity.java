package com.threedigital.txt_n_ten.app;

import android.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    Button btnSendSMS;
    IntentFilter intentFilter;

    public BroadcastReceiver intentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            TextView SMSes = (TextView) findViewById(R.id.text1);
            SMSes.setText(intent.getExtras().getString("sms"));

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_dialog_item);

        intentFilter = new IntentFilter();
        intentFilter.addAction("SMS_RECEIVED_ACTION");

        registerReceiver(intentReceiver, intentFilter);


        btnSendSMS = (Button) findViewById(R.id.button1);
        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendSMS("3333", "Hey Bae");
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.putExtra("address", "901;462;6171");
                i.putExtra("sms body", "Better Read Fast!");
                i.setType("vnd.android-dir/mms-sms");
                startActivity(i);

            }

        });
    }

    @Override
    protected void onResume() {
        registerReceiver(intentReceiver, intentFilter);
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(intentReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
      unregisterReceiver(intentReceiver);
        super.onDestroy();
    }

    private void sendSMS(String phoneNumber, String message) {
       SmsManager sms = SmsManager.getDefault();
       sms.sendTextMessage(phoneNumber, null, message, null, null);

    }
}
  