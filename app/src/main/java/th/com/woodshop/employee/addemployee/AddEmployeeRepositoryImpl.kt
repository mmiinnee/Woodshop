package th.com.woodshop.employee.addemployee

import th.com.woodshop.model.EmployeeEntity
import th.com.woodshop.room.WoodShopDao

class AddEmployeeRepositoryImpl(private val woodShopDao: WoodShopDao) : AddEmployeeRepository {

    override suspend fun insertEmployee(employeeEntity: EmployeeEntity) {
        woodShopDao.insertEmployee(employeeEntity)
    }

    override suspend fun updateEmployee(number: String, name: String, lastName: String, age: String, phoneNumber: String, address: String) {
        woodShopDao.updateEmployeeData(number, name, lastName, age, phoneNumber, address)
    }

    override suspend fun clearEmployeeTable() {
        woodShopDao.clearEmployTable()
    }
}