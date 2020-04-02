package arcdes.app.pushypushnotification

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import me.pushy.sdk.Pushy
import java.lang.Exception
import java.lang.ref.WeakReference

/**
 * Created by Rhen Nepacena on 31/03/2020.
 *           ArcDes
 **/

class RegisterForPushNotificationsAsync constructor(private val context: WeakReference<Context>?) :
    AsyncTask<Void, Void, Exception?>() {

    override fun doInBackground(vararg params: Void?): Exception? {
        try {
            val deviceToken = Pushy.register(context?.get())
        } catch (e: Exception) {
            return e
        }

        return null
    }

    override fun onPostExecute(result: Exception?) {
        if(result != null){
            Log.e("registerPushNotif-e","$result")
        }
    }

}