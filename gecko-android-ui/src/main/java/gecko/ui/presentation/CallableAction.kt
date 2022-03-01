package gecko.ui.presentation

internal fun interface CallableAction<Input> {
    fun onAction(input: Input)
}

internal fun <T> CallableAction<T>.unwrap(): (T) -> Unit = this::onAction