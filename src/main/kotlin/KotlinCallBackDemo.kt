import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    var instance = DemoInstance()
    instance.register {
        onSuccess { }
        onFail { }
    }
    GlobalScope.launch {
        delay(1000)
    }
    runBlocking {
        // but this expression blocks the main thread
        delay(2000L)  // ... while we delay for 2 seconds to keep JVM alive
        instance
    }
}


class DemoInstance<T> private constructor() {
    var result: T? = null

    private lateinit var callback: ListenerBuilder<T>

    fun register(init: ListenerBuilder<T>.() -> Unit) {
        callback = ListenerBuilder<T>().also(init)
    }

    inner class ListenerBuilder<T> {
        internal var mSuccess: ((T?) -> Unit)? = null
        internal var mFail: (() -> Unit)? = null

        fun onSuccess(action: (T?) -> Unit) {
            mSuccess = action
        }

        fun onFail(action: () -> Unit) {
            mFail = action
        }

    }

    fun success() {
        if (::callback.isInitialized) {
            callback.mSuccess?.invoke(result)
        }
    }

    fun fail() {
        if (::callback.isInitialized) {
            callback.mFail?.invoke()
        }
    }

    companion object {
        operator fun invoke(): DemoInstance<String> {
            return DemoInstance<String>().apply { result = "Hello" }
        }
    }
}

