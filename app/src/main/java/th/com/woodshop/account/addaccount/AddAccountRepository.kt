package th.com.woodshop.account.addaccount

import th.com.woodshop.model.AccountEntity

interface AddAccountRepository {

    suspend fun insertAccount(accountEntity: AccountEntity)

    suspend fun deleteAccount(rowId : Int)

    suspend fun clearAccountTable()
}