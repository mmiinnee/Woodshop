package th.com.woodshop.checkname

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import th.com.woodshop.base.BaseViewModel
import th.com.woodshop.model.CheckInEntity
import th.com.woodshop.model.Result
import th.com.woodshop.usecase.CheckInUseCase
import th.com.woodshop.usecase.CheckUserEmployeeUseCase
import java.text.SimpleDateFormat
import java.util.*

class CheckInFragmentViewModel(private val checkInUseCase: CheckInUseCase,
                               private val checkUserEmployeeUseCase: CheckUserEmployeeUseCase) : BaseViewModel() {

    private val checkInEntity = CheckInEntity()

    val checkInLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val checkUserEmployeeLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private fun checkIn(employeeNumber: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                checkInEntity.employeeNumber = employeeNumber
                checkInEntity.timestamp = SimpleDateFormat("dd-MM-yyyy_HH:mm:ss", Locale.ENGLISH).format(Date())

                when (val result = checkInUseCase.execute(checkInEntity)) {
                    is Result.Success -> {
                        checkInLiveData.postValue(result.data)
                    }
                    is Result.Fail -> {
                        errorLiveData.postValue(result.exception)
                    }
                }
            }
        }
    }

    fun checkUserEmployee(employeeNumber: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when (val result = checkUserEmployeeUseCase.execute(employeeNumber)) {
                    is Result.Success -> {
                        if (result.data) {
                            checkIn(employeeNumber)
                        }
                        checkUserEmployeeLiveData.postValue(result.data)
                    }
                    is Result.Fail -> {
                        errorLiveData.postValue(result.exception)
                    }
                }
            }
        }
    }
}