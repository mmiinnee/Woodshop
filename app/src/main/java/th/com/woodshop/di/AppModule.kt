package th.com.woodshop.di

import androidx.room.Room
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import th.com.woodshop.account.addaccount.AddAccountFragmentViewModel
import th.com.woodshop.account.addaccount.AddAccountRepositoryImpl
import th.com.woodshop.account.mainaccount.AccountFragmentViewModel
import th.com.woodshop.account.mainaccount.AccountRepositoryImpl
import th.com.woodshop.checkname.CheckInFragmentViewModel
import th.com.woodshop.checkname.CheckInRepositoryImpl
import th.com.woodshop.employee.mainemployee.EmployeeFragmentViewModel
import th.com.woodshop.employee.mainemployee.EmployeeRepositoryImpl
import th.com.woodshop.employee.addemployee.AddEmployeeFragmentViewModel
import th.com.woodshop.employee.addemployee.AddEmployeeRepositoryImpl
import th.com.woodshop.shareviewmodel.ShareViewModel
import th.com.woodshop.room.WoodShopDatabase
import th.com.woodshop.usecase.*

val appModule = module {
}

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), WoodShopDatabase::class.java, WoodShopDatabase.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}

val repositoryModule = module {
    single {
        val database: WoodShopDatabase = get()
        CheckInRepositoryImpl(database.woodShopDao())
    }

    single {
        val database: WoodShopDatabase = get()
        AddEmployeeRepositoryImpl(database.woodShopDao())
    }

    single {
        val database: WoodShopDatabase = get()
        EmployeeRepositoryImpl(database.woodShopDao())
    }

    single {
        val database: WoodShopDatabase = get()
        AddAccountRepositoryImpl(database.woodShopDao())
    }

    single {
        val database: WoodShopDatabase = get()
        AccountRepositoryImpl(database.woodShopDao())
    }
}

val useCaseModule = module {
    factory { CheckInUseCase(get()) }
    factory { CheckUserEmployeeUseCase(get()) }
    factory { AddEmployeeUseCase(get()) }
    factory { GetEmployeeListUseCase(get()) }
    factory { EditEmployeeUseCase(get()) }
    factory { GetEmployeeDataUseCase(get()) }
    factory { DeleteEmployeeDataUseCase(get()) }
    factory { AddAccountUseCase(get()) }
    factory { GetAccountListUseCase(get()) }
    factory { DeleteAccountDataUseCase(get()) }
}

val viewModelModule = module {
    viewModel { CheckInFragmentViewModel(get(), get()) }
    viewModel { AddEmployeeFragmentViewModel(get(), get(), get()) }
    viewModel { ShareViewModel() }
    viewModel { EmployeeFragmentViewModel(get(), get()) }
    viewModel { AddAccountFragmentViewModel(get()) }
    viewModel { AccountFragmentViewModel(get(), get()) }
}