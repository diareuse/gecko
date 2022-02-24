package gecko.ui.presentation

class SimplePresenterConcat<Model>(
    private vararg val presenters: SimplePresenter<Model>
) : SimplePresenter<Model> {

    override fun present(model: Model) {
        for (presenter in presenters)
            presenter.present(model)
    }

}