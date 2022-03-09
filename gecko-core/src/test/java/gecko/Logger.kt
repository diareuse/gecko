package gecko

fun logger(body: (String) -> Unit) = Logger { body(it) }