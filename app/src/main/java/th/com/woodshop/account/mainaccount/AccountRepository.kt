package th.com.woodshop.account.mainaccount

import th.com.woodshop.model.AccountEntity

interface AccountRepository {

    suspend fun getAccountList(): MutableList<AccountEntity>

    suspend fun deleteAccountData(rowId: Int)

}