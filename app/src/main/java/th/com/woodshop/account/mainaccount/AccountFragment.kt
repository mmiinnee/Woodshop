package th.com.woodshop.account.mainaccount

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_reve_and_expen.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

import th.com.woodshop.R
import th.com.woodshop.shareviewmodel.ShareViewModel
import th.com.woodshop.account.addaccount.AddAccountFragment
import th.com.woodshop.alert.AlertMessageDialogFragment
import th.com.woodshop.utils.AppConfig

class AccountFragment(private val callback: (Int) -> Unit) : Fragment() {

    private lateinit var accountAdapter: AccountAdapter

    private val vm: AccountFragmentViewModel by viewModel()
    private val vmShare: ShareViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_reve_and_expen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        setAdapter()

        vm.getAccountList()

        observeData()

        btn_back.setOnClickListener {
            callback.invoke(0)
        }

        btn_add.setOnClickListener {
            vmShare.triggerUpdateTitleBar("เพิ่มรายรับ - รายจ่าย")

            activity?.supportFragmentManager?.beginTransaction()
                    ?.add(R.id.frame_layout, AddAccountFragment::class.java, null)
                    ?.addToBackStack("addAccount")
                    ?.commit()
        }
    }

    private fun observeData() {
        vmShare.updateDataLiveData.observe(viewLifecycleOwner, Observer {
            vm.getAccountList()
        })

        vm.getAccountListLiveData.observe(viewLifecycleOwner, Observer {
            accountAdapter.setListData(it)
        })

        vm.deleteAccountDataLiveData.observe(viewLifecycleOwner, Observer {
            vm.getAccountList()
        })

        vm.errorLiveData.observe(viewLifecycleOwner, Observer {
            AlertMessageDialogFragment.Builder()
                    .setMessage(it)
                    .build()
                    .show(activity?.supportFragmentManager!!, AppConfig.TAG)
        })
    }

    private fun setAdapter() {
        accountAdapter = AccountAdapter { rowId: Int ->
            vm.deleteAccountDate(rowId)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = accountAdapter
        }
    }
}
