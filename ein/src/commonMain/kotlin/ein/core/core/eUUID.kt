package ein.core.core

import kotlin.random.Random

private val uuid = "3b99e3e0-7598-11e8-90be-95472fb3ecbd".split('-').map{v->v.length}
fun uuid() = uuid.joinToString(separator = "-"){Random.nextLong(0x100000000000L, 0xffffffffffffL).toString(16).substring(0, it)}
