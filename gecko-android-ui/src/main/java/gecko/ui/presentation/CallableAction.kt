package gecko.ui.presentation

fun interface CallableAction<Input> {
    fun onAction(input: Input)
}

fun <T> CallableAction<T>.unwrap(): (T) -> Unit = this::onAction