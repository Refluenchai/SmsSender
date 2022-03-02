package br.com.foursys.smssender

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status

class SMSBroadcastReceiver: BroadcastReceiver() {

    private var message: String = ""

    override fun onReceive(context: Context, intent: Intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.action)) {
            val extras = intent.extras
            val status = extras?.get(SmsRetriever.EXTRA_STATUS) as Status

            message = when (status.statusCode) {
                CommonStatusCodes.SUCCESS -> extras.get(SmsRetriever.EXTRA_SMS_MESSAGE) as String
                else -> {""}
            }
        }
    }
}