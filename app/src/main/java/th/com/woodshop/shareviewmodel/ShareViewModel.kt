package th.com.woodshop.shareviewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import th.com.woodshop.base.BaseViewModel
import th.com.woodshop.utils.SingleLiveEvent

class ShareViewModel : BaseViewModel() {
    val updateDataLiveData: MutableLiveData<Unit> = MutableLiveData()
    val updateTitleBatLiveData: MutableLiveData<String> = MutableLiveData()
    val sendEmployeeNumberLiveData: SingleLiveEvent<Pair<Boolean, String>> = SingleLiveEvent()

    fun triggerUpdateData() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                updateDataLiveData.postValue(Unit)
            }
        }
    }

    fun triggerUpdateTitleBar(value: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                updateTitleBatLiveData.postValue(value)
            }
        }
    }

    fun sendEmployeeNumber(status: Boolean, value: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                sendEmployeeNumberLiveData.postValue(Pair(status, value))
            }
        }
    }
}