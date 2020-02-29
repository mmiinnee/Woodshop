package th.com.woodshop.account.mainaccount

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import th.com.woodshop.base.BaseViewModel
import th.com.woodshop.model.AccountEntity
import th.com.woodshop.model.Result
import th.com.woodshop.usecase.DeleteAccountDataUseCase
import th.com.woodshop.usecase.GetAccountListUseCase
import th.com.woodshop.utils.Utils

class AccountFragmentViewModel(private val getAccountListUseCase: GetAccountListUseCase,
                               private val deleteAccountDataUseCase: DeleteAccountDataUseCase) : BaseViewModel() {

    val getAccountListLiveData: MutableLiveData<MutableList<AccountEntity>> = MutableLiveData()
    val deleteAccountDataLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getAccountList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when (val result = getAccountListUseCase.execute(Utils)) {
                    is Result.Success -> {
                        getAccountListLiveData.postValue(result.data)
                    }
                }
            }
        }
    }

    fun deleteAccountDate(rowId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when (val result = deleteAccountDataUseCase.execute(rowId)) {
                    is Result.Success -> {
                        deleteAccountDataLiveData.postValue(result.data)
                    }
                    is Result.Fail -> {
                        errorLiveData.postValue(result.exception)
                    }
                }
            }
        }
    }
}