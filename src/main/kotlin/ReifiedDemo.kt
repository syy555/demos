import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

fun main(args: Array<String>) {
    val a = C<Int>()
    val b = C<String>()

    println(C<Int>().typeToken)
    println(C<List<String>>().typeToken)
    println(C<String>().type)
    println(C<String>().type)
}


open class C<T>(val typeToken: Class<T> ) {
    val type: Type
        get() = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
}

inline fun <reified T> C(): C<T> = object :C<T>(T::class.java){

}
