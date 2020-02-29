package th.com.woodshop.employee.addemployee

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.fragment_add_employee.*
import th.com.woodshop.R
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import th.com.woodshop.alert.AlertMessageDialogFragment
import th.com.woodshop.shareviewmodel.ShareViewModel
import th.com.woodshop.utils.AppConfig
import th.com.woodshop.utils.phoneformat.PhoneFormatUtility

class AddEmployeeFragment : Fragment() {

    private var name: String? = ""
    private var lastName: String? = ""
    private var age: String? = ""
    private var phoneNumber: String? = ""
    private var address: String? = ""
    private var employeeNumber: String? = ""
    private var editFlag: Boolean? = false

    private val vm: AddEmployeeFragmentViewModel by viewModel()
    private val vmShare: ShareViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_employee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        observeData()

        et_phone_number.setDashFormat(PhoneFormatUtility.DEFAULT_TEXT_FORMAT)

        btn_back.setOnClickListener {
            vmShare.triggerUpdateTitleBar("ข้อมูลพนักงาน")

            activity?.supportFragmentManager?.popBackStack("addEmployee", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        btn_add.apply {
            setOnClickListener {
                name = et_name.text.toString()
                lastName = et_lastname.text.toString()
                age = et_age.text.toString()
                phoneNumber = et_phone_number.text.toString()
                address = et_address.text.toString()

                if (editFlag!!) vm.editEmployeeData(employeeNumber!!, name!!, lastName!!, age!!, phoneNumber!!, address!!)
                else vm.addEmployee(name!!, lastName!!, age!!, phoneNumber!!, address!!)
            }
        }
    }

    private fun observeData() {
        vm.getEmployeeLiveData.observe(viewLifecycleOwner, Observer {
            et_name.setText(it.employeeName)
            et_lastname.setText(it.employeeLastName)
            et_age.setText(it.employeeAge)
            et_phone_number.setText(it.employeePhoneNumber)
            et_address.setText(it.employeeaAddress)
        })

        vm.addEmployeeLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                AlertMessageDialogFragment.Builder()
                        .setMessage("เพิ่มข้อมูล พนักงาน เรียบร้อย")
                        .setCallback {
                            vmShare.triggerUpdateData()
                            activity?.supportFragmentManager?.popBackStack("addEmployee", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                        }
                        .build()
                        .show(activity?.supportFragmentManager!!, AppConfig.TAG)
            } else {
                AlertMessageDialogFragment.Builder()
                        .setMessage("เพิ่มข้อมูลผิดพลาดกรุณาลองใหม่")
                        .build()
                        .show(activity?.supportFragmentManager!!, AppConfig.TAG)
            }
        })

        vm.editEmployeeLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                AlertMessageDialogFragment.Builder()
                        .setMessage("แก้ไขข้อมูล พนักงาน เรียบร้อย")
                        .setCallback {
                            vmShare.triggerUpdateData()
                            activity?.supportFragmentManager?.popBackStack("addEmployee", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                        }
                        .build()
                        .show(activity?.supportFragmentManager!!, AppConfig.TAG)
            } else {
                AlertMessageDialogFragment.Builder()
                        .setMessage("แก้ไขข้อมูลผิดพลาดกรุณาลองใหม่")
                        .build()
                        .show(activity?.supportFragmentManager!!, AppConfig.TAG)
            }
        })

        vm.errorLiveData.observe(viewLifecycleOwner, Observer {
            AlertMessageDialogFragment.Builder()
                    .setMessage(it)
                    .build()
                    .show(activity?.supportFragmentManager!!, AppConfig.TAG)
        })

        vmShare.sendEmployeeNumberLiveData.observe(viewLifecycleOwner, Observer {
            it.apply {
                editFlag = first
                employeeNumber = second
            }

            vm.getEmployeeData(employeeNumber!!)
            btn_add.text = "บันทึก"
        })
    }
}
