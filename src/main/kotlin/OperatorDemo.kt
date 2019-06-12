
fun main(args: Array<String>) {
    //这里会调用伴生对象的方法，通过重载invoke运算符模拟构造方法
   Test(1)
}


class Test private constructor(val index: Int) {
    companion object {
        operator fun invoke(index: Int):Test{
            return Test(index)
        }
    }
}