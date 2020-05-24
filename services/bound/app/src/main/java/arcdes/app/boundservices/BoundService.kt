package arcdes.app.boundservices

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.util.Log

/**
 * Created by Rhen Nepacena on 21/05/2020.
 *           ArcDes
 **/
class BoundService : Service(){

    private val binder = BoundBinder()
    lateinit var handler:Handler
    var progress:Int = 0
    var maxValue:Int = 0
    var isPaused:Boolean = false

    companion object{
        const val TAG = "BoundService"
    }

    override fun onCreate() {
        super.onCreate()
        handler = Handler()
        progress = 0
        isPaused = true
        maxValue = 5000
    }

    override fun onBind(intent: Intent?): IBinder? {
        logResp("run: onBind Started")
        return binder
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        stopSelf()
        logResp("run: onTaskRemoved")
    }

    override fun onDestroy() {
        super.onDestroy()
        logResp("run: destroyed")
    }

    inner class BoundBinder : Binder() {
        fun getServiceInstance() :BoundService{
            logResp("run: getInstance ")
            return this@BoundService
        }
    }

    private fun startPretendLongRunningTask(){
        val runnable = object:Runnable{
            override fun run() {
                if(progress >= maxValue || isPaused){
                    logResp("run: removing callbacks. ")
                    handler.removeCallbacks(this)
                }else{
                    logResp("run: progress: $progress")
                    progress += 100
                    onInteractionListener?.progressValue(progress,maxValue)
                    handler.postDelayed(this,100)
                }
            }
        }
        handler.postDelayed(runnable,100)
    }

    fun pausePretendLongRunningTask(){
        isPaused = true
    }

    fun unPausePretendLongRunningTask(){
        isPaused = false
        startPretendLongRunningTask()
    }

    fun resetTask(){
        progress = 0
    }

    private fun logResp(message:String?){
        Log.e(TAG," $message")
    }

    var onInteractionListener:OnInteractionListener? = null

    interface OnInteractionListener{
        fun progressValue(concurrentValue:Int,maxValue:Int)
    }
}