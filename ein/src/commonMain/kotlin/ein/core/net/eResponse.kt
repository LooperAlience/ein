package ein.core.net

import ein.core.value.eValue

abstract class eResponse(val error:String?){
    val extra = mutableMapOf<String, Any>()
    var result:Any = ""
    val isOk get() = error == null
    abstract val state:Int
    abstract fun header(k:String):String
    abstract fun text(block:(String)->Unit)
    abstract fun json(block:(eValue?)->Unit)
    abstract fun bytes(block:(ByteArray?)->Unit)
}