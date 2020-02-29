package th.com.woodshop.account.addaccount

import th.com.woodshop.model.AccountEntity
import th.com.woodshop.room.WoodShopDao

class AddAccountRepositoryImpl(private val woodShopDao: WoodShopDao) : AddAccountRepository {
    override suspend fun insertAccount(accountEntity: AccountEntity) {
        woodShopDao.insertAccount(accountEntity)
    }

    override suspend fun deleteAccount(rowId: Int) {
        woodShopDao.deleteAccountData(rowId)
    }

    override suspend fun clearAccountTable() {
        woodShopDao.clearAccountTable()
    }
}