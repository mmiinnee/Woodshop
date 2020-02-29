package th.com.woodshop.employee.mainemployee

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_employee.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

import th.com.woodshop.R
import th.com.woodshop.alert.AlertMessageDialogFragment
import th.com.woodshop.employee.addemployee.AddEmployeeFragment
import th.com.woodshop.shareviewmodel.ShareViewModel
import th.com.woodshop.utils.AppConfig

class EmployeeFragment(private val callback: (Int) -> Unit) : Fragment() {

    private lateinit var employeeAdapter: EmployeeAdapter
    private val vmShare: ShareViewModel by sharedViewModel()
    private val vm: EmployeeFragmentViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_employee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        setAdapter()

        vm.getEmployeeData()

        observeData()

        btn_back.setOnClickListener {
            callback.invoke(0)
        }

        btn_add.setOnClickListener {
            vmShare.triggerUpdateTitleBar("เพิ่มข้อมูลพนักงาน")

            activity?.supportFragmentManager?.beginTransaction()
                    ?.add(R.id.frame_layout, AddEmployeeFragment::class.java, null)
                    ?.addToBackStack("addEmployee")
                    ?.commit()
        }
    }

    private fun observeData() {
        vmShare.updateDataLiveData.observe(viewLifecycleOwner, Observer {
            vm.getEmployeeData()
        })

        vm.getEmployeeLiveData.observe(viewLifecycleOwner, Observer {
            employeeAdapter.setListData(it)
        })

        vm.deleteEmployeeDataLiveData.observe(viewLifecycleOwner, Observer {
            vm.getEmployeeData()
        })

        vm.errorLiveData.observe(viewLifecycleOwner, Observer {
            AlertMessageDialogFragment.Builder()
                    .setMessage(it)
                    .build()
                    .show(activity?.supportFragmentManager!!, AppConfig.TAG)
        })
    }

    private fun setAdapter() {
        employeeAdapter = EmployeeAdapter { status: Boolean, employeeNumber: String ->
            if (status) {
                vmShare.triggerUpdateTitleBar("แก้ไขข้อมูลพนักงาน")

                activity?.supportFragmentManager?.beginTransaction()
                        ?.add(R.id.frame_layout, AddEmployeeFragment::class.java, null)
                        ?.addToBackStack("addEmployee")
                        ?.commit()

                vmShare.sendEmployeeNumber(status, employeeNumber)
            } else {
                vm.deleteEmployeeData(employeeNumber)
            }
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = employeeAdapter
        }
    }
}
