package th.com.woodshop.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import th.com.woodshop.checkname.CheckInFragment
import th.com.woodshop.employee.mainemployee.EmployeeFragment
import th.com.woodshop.menu.MenuFragment
import th.com.woodshop.account.mainaccount.AccountFragment

class ViewPagerAdapter(fragment: FragmentManager, lifecycle: Lifecycle, private var callback: (Int) -> Unit) : FragmentStateAdapter(fragment, lifecycle) {

    private var fragmentPage: Fragment? = null

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                fragmentPage = MenuFragment {
                    callback.invoke(it)
                }
                return fragmentPage!!
            }
            1 -> {
                fragmentPage = CheckInFragment {
                    callback.invoke(it)
                }
                return fragmentPage!!
            }
            2 -> {
                fragmentPage = AccountFragment {
                    callback.invoke(it)
                }
                return fragmentPage!!
            }
            3 -> {
                fragmentPage = EmployeeFragment {
                    callback.invoke(it)
                }
                return fragmentPage!!
            }
            else -> {
                fragmentPage = MenuFragment {
                    callback.invoke(it)
                }
                return fragmentPage!!
            }
        }
    }
}