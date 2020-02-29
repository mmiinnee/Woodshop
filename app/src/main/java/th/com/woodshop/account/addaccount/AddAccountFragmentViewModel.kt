package th.com.woodshop.account.addaccount

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import th.com.woodshop.base.BaseViewModel
import th.com.woodshop.model.AccountEntity
import th.com.woodshop.model.Result
import th.com.woodshop.usecase.AddAccountUseCase

class AddAccountFragmentViewModel(private val addAccountUseCase: AddAccountUseCase) : BaseViewModel() {

    private val accountEntity = AccountEntity()

    val addAccountLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun addAccount(typeData: String, dateData: String, detailData: String, priceData: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                if (typeData.trim().isEmpty()) {
                    errorLiveData.postValue("กรุณาระบุ ประเภท")
                } else if (dateData.trim().isEmpty()) {
                    errorLiveData.postValue("กรุณาระบุ วันที่")
                } else if (detailData.trim().isEmpty()) {
                    errorLiveData.postValue("กรุณาระบุ รายละเอียด")
                } else if (priceData.trim().isEmpty()) {
                    errorLiveData.postValue("กรุณาระบุ ราคา")
                } else {
                    accountEntity.apply {
                        type = typeData
                        date = dateData
                        detail = detailData
                        price = priceData
                    }

                    when (val result = addAccountUseCase.execute(accountEntity)) {
                        is Result.Success -> {
                            addAccountLiveData.postValue(result.data)
                        }
                    }
                }
            }
        }
    }
}