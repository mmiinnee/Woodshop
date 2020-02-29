package th.com.woodshop.usecase

import th.com.woodshop.employee.addemployee.AddEmployeeRepositoryImpl
import th.com.woodshop.model.EmployeeEntity
import th.com.woodshop.model.Result

class AddEmployeeUseCase(private val addEmployeeRepositoryImpl: AddEmployeeRepositoryImpl) : BaseCoroutinesUseCase<EmployeeEntity, Boolean>() {
    override suspend fun execute(parameter: EmployeeEntity): Result<Boolean> {
        return try {
            val response = addEmployeeRepositoryImpl.insertEmployee(parameter)
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