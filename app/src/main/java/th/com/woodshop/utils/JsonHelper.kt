package th.com.woodshop.utils

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONObject

object JsonHelper {
    private val gson = Gson()
    private val parser: JsonParser by lazy { JsonParser() }


    fun toJsonString(src: Any?): String {
        return gson.toJson(src)
    }

    fun <T : Any?> toClass(src: JSONObject, className: Class<T>): T {
        return gson.fromJson(src.toString(), className)
    }

    fun toJsonObject(src: String): JsonObject {
        return parser.parse(src) as JsonObject
    }

    fun <T> fromJson(jsonObject: JsonObject, classOfT: Class<T>): T? {
        return gson.fromJson(jsonObject, classOfT)
    }

    fun <T> fromJson(jsonString: String, classOfT: Class<T>): T? {
        return gson.fromJson(jsonString, classOfT)
    }

    fun toJson(src: Any?): String {
        return gson.toJson(src)
    }

    fun <T : Any?> JSONObject.toModel(toClass: Class<T>): T {
        return toClass(this, toClass)
    }
}
