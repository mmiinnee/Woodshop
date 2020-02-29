package th.com.woodshop.usecase

import th.com.woodshop.employee.mainemployee.EmployeeRepositoryImpl
import th.com.woodshop.model.EmployeeEntity
import th.com.woodshop.model.Result

class GetEmployeeDataUseCase(private val employeeRepositoryImpl: EmployeeRepositoryImpl) : BaseCoroutinesUseCase<String, EmployeeEntity>() {
    override suspend fun execute(parameter: String): Result<EmployeeEntity> {
        return try {
            val response = employeeRepositoryImpl.getEmployeeData(parameter)
            if (response != null) {
                Result.Success(response)
            } else {
                Result.Success(EmployeeEntity())
            }
        } catch (e: Exception) {
            Result.Fail(e.toString())
        }
    }
}