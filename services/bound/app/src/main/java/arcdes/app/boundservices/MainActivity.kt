package arcdes.app.boundservices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BoundService.OnInteractionListener {

    private lateinit var serviceConnection:ServiceConnection
    private var boundService: BoundService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeServiceConnection()
        interactOnStart()
    }

    override fun onResume() {
        super.onResume()
        startService()
    }

    override fun onPause() {
        super.onPause()
        unbindService(serviceConnection)
    }

    private fun interactOnStart(){
        btn_start.setOnClickListener {
            toggleUpdates()
        }
    }

    private fun startService(){
        val serviceIntent = Intent(this,BoundService::class.java)
        startService(serviceIntent)
        bindService()
    }

    private fun bindService(){
        val serviceIntent = Intent(this,BoundService::class.java)
        bindService(serviceIntent,serviceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun toggleUpdates(){
        if(boundService == null) return

        boundService?.run {
            if(progress == maxValue){
                resetTask()
            }else{
                if(isPaused){
                    unPausePretendLongRunningTask()
                }else{
                    pausePretendLongRunningTask()
                }
            }

        }
    }


    private fun initializeServiceConnection(){

        serviceConnection = object: ServiceConnection{
            override fun onServiceDisconnected(name: ComponentName?) {

            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                val binder = service as BoundService.BoundBinder
                boundService = binder.getServiceInstance()
                boundService?.onInteractionListener = this@MainActivity
            }
        }
    }

    override fun progressValue(concurrentValue: Int, maxValue:Int) {
        val progressString = "${(((100 * concurrentValue) / maxValue))} %"
        pb_progress.progress = concurrentValue
        pb_progress.max = maxValue
        tt_indicator.text = progressString
    }
}