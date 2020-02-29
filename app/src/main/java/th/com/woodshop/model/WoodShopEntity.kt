package th.com.woodshop.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "CheckIn")
data class CheckInEntity(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("rowId")
    var rowid: Int = 0,

    @ColumnInfo(name = "employeeNumber")
    @SerializedName("employeeNumber")
    var employeeNumber: String? = "",

    @ColumnInfo(name = "timestamp")
    @SerializedName("timestamp")
    var timestamp: String? = ""
)

@Entity(tableName = "Employee", primaryKeys = ["employeeNumber"])
data class EmployeeEntity(
    @SerializedName("employeeNumber")
    var employeeNumber: String = "",

    @ColumnInfo(name = "employeeName")
    @SerializedName("employeeName")
    var employeeName: String? = "",

    @ColumnInfo(name = "employeeLastName")
    @SerializedName("employeeLastName")
    var employeeLastName: String? = "",

    @ColumnInfo(name = "employeeAge")
    @SerializedName("employeeAge")
    var employeeAge: String? = "",

    @ColumnInfo(name = "employeePhoneNumber")
    @SerializedName("employeePhoneNumber")
    var employeePhoneNumber: String? = "",

    @ColumnInfo(name = "employeeaAddress")
    @SerializedName("employeeaAddress")
    var employeeaAddress: String? = ""
)

@Entity(tableName = "Account")
data class AccountEntity(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("rowId")
    var rowid: Int = 0,

    @ColumnInfo(name = "type")
    @SerializedName("type")
    var type: String? = "",

    @ColumnInfo(name = "date")
    @SerializedName("date")
    var date: String? = "",

    @ColumnInfo(name = "detail")
    @SerializedName("detail")
    var detail: String? = "",

    @ColumnInfo(name = "price")
    @SerializedName("price")
    var price: String? = ""
)