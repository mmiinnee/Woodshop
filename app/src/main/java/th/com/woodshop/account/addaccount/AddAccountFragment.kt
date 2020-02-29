package th.com.woodshop.account.addaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.layernet.thaidatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.fragment_add_account.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import th.com.woodshop.R
import th.com.woodshop.alert.AlertMessageDialogFragment
import th.com.woodshop.shareviewmodel.ShareViewModel
import th.com.woodshop.utils.AppConfig
import th.com.woodshop.utils.priceformat.PriceFormatUtility
import java.text.SimpleDateFormat
import java.util.*

class AddAccountFragment : Fragment() {

    var mDay: Int = 0
    var mMonth: Int = 0
    var mYear: Int = 0

    var typeData: String? = ""
    var dateData: String? = ""
    var detailData: String? = ""
    var priceData: String? = ""

    private val vm: AddAccountFragmentViewModel by viewModel()
    private val vmShare: ShareViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        observeData()

        et_date.setOnClickListener {
            val calendar = Calendar.getInstance()
            mDay = calendar.get(Calendar.DAY_OF_MONTH)
            mMonth = calendar.get(Calendar.MONTH)
            mYear = calendar.get(Calendar.YEAR)

            val datePickerDialog = DatePickerDialog.newInstance({ view, year, monthOfYear, dayOfMonth ->
                val dateFormat = SimpleDateFormat("dd/MM/yyyy")
                val dateParse = dateFormat.parse("$dayOfMonth/${(monthOfYear + 1)}/$year")
                et_date.setText(dateFormat.format(dateParse))
            }, mYear, mMonth, mDay)
            datePickerDialog.accentColor = ContextCompat.getColor(context!!, R.color.colorPrimary)
            datePickerDialog.maxDate = calendar
            datePickerDialog.show(activity?.fragmentManager, "")
        }

        et_price.setDashFormat(PriceFormatUtility.DEFAULT_TEXT_FORMAT)

        btn_back.setOnClickListener {
            vmShare.triggerUpdateTitleBar("รายรับ - รายจ่าย")

            activity?.supportFragmentManager?.popBackStack("addAccount", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        btn_add.setOnClickListener {
            typeData = et_type.text.toString()
            dateData = et_date.text.toString()
            detailData = et_detail.text.toString()
            priceData = et_price.text.toString()

            vm.addAccount(typeData!!, dateData!!, detailData!!, priceData!!)
        }
    }

    private fun observeData() {
        vm.addAccountLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                AlertMessageDialogFragment.Builder()
                        .setMessage("เพิ่มข้อมูล รายรับ - รายจ่าย เรียบร้อย")
                        .setCallback {
                            vmShare.triggerUpdateData()
                            activity?.supportFragmentManager?.popBackStack("addAccount", FragmentManager.POP_BACK_STACK_INCLUSIVE)
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

        vm.errorLiveData.observe(viewLifecycleOwner, Observer {
            AlertMessageDialogFragment.Builder()
                    .setMessage(it)
                    .build()
                    .show(activity?.supportFragmentManager!!, AppConfig.TAG)
        })
    }
}
