package th.com.woodshop.usecase

import th.com.woodshop.checkname.CheckInRepositoryImpl
import th.com.woodshop.model.CheckInEntity
import th.com.woodshop.model.Result

class CheckInUseCase(private val checkInRepositoryImpl: CheckInRepositoryImpl) : BaseCoroutinesUseCase<CheckInEntity, Boolean>() {
    override suspend fun execute(parameter: CheckInEntity): Result<Boolean> {
        return try {
            val response = checkInRepositoryImpl.insertCheckIn(parameter)
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