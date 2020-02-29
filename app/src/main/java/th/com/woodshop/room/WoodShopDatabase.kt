package th.com.woodshop.room

import androidx.room.Database
import androidx.room.RoomDatabase
import th.com.woodshop.model.AccountEntity
import th.com.woodshop.model.CheckInEntity
import th.com.woodshop.model.EmployeeEntity

@Database(entities = [CheckInEntity::class, EmployeeEntity::class, AccountEntity::class], version = 2, exportSchema = false)
abstract class WoodShopDatabase : RoomDatabase() {

    abstract fun woodShopDao(): WoodShopDao

    companion object {
        const val DB_NAME = "WoodShop.db"
    }
}