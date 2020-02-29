package th.com.woodshop.employee.addemployee

import th.com.woodshop.model.EmployeeEntity

interface AddEmployeeRepository {
    suspend fun insertEmployee(employeeEntity: EmployeeEntity)

    suspend fun updateEmployee(number: String, name: String, lastName: String, age: String, phoneNumber: String, address: String)

    suspend fun clearEmployeeTable()
}