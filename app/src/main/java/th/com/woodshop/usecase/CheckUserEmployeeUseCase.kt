package th.com.woodshop.usecase

import th.com.woodshop.checkname.CheckInRepositoryImpl
import th.com.woodshop.model.Result

class CheckUserEmployeeUseCase(private val checkInRepositoryImpl: CheckInRepositoryImpl) : BaseCoroutinesUseCase<String, Boolean>() {
    override suspend fun execute(parameter: String): Result<Boolean> {
        return try {
            val response = checkInRepositoryImpl.checkUserEmployee(parameter)
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