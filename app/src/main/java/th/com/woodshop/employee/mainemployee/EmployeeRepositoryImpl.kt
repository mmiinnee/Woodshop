package th.com.woodshop.employee.mainemployee

import th.com.woodshop.model.EmployeeEntity
import th.com.woodshop.room.WoodShopDao

class EmployeeRepositoryImpl(private val woodShopDao: WoodShopDao) : EmployeeRepository {

    override suspend fun getEmployeeList(): MutableList<EmployeeEntity> = woodShopDao.getEmployeeList()

    override suspend fun getEmployeeData(employeeNumber: String): EmployeeEntity = woodShopDao.getEmployeeData(employeeNumber)

    override suspend fun deleteEmployeeData(employeeNumber: String) {
        woodShopDao.deleteEmployeeData(employeeNumber)
    }
}