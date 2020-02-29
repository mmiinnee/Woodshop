package th.com.woodshop.utils.priceformat

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class PriceDashTextWatcher(private val edt: EditText, private val format: String) : TextWatcher {
    private var strAfter: String? = null
    private var strBefore: String? = null
    private var selectionIndex: Int = 0
    private var maxLength: Int = 0

    init {
        maxLength = format.replace(PriceFormatUtility.SEPARATE, "").length
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        strBefore = s.toString()
    }

    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

    override fun afterTextChanged(s: Editable) {
        edt.removeTextChangedListener(this)
        val str = s.toString()
        if (str != "") {
            selectionIndex = edt.selectionStart
            strAfter = str
            val strResult: String
            val s1 = strAfter!!.length
            strAfter = strAfter!!.replace(PriceFormatUtility.SEPARATE.toRegex(), "")
            strAfter = if (strAfter!!.length > maxLength) strBefore else strAfter

            val currentFormat = getCurrentFormat(strAfter!!.length)

            strResult = PriceFormatUtility.applyStringPattern(strAfter!!, currentFormat)

            val s2 = strResult.length

            selectionIndex = if (s2 > s1) selectionIndex + (s2 - s1) else selectionIndex - (s1 - s2)
            s.clear()
            s.append(strResult)
            try {
                edt.setSelection(selectionIndex)
            } catch (e: IndexOutOfBoundsException) {
                edt.setSelection(0)
            }

        }
        edt.addTextChangedListener(this)
    }

    private fun getCurrentFormat(afterLength: Int): String {
        val currentFormat = StringBuilder()
        when (afterLength) {
            1 -> currentFormat.append("#")
            2 -> currentFormat.append("##")
            3 -> currentFormat.append("###")
            4 -> currentFormat.append("#,###")
            5 -> currentFormat.append("##,###")
            6 -> currentFormat.append("###,###")
            7 -> currentFormat.append("#,###,###")
            8 -> currentFormat.append("##,###,###")
            9 -> currentFormat.append("###,###,###")
            10 -> currentFormat.append("#,###,###,###")
            11 -> currentFormat.append("##,###,###,###")
            12 -> currentFormat.append("###,###,###,###")
            13 -> currentFormat.append("#,###,###,###,###")
        }
        return currentFormat.toString()
    }
}
