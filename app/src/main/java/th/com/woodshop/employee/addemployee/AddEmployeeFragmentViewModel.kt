package th.com.woodshop.employee.addemployee

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import th.com.woodshop.base.BaseViewModel
import th.com.woodshop.model.EmployeeEntity
import th.com.woodshop.model.Result
import th.com.woodshop.usecase.AddEmployeeUseCase
import th.com.woodshop.usecase.EditEmployeeUseCase
import th.com.woodshop.usecase.GetEmployeeDataUseCase
import th.com.woodshop.utils.Utils

class AddEmployeeFragmentViewModel(private val addEmployeeUseCase: AddEmployeeUseCase,
                                   private val editEmployeeUseCase: EditEmployeeUseCase,
                                   private val getEmployeeDataUseCase: GetEmployeeDataUseCase) : BaseViewModel() {

    private val employeeEntity = EmployeeEntity()

    val addEmployeeLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val editEmployeeLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val getEmployeeLiveData: MutableLiveData<EmployeeEntity> = MutableLiveData()

    fun addEmployee(name: String, lastName: String, age: String, phoneNumber: String, address: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                if (name.trim().isEmpty()) {
                    errorLiveData.postValue("กรุณาระบุ ชื่อ")
                } else if (lastName.trim().isEmpty()) {
                    errorLiveData.postValue("กรุณาระบุ นามสกุล")
                } else if (age.trim().isEmpty()) {
                    errorLiveData.postValue("กรุณาระบุ อายุ")
                } else if (phoneNumber.trim().isEmpty() || phoneNumber.length != 12 || phoneNumber.substring(0, 1) != "0") {
                    errorLiveData.postValue("กรุณาระบุ เบอร์โทรศัพท์")
                } else if (address.trim().isEmpty()) {
                    errorLiveData.postValue("กรุณาระบุ ที่อยู่")
                } else {
                    employeeEntity.apply {
                        employeeNumber = Utils.generateRandom(6)
                        employeeName = name
                        employeeLastName = lastName
                        employeeAge = age
                        employeePhoneNumber = phoneNumber
                        employeeaAddress = address
                    }

                    when (val result = addEmployeeUseCase.execute(employeeEntity)) {
                        is Result.Success -> {
                            addEmployeeLiveData.postValue(result.data)
                        }
                        is Result.Fail -> {
                            errorLiveData.postValue(result.exception)
                        }
                    }
                }
            }
        }
    }

    fun editEmployeeData(number: String, name: String, lastName: String, age: String, phoneNumber: String, address: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                if (name.trim().isEmpty()) {
                    errorLiveData.postValue("กรุณาระบุ ชื่อ")
                } else if (lastName.trim().isEmpty()) {
                    errorLiveData.postValue("กรุณาระบุ นามสกุล")
                } else if (age.trim().isEmpty()) {
                    errorLiveData.postValue("กรุณาระบุ อายุ")
                } else if (phoneNumber.trim().isEmpty() || phoneNumber.length != 12 || phoneNumber.substring(0, 1) != "0") {
                    errorLiveData.postValue("กรุณาระบุ เบอร์โทรศัพท์")
                } else if (address.trim().isEmpty()) {
                    errorLiveData.postValue("กรุณาระบุ ที่อยู่")
                } else {
                    employeeEntity.apply {
                        employeeNumber = number
                        employeeName = name
                        employeeLastName = lastName
                        employeeAge = age
                        employeePhoneNumber = phoneNumber
                        employeeaAddress = address
                    }

                    when (val result = editEmployeeUseCase.execute(employeeEntity)) {
                        is Result.Success -> {
                            editEmployeeLiveData.postValue(result.data)
                        }
                        is Result.Fail -> {
                            errorLiveData.postValue(result.exception)
                        }
                    }
                }
            }
        }
    }

    fun getEmployeeData(number: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                when (val result = getEmployeeDataUseCase.execute(number)) {
                    is Result.Success -> {
                        getEmployeeLiveData.postValue(result.data)
                    }
                    is Result.Fail -> {
                        errorLiveData.postValue(result.exception)
                    }
                }
            }
        }
    }
}