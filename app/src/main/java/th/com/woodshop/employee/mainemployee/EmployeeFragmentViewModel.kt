package th.com.woodshop.employee.mainemployee

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import th.com.woodshop.base.BaseViewModel
import th.com.woodshop.model.EmployeeEntity
import th.com.woodshop.model.Result
import th.com.woodshop.usecase.DeleteEmployeeDataUseCase
import th.com.woodshop.usecase.GetEmployeeListUseCase
import th.com.woodshop.utils.Utils

class EmployeeFragmentViewModel(private val getEmployeeListUseCase: GetEmployeeListUseCase,
                                private val deleteEmployeeDataUseCase: DeleteEmployeeDataUseCase) : BaseViewModel() {

    val getEmployeeLiveData: MutableLiveData<MutableList<EmployeeEntity>> = MutableLiveData()
    val deleteEmployeeDataLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getEmployeeData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when (val result = getEmployeeListUseCase.execute(Utils)) {
                    is Result.Success -> {
                        getEmployeeLiveData.postValue(result.data)
                    }
                }
            }
        }
    }

    fun deleteEmployeeData(employeeNumber: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when (val result = deleteEmployeeDataUseCase.execute(employeeNumber)) {
                    is Result.Success -> {
                        deleteEmployeeDataLiveData.postValue(result.data)
                    }
                    is Result.Fail -> {
                        errorLiveData.postValue(result.exception)
                    }
                }
            }
        }
    }
}