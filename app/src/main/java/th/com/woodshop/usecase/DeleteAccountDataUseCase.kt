package th.com.woodshop.usecase

import th.com.woodshop.account.mainaccount.AccountRepositoryImpl
import th.com.woodshop.employee.mainemployee.EmployeeRepositoryImpl
import th.com.woodshop.model.EmployeeEntity
import th.com.woodshop.model.Result
import th.com.woodshop.utils.Utils

class DeleteAccountDataUseCase(private val accountRepositoryImpl: AccountRepositoryImpl) : BaseCoroutinesUseCase<Int, Boolean>() {
    override suspend fun execute(parameter: Int): Result<Boolean> {
        return try {
            val response = accountRepositoryImpl.deleteAccountData(parameter)
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