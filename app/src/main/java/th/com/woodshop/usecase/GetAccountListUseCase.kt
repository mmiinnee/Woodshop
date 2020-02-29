package th.com.woodshop.usecase

import th.com.woodshop.account.mainaccount.AccountRepositoryImpl
import th.com.woodshop.model.AccountEntity
import th.com.woodshop.model.Result
import th.com.woodshop.utils.Utils

class GetAccountListUseCase(private val accountRepositoryImpl: AccountRepositoryImpl) : BaseCoroutinesUseCase<Utils, MutableList<AccountEntity>>() {
    override suspend fun execute(parameter: Utils): Result<MutableList<AccountEntity>> {
        return try {
            val response = accountRepositoryImpl.getAccountList()
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