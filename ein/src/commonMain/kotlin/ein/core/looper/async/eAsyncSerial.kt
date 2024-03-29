package ein.core.looper.async

import ein.core.looper.EMPTYBLOCK
import ein.core.looper.eSerial
import ein.core.looper.now

class eAsyncSerial(private val async:eAsync):eSerial<eAwait>(){
    var error = EMPTYBLOCK
    var timeout = 0.0

    private var isNext = false
    private var started = 0.0
    private val checkNext = {it:eAwait->Unit
        if(isNext) it.stop()
        if(timeout > 0.0 && now() - started > timeout) it.error(error)
    }
    override fun addScheduler(task:eAwait){async += task}
    fun wait(block:eAsyncSerial.()->Unit){
        last(
            async.task{
                it.stop()
                isNext = false
                started = now()
                this.block()
            },
            async.task(block = checkNext)
        )
    }
    fun go(){isNext = true}
    fun run(block:eAsyncSerial.()->Unit){
        last(async.task{
            it.stop()
            this.block()
        })
    }
    fun loop(term:Double, block:(()->Unit)->Unit){
        var run = false
        var isStop = false
        val f:()->Unit = {isStop = true}
        var prev = 0.0
        last(async.task {
            if(!run){
                run = true
                block(f)
                if(isStop) it.stop()
                prev = now()
            }else if(now() - prev > term){
                run = false
            }
        })
    }
}