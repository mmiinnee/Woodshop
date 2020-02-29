package th.com.woodshop.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import th.com.woodshop.R
import th.com.woodshop.shareviewmodel.ShareViewModel
import th.com.woodshop.viewpager.ViewPagerAdapter

class MainActivity : AppCompatActivity() {

    private val vm: ShareViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewpager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle) {
            viewpager.setCurrentItem(it, true)
        }

        viewpager.isUserInputEnabled = false

        viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                when (position) {
                    0 -> toolbar.tv_title.text = "ระบบการจัดการโรงค้าไม้"
                    1 -> toolbar.tv_title.text = "เช็คชื่อพนักงาน"
                    2 -> toolbar.tv_title.text = "รายรับ - รายจ่าย"
                    3 -> toolbar.tv_title.text = "ข้อมูลพนักงาน"
                }
            }
        })

        TabLayoutMediator(tab_layout, viewpager) { tab, position ->
            when (position) {
                0 -> tab.setIcon(R.drawable.ic_home)
                1 -> tab.setIcon(R.drawable.ic_list)
                2 -> tab.setIcon(R.drawable.ic_money)
                3 -> tab.setIcon(R.drawable.ic_employee)
            }
        }.attach()

        vm.updateTitleBatLiveData.observe(this, Observer {
            toolbar.tv_title.text = it
        })
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}
