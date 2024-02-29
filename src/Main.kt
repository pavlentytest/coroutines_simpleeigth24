import kotlinx.coroutines.*

suspend fun main2() {
    for(i in 0..5) {
        delay(500)
        println(i)
    }
    println("Test message!")
}

suspend fun main3() {
    coroutineScope {
        launch {
            heavyWork()
        }
        println("Test message!")
    }
}
suspend fun heavyWork() {
    for(i in 0..5) {
        delay(500)
        println(i)
    }
}
suspend fun main4() {
    coroutineScope {
        val job : Job = launch {
            heavyWork()
        }
        println("Start!")
        job.join() // ждем завершения работы корутины
        println("Finished!")
    }
}
suspend fun main5() {
    coroutineScope {
        // корутина создана, но не запущена
        val job : Job = launch(start = CoroutineStart.LAZY) {
            heavyWork()
        }
        delay(1000)
        job.start() // запуск корутины
        delay(3000)
        job.cancel() // отмена корутины
        job.join() // ждем завершение
        job.cancelAndJoin() //
    }
}

suspend fun main() {
    coroutineScope {
        // создаем корутину
        val result : Deferred<String> = async(start = CoroutineStart.LAZY) {
            getResult()
        }
        delay(1000)
        result.start() // Запускаю
        println("Result: ${result.await()}") // ждем результатов
        println("Finished!")
    }
}
suspend fun getResult() : String {
    delay(1000)
    return "Result string!"
}