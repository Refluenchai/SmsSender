package br.com.foursys.smssender

import android.os.Bundle
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.phone.SmsRetriever

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RequestHintNumbers(this.activityResultRegistry, this).requestHint()

        val client = SmsRetriever.getClient(this)

        val task = client.startSmsRetriever()

        task.addOnSuccessListener {

        }.addOnFailureListener {

        }
    }




}