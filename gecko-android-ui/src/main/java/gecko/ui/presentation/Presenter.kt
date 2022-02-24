package gecko.ui.presentation

interface Presenter<Model, Output> {

    fun present(model: Model): Output

}
