package th.com.woodshop.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import th.com.woodshop.model.AccountEntity
import th.com.woodshop.model.CheckInEntity
import th.com.woodshop.model.EmployeeEntity

@Dao
interface WoodShopDao {

    //    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Insert
    suspend fun insertCheckIn(checkInEntity: CheckInEntity)

    @Query("SELECT * FROM Employee WHERE employeeNumber = :number")
    suspend fun checkUserEmployee(number: String): EmployeeEntity

    @Query("DELETE FROM CheckIn")
    suspend fun clearCheckInTable()

    @Insert
    suspend fun insertEmployee(employeeEntity: EmployeeEntity)

    @Query("SELECT * FROM Employee")
    suspend fun getEmployeeList(): MutableList<EmployeeEntity>

    @Query("SELECT * FROM Employee WHERE employeeNumber = :number")
    suspend fun getEmployeeData(number: String): EmployeeEntity

    @Query("UPDATE Employee SET employeeName = :name, employeeLastName = :lastName, employeeAge = :age, employeePhoneNumber = :phoneNumber, employeeaAddress = :address WHERE employeeNumber = :number")
    suspend fun updateEmployeeData(number: String, name: String, lastName: String, age: String, phoneNumber: String, address: String)

    @Query("DELETE FROM Employee WHERE employeeNumber = :number")
    suspend fun deleteEmployeeData(number: String)

    @Query("DELETE FROM Employee")
    suspend fun clearEmployTable()

    @Insert
    suspend fun insertAccount(accountEntity: AccountEntity)

    @Query("SELECT * FROM Account")
    suspend fun getAccountList(): MutableList<AccountEntity>

    @Query("DELETE FROM Account WHERE rowid = :rowId")
    suspend fun deleteAccountData(rowId: Int)

    @Query("DELETE FROM Account")
    suspend fun clearAccountTable()

}