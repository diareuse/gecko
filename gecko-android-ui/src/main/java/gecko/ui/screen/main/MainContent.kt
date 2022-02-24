package gecko.ui.screen.main

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import gecko.ui.presentation.SimplePresenter
import gecko.ui.presentation.ViewPresenter

class MainContent(
    private val content: ViewPresenter<Unit>
) : SimplePresenter<ComponentActivity> {

    override fun present(model: ComponentActivity) = with(model) {
        setContent {
            content.present(Unit).invoke()
        }
    }

}