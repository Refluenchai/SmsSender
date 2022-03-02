package br.com.foursys.smssender

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.gms.auth.api.credentials.Credential
import com.google.android.gms.auth.api.credentials.Credentials
import com.google.android.gms.auth.api.credentials.HintRequest

class RequestHintNumbers(
    private val registry: ActivityResultRegistry,
    private val activity: AppCompatActivity
) : DefaultLifecycleObserver {

    var credential: Credential? = null
    lateinit var launcher: ActivityResultLauncher<IntentSenderRequest>

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        launcher = registry.register("key", owner, StartIntentSenderForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                credential = result.data?.getParcelableExtra(Credential.EXTRA_KEY)
            }
        }
    }

    fun requestHint() {
        val hintRequest = HintRequest.Builder()
            .setPhoneNumberIdentifierSupported(true)
            .build()

        val intent = Credentials.getClient(activity).getHintPickerIntent(hintRequest)

        val intentSenderRequest = IntentSenderRequest.Builder(intent.intentSender).build()

        launcher.launch(intentSenderRequest)
    }
}