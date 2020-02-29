package th.com.woodshop.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.toolbar.view.*
import th.com.woodshop.R
import th.com.woodshop.base.BaseFragment

class MenuFragment(private val callback: (Int) -> Unit) : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        btn_check_name.setOnClickListener {
            callback.invoke(1)
        }
        btn_reve_expen.setOnClickListener {
            callback.invoke(2)
        }
        btn_employee.setOnClickListener {
            callback.invoke(3)
        }
    }
}
