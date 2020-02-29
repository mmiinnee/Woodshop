package th.com.woodshop.checkname

import th.com.woodshop.model.CheckInEntity
import th.com.woodshop.model.EmployeeEntity

interface CheckInRepository {
    suspend fun insertCheckIn(checkInEntity: CheckInEntity)

    suspend fun checkUserEmployee(employeeNumber: String) : EmployeeEntity

    suspend fun clearCheckInTable()
}