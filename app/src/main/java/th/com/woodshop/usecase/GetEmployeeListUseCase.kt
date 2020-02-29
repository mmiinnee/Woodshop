package th.com.woodshop.usecase

import th.com.woodshop.employee.mainemployee.EmployeeRepositoryImpl
import th.com.woodshop.model.EmployeeEntity
import th.com.woodshop.model.Result
import th.com.woodshop.utils.Utils

class GetEmployeeListUseCase(private val employeeRepositoryImpl: EmployeeRepositoryImpl) : BaseCoroutinesUseCase<Utils, MutableList<EmployeeEntity>>() {
    override suspend fun execute(parameter: Utils): Result<MutableList<EmployeeEntity>> {
        return try {
            val response = employeeRepositoryImpl.getEmployeeList()
            if (response != null) {
                Result.Success(response)
            } else {
                Result.Success(arrayListOf())
            }
        } catch (e: Exception) {
            Result.Fail(e.toString())
        }
    }
}