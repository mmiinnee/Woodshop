package th.com.woodshop.utils.priceformat

import android.content.Context
import android.text.method.DigitsKeyListener
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class PriceFormatEditText : AppCompatEditText {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setDashFormat(format: String?) {
        if (format != null) {
            addTextChangedListener(PriceDashTextWatcher(this, format))
            val keyListener = DigitsKeyListener.getInstance("0123456789" + PriceFormatUtility.SEPARATE)
            setKeyListener(keyListener)
        }
    }
}
