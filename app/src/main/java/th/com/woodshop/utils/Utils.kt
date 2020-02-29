package th.com.woodshop.utils

import java.util.*

object Utils {
    fun generateRandom(length: Int): String {
        val sb = StringBuilder()
        val candidateChars = "1234567890"
        val random = Random()
        for (i in 0 until length) {
            sb.append(candidateChars[random.nextInt(candidateChars
                    .length)])
        }

        return sb.toString()
    }
}