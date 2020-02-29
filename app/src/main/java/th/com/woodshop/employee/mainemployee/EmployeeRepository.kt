package th.com.woodshop.employee.mainemployee

import th.com.woodshop.model.EmployeeEntity

interface EmployeeRepository {
    suspend fun getEmployeeList(): MutableList<EmployeeEntity>

    suspend fun getEmployeeData(employeeNumber: String) : EmployeeEntity

    suspend fun deleteEmployeeData(employeeNumber: String)
}