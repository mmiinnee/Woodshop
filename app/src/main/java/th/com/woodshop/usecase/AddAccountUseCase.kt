package th.com.woodshop.usecase

import th.com.woodshop.account.addaccount.AddAccountRepositoryImpl
import th.com.woodshop.employee.addemployee.AddEmployeeRepositoryImpl
import th.com.woodshop.model.AccountEntity
import th.com.woodshop.model.EmployeeEntity
import th.com.woodshop.model.Result

class AddAccountUseCase(private val addAccountRepositoryImpl: AddAccountRepositoryImpl) : BaseCoroutinesUseCase<AccountEntity, Boolean>() {
    override suspend fun execute(parameter: AccountEntity): Result<Boolean> {
        return try {
            val response = addAccountRepositoryImpl.insertAccount(parameter)
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