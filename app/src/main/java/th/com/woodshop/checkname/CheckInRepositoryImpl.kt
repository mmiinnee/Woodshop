package th.com.woodshop.checkname

import th.com.woodshop.model.CheckInEntity
import th.com.woodshop.model.EmployeeEntity
import th.com.woodshop.room.WoodShopDao

class CheckInRepositoryImpl(private val woodShopDao: WoodShopDao) : CheckInRepository {

    override suspend fun insertCheckIn(checkInEntity: CheckInEntity) {
        woodShopDao.insertCheckIn(checkInEntity)
    }

    override suspend fun checkUserEmployee(employeeNumber: String): EmployeeEntity = woodShopDao.checkUserEmployee(employeeNumber)

    override suspend fun clearCheckInTable() {
        woodShopDao.clearCheckInTable()
    }
}