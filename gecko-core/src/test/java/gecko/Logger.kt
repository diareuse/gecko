package gecko

internal fun logger(body: (String) -> Unit) = Logger { body(it) }