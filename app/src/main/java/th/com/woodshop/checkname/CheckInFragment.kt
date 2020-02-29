package th.com.woodshop.checkname

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.fragment_check_name.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import th.com.woodshop.R
import th.com.woodshop.alert.AlertMessageDialogFragment
import th.com.woodshop.qrcode.CameraScanQRCode
import th.com.woodshop.utils.AppConfig

class CheckInFragment(private val callback: (Int) -> Unit) : Fragment() {

    private val vm: CheckInFragmentViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_check_name, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        observerData()

        btn_back.setOnClickListener {
            callback.invoke(0)
        }

        img_scan_qr_code.setOnClickListener {
            Dexter.withActivity(activity)
                    .withPermission(Manifest.permission.CAMERA)
                    .withListener(object : PermissionListener {
                        override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                            startActivityForResult(Intent(activity, CameraScanQRCode::class.java), 200)
                        }

                        override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {
                            token?.continuePermissionRequest()
                        }

                        override fun onPermissionDenied(response: PermissionDeniedResponse?) {}
                    }).check()
        }
    }

    private fun observerData() {
        vm.checkInLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                AlertMessageDialogFragment.Builder()
                        .setMessage("เช็คชื่อเรียบร้อย")
                        .build()
                        .show(activity?.supportFragmentManager!!, AppConfig.TAG)
            } else {
                AlertMessageDialogFragment.Builder()
                        .setMessage("เช็คชื่อไม่สำเร็จกรุณาลองใหม่")
                        .build()
                        .show(activity?.supportFragmentManager!!, AppConfig.TAG)
            }
        })

        vm.checkUserEmployeeLiveData.observe(viewLifecycleOwner, Observer {
            if (!it) {
                AlertMessageDialogFragment.Builder()
                        .setMessage("ไม่มีรหัสพนักงาน")
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 200) {
            if (resultCode == RESULT_OK) {
                val result = data?.getStringExtra("result")
                result?.let {
                    vm.checkUserEmployee(it)
                }
            }
        }
    }
}
