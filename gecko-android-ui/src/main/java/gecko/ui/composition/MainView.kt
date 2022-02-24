package gecko.ui.composition

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.navigation.NavGraphBuilder
import gecko.ui.component.navigation.Destinations
import gecko.ui.presentation.SimplePresenter
import gecko.ui.presentation.SimplePresenterConcat
import gecko.ui.presentation.ViewPresenter
import gecko.ui.presentation.navigation.NavigationPresenter
import gecko.ui.presentation.navigation.NavigationRoute
import gecko.ui.presentation.theme.InsetsPresenter
import gecko.ui.presentation.theme.SystemUiPresenter
import gecko.ui.presentation.theme.ThemePresenter
import gecko.ui.screen.main.MainContent

fun MainView(context: Context): SimplePresenter<ComponentActivity> {
    val routes: SimplePresenter<NavGraphBuilder> = SimplePresenterConcat(
        NavigationRoute(Destinations.Dashboard, DashboardView(context)),
        NavigationRoute(Destinations.CallDetail, DetailView(context))
    )
    var view: ViewPresenter<Unit>
    view = NavigationPresenter(routes)
    view = InsetsPresenter(view)
    view = ThemePresenter(view)
    view = SystemUiPresenter(view)
    return MainContent(view)
}