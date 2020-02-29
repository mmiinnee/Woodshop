package th.com.woodshop.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val loadingLiveData : MutableLiveData<Boolean> = MutableLiveData()
    val errorLiveData : MutableLiveData<String> = MutableLiveData()
}