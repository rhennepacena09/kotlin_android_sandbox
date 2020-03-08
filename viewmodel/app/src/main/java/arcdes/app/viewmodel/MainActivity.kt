package arcdes.app.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var sharedViewModel:SharedViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        sharedViewModel?.text?.observe(this, Observer {
            tt_input_label.text = it.toString()
        })
        setupFragmentA()
        setupFragmentB()
    }

    private fun setupFragmentA(){
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.rl_fragmentA,FragmentA())
        ft.commit()
    }

    private fun setupFragmentB(){
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.rl_fragmentB,FragmentB())
        ft.commit()
    }

}
