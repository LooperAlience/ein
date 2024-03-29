package ein.core.looper.ani

import ein.core.log.log
import ein.core.looper.eTask
import ein.core.looper.now
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

private const val PI = kotlin.math.PI
private const val HPI = PI / 2

class eAniTask:eTask() {
    var time = 0.0
    var turn = 0
    var loop = 1
    var isInfinity = false
    var rate = 0.0

    internal var current = 0.0
    internal var end = 0.0
    internal var isPaused = false
    internal var pauseStart = 0.0

    override fun startTask() {
        end = start + time
    }
    fun pause(){
        if(isPaused) return
        isPaused = true
        pauseStart = now()
    }
    fun resume(){
        if(!isPaused) return
        isPaused = false
        val gap = now() - pauseStart
        start += gap
        end += gap
    }

    fun linear(from: Double, to: Double): Double {
        return from + rate * (to - from)
    }
    fun backIn(from: Double, to: Double): Double {
        val b = to - from
        return b * rate * rate * (2.70158 * rate - 1.70158) + from
    }
    fun backOut(from: Double, to: Double): Double {
        val a = rate - 1
        val b = to - from
        return b * (a * a * (2.70158 * a + 1.70158) + 1) + from
    }
    fun backInOut(from: Double, to: Double): Double {
        var a = rate * 2
        val b = to - from
        if (1 > a)
            return .5 * b * a * a * (3.5949095 * a - 2.5949095) + from
        else {
            a -= 2.0
            return .5 * b * (a * a * (3.70158 * a + 2.70158) + 2) + from
        }
    }
    fun sineIn(from: Double, to: Double): Double {
        val b = to - from
        return -b * cos(rate * HPI) + b + from
    }
    fun sineOut(from: Double, to: Double): Double {
        return (to - from) * sin(rate * HPI) + from
    }
    fun sineInOut(from: Double, to: Double): Double {
        return .5 * -(to - from) * (cos(PI * rate) - 1) + from
    }
    fun circleIn(from: Double, to: Double): Double {
        return -(to - from) * (sqrt(1 - rate * rate) - 1) + from
    }
    fun circleOut(from: Double, to: Double): Double {
        val a = rate - 1
        return (to - from) * sqrt(1 - a * a) + from
    }
    fun circleInOut(from: Double, to: Double): Double {
        var a = rate * 2
        val b = to - from
        if (1 > a)
            return .5 * -b * (sqrt(1 - a * a) - 1) + from
        else {
            a -= 2.0
            return .5 * b * (sqrt(1 - a * a) + 1) + from
        }
    }
    fun quadraticIn(from: Double, to: Double): Double {
        return (to - from) * rate * rate + from
    }
    fun quadraticOut(from: Double, to: Double): Double {
        return -(to - from) * rate * (rate - 2) + from
    }
    fun bounceOut(from: Double, to: Double): Double {
        var a = rate
        val b = to - from
        if (0.363636 > a)
            return 7.5625 * b * a * a + from
        else if (0.727272 > a) {
            a -= 0.545454
            return b * (7.5625 * a * a + 0.75) + from
        } else if (0.90909 > a) {
            a -= 0.818181
            return b * (7.5625 * a * a + 0.9375) + from
        } else {
            a -= 0.95454
            return b * (7.5625 * a * a + 0.984375) + from
        }
    }
}