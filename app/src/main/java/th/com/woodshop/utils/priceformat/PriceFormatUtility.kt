package th.com.woodshop.utils.priceformat

object PriceFormatUtility {
    const val SEPARATE = ","
    const val DEFAULT_TEXT_FORMAT = "#,###,###,###,###"

    fun applyStringPattern(text: String, format: String): String {
        val pattern = StringBuilder()
        val replacement = StringBuilder()
        val formats = format.split(SEPARATE.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (i in formats.indices) {
            pattern.append("(\\d{").append(formats[i].length).append("})")
            if (i == 0) {
                replacement.append("$").append(i + 1)
            } else {
                replacement.append("$SEPARATE$").append(i + 1)
            }
        }
        return text.replaceFirst(pattern.toString().toRegex(), replacement.toString())
    }
}
