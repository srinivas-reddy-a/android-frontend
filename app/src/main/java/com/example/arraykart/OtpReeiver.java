package com.example.arraykart;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.EditText;

import androidx.annotation.RequiresApi;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;

public class OtpReeiver extends BroadcastReceiver {
//    public static String Sign_in_page_otp ;
//
//    public void setOtp_text(String text){
//        OtpReeiver.Sign_in_page_otp = text;
//    }
//    public String getOtp(){
//        return Sign_in_page_otp;
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
//        for(SmsMessage smsMessage : messages){
//            String message_body = smsMessage.getMessageBody();
//            String getOtp = message_body.split(":")[1];
////            Sign_in_page_otp.setText(getOtp);
//            Sign_in_page_otp = getOtp;
//        }
//    }

    public SmsBroadcastReceiverListener smsBroadcastReceiverListener;

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction() == SmsRetriever.SMS_RETRIEVED_ACTION){

            Bundle extras = intent.getExtras();

            Status smsRetreiverStatus = (Status) extras.get(SmsRetriever.EXTRA_STATUS);

            switch (smsRetreiverStatus.getStatusCode()){

                case CommonStatusCodes
                        .SUCCESS:
                    Intent messageIntent = extras.getParcelable(SmsRetriever.EXTRA_CONSENT_INTENT);
                    smsBroadcastReceiverListener.onSuccess(messageIntent);
                    break;
                case CommonStatusCodes.TIMEOUT:
                    smsBroadcastReceiverListener.onFailure();
                    break;

            }
        }
    }

    public interface SmsBroadcastReceiverListener{

        void onSuccess(Intent intent);

        void onFailure();


    }
}