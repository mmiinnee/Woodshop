package th.com.woodshop.usecase

import th.com.woodshop.employee.mainemployee.EmployeeRepositoryImpl
import th.com.woodshop.model.EmployeeEntity
import th.com.woodshop.model.Result
import th.com.woodshop.utils.Utils

class DeleteEmployeeDataUseCase(private val employeeRepositoryImpl: EmployeeRepositoryImpl) : BaseCoroutinesUseCase<String, Boolean>() {
    override suspend fun execute(parameter: String): Result<Boolean> {
        return try {
            val response = employeeRepositoryImpl.deleteEmployeeData(parameter)
            if (response != null) {
                Result.Success(true)
            } else {
                Result.Success(false)
            }
        } catch (e: Exception) {
            Result.Fail(e.toString())
        }
    }
}