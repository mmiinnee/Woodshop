package th.com.woodshop.utils.phoneformat

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class PhoneDashTextWatcher(private val edt: EditText, private val format: String) : TextWatcher {
    private var strAfter: String? = null
    private var strBefore: String? = null
    private var selectionIndex: Int = 0
    private var maxLength: Int = 0

    init {
        maxLength = format.replace(PhoneFormatUtility.SEPARATE, "").length
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        strBefore = s.toString()
    }

    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

    }

    override fun afterTextChanged(s: Editable) {
        edt.removeTextChangedListener(this)
        val str = s.toString()
        if (str != "") {
            selectionIndex = edt.selectionStart
            strAfter = str
            val strResult: String
            val s1 = strAfter!!.length
            strAfter = strAfter!!.replace(PhoneFormatUtility.SEPARATE.toRegex(), "")
            strAfter = if (strAfter!!.length > maxLength) strBefore else strAfter

            val currentFormat = getCurrentFormat(strAfter!!.toCharArray())

            strResult = PhoneFormatUtility.applyStringPattern(strAfter!!, currentFormat)
            val s2 = strResult.length

            selectionIndex = if (s2 > s1) selectionIndex + (s2 - s1) else selectionIndex - (s1 - s2)
            s.clear()
            s.append(strResult)
            // edt.setText( strResult ); Don't do this!!!
            try {
                edt.setSelection(selectionIndex)
            } catch (e: IndexOutOfBoundsException) {
                edt.setSelection(0)
            }

        }
        edt.addTextChangedListener(this)
    }

    private fun getCurrentFormat(afterChar: CharArray): String {
        val currentFormat = StringBuilder()
        var dashCount = 0
        for (i in afterChar.indices) {
            if (i + dashCount < format.length) {
                if (format.toCharArray()[i + dashCount] == PhoneFormatUtility.SEPARATE[0]) {
                    currentFormat.append(format.toCharArray()[i + dashCount])
                    currentFormat.append("#")
                    dashCount += 1
                } else {
                    currentFormat.append(format.toCharArray()[i + dashCount])
                }
            }

        }
        return currentFormat.toString()
    }
}
