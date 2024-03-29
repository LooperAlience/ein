package ein.android.app

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object eShared{
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    class Item(private val s:SharedPreferences){
        private val edit = s.edit()
        fun i(k:String):Int = s.getInt(k, 0)
        fun l(k:String):Long = s.getLong(k, 0L)
        fun f(k:String):Float = s.getFloat(k, 0F)
        fun s(k:String):String = s.getString(k, "")
        fun b(k:String):Boolean = s.getBoolean(k, false)
        fun i(k:String, v:Int) {
            edit.putInt(k, v)
            edit.commit()
        }
        fun l(k:String, v:Long){
            edit.putLong(k, v)
            edit.commit()
        }
        fun f(k:String, v:Float){
            edit.putFloat(k, v)
            edit.commit()
        }
        fun s(k:String, v:String){
            edit.putString(k, v)
            edit.commit()
        }
        fun b(k:String, v:Boolean){
            edit.putBoolean(k, v)
            edit.commit()
        }
    }
    fun name(k:String):Item = Item(eApp.app.getSharedPreferences(k, MODE_PRIVATE))
}