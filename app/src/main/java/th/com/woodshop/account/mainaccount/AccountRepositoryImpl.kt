package th.com.woodshop.account.mainaccount

import th.com.woodshop.model.AccountEntity
import th.com.woodshop.room.WoodShopDao

class AccountRepositoryImpl(private val woodShopDao: WoodShopDao) : AccountRepository {

    override suspend fun getAccountList(): MutableList<AccountEntity> = woodShopDao.getAccountList()

    override suspend fun deleteAccountData(rowId: Int) {
        woodShopDao.deleteAccountData(rowId)
    }
}