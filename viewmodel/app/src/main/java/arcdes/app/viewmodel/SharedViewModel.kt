package arcdes.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Rhen Nepacena on 07/03/2020.
 *           ArcDes
 **/

class SharedViewModel : ViewModel(){

    var text = MutableLiveData<CharSequence>()

    fun setText(input:CharSequence){
        /* setValue runs on UIThread while postValue runs on Background*/
        text.value = input
    }

    fun getText():LiveData<CharSequence>{
        return text
    }
}