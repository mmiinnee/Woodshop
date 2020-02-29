package th.com.woodshop.pincode

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pin_code.*
import kotlinx.android.synthetic.main.toolbar.view.*
import th.com.woodshop.R
import th.com.woodshop.alert.AlertMessageDialogFragment
import th.com.woodshop.main.MainActivity
import th.com.woodshop.utils.AppConfig
import th.com.woodshop.utils.Preference
import kotlin.system.exitProcess

class PinCodeActivity : AppCompatActivity() {

    private var pinCodeFirstFlag: Boolean? = false
    private var pinCodeConfirmFlag: Boolean? = false
    private var pinCodeFirst: String? = ""
    private var pinCodeSecond: String? = ""
    private var countPinCodeWrong: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin_code)

        init()
    }

    private fun init() {
        toolbar.tv_title.text = "ระบบจัดการโรงค้าไม้"
        tv_forgot_pin.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        pinCodeFirst = Preference.instance.getString(this, AppConfig.TAG, "")
        if (pinCodeFirst?.isEmpty()!!) {
            pinCodeFirstFlag = true
            tv_message_pin.text = "กรุณาใส่ PIN เพื่อลงทะเบียน"
            btn_login.text = "ยืนยัน"
        } else {
            tv_forgot_pin.visibility = View.VISIBLE
        }

        et_pincode.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                btn_login.isEnabled = et_pincode.text?.length!! > 0
                if (pinCodeFirstFlag!!) {
                    if (!pinCodeConfirmFlag!!) {
                        pinCodeFirst = s.toString()
                    } else {
                        pinCodeSecond = s.toString()
                    }
                } else {
                    pinCodeSecond = s.toString()
                }
            }
        })

        btn_login.apply {
            setOnClickListener {


                when (this.text) {
                    "ยืนยัน" -> {
                        if (!pinCodeConfirmFlag!!) {
                            pinCodeConfirmFlag = true
                            tv_message_pin.text = "กรุณาใส่ PIN เพื่อยืนยัน"
                            et_pincode.setText("")
                            et_pincode.requestFocus()
                        } else {
                            if (pinCodeFirst == pinCodeSecond) {
                                Preference.instance.putString(this@PinCodeActivity, AppConfig.TAG, pinCodeSecond)
                                startActivity(Intent(this@PinCodeActivity, MainActivity::class.java))
                                finish()
                            } else {
                                dialogPinCodeWrong()
                            }
                        }
                    }
                    "เข้าสู่ระบบ" -> {
                        if (pinCodeFirst == pinCodeSecond) {
                            startActivity(Intent(this@PinCodeActivity, MainActivity::class.java))
                            finish()
                        } else {
                            dialogPinCodeWrong()
                        }
                    }
                }
            }
        }
    }

    private fun dialogPinCodeWrong() {
        if (countPinCodeWrong != 2) {
            countPinCodeWrong = countPinCodeWrong?.plus(1)
            AlertMessageDialogFragment.Builder()
                    .setMessage("PIN ไม่ถูกต้อง")
                    .build()
                    .show(supportFragmentManager, AppConfig.TAG)
        } else {
            AlertMessageDialogFragment.Builder()
                    .setMessage("PIN ไม่ถูกต้อง")
                    .setCallback {
                        exitProcess(0)
                    }
                    .build()
                    .show(supportFragmentManager, AppConfig.TAG)
        }
    }
}
