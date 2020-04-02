package arcdes.app.pushypushnotification

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import me.pushy.sdk.Pushy
import java.lang.ref.WeakReference


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestRuntimePermission()
        setupPushyNotification()

    }

    private fun requestRuntimePermission() {
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        READ_EXTERNAL_STORAGE,
                        WRITE_EXTERNAL_STORAGE
                    ),
                    0
                )
            }
        }
    }

    private fun setupPushyNotification(){
        Pushy.listen(this)

        if (!Pushy.isRegistered(applicationContext)) {
            var registrationPushNotifAsync: RegisterForPushNotificationsAsync? = null

            if (registrationPushNotifAsync == null) {
                registrationPushNotifAsync = RegisterForPushNotificationsAsync(WeakReference(this))
            }

            registrationPushNotifAsync.execute()
        }
    }
}
