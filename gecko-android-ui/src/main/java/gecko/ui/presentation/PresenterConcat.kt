package gecko.ui.presentation

class PresenterConcat<Model, Output>(
    private vararg val presenters: Presenter<Model, Output>
) : Presenter<Model, List<Output>> {

    override fun present(model: Model): List<Output> {
        return presenters.map { it.present(model = model) }
    }

}