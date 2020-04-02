package arcdes.app.pushypushnotification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * Created by Rhen Nepacena on 31/03/2020.
 *           ArcDes
 **/

class PushReceiver : BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationText: String?

        if (intent?.getStringExtra("message") != null) {
            notificationText = intent.getStringExtra("message")
            Log.e("push-receiver-notif-in", "test-$notificationText")
        }
    }
}