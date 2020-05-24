package arcdes.app.viewmodel

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_ab.*

/**
 * Created by Rhen Nepacena on 08/03/2020.
 *           ArcDes
 **/

class FragmentA : Fragment(){

    var sharedViewModel:SharedViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ab,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
            sharedViewModel = ViewModelProvider(activity as FragmentActivity).get(SharedViewModel::class.java)

        sharedViewModel?.text?.observe(viewLifecycleOwner, Observer{
            et_input.text = Editable.Factory.getInstance().newEditable(it.toString())
        })

        btn_submit.setOnClickListener {
            sharedViewModel?.setText(et_input.text.toString())
        }
    }
}