package arcdes.app.samplekoin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.koin.android.ext.android.get

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        definingSingleInModules()
        definingFactoryModules()
    }

    private fun definingSingleInModules(){
        val first = get<SingleModule>()
        first.foo()
        val second = get<SingleModule>()
        second.foo()

        Log.e("${this::class}","- first $first")
        Log.e("${this::class}","- first $second")
    }

    private fun definingFactoryModules(){
        val firstFactory = get<FactoryModule>()
        firstFactory.foo()
        val secondFactory = get<FactoryModule>()
        secondFactory.foo()

        Log.e("${this::class}","- first $firstFactory")
        Log.e("${this::class}","- first $secondFactory")
    }

}