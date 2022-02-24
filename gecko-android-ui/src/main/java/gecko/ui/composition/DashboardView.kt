package gecko.ui.composition

import android.content.Context
import androidx.navigation.NavBackStackEntry
import gecko.ui.presentation.ViewPresenter
import gecko.ui.presentation.ViewPresenterConcat
import gecko.ui.presentation.ViewPresenterViewModelEffect
import gecko.ui.screen.dashboard.DashboardContent
import gecko.ui.screen.dashboard.DashboardContentLoader
import gecko.ui.screen.dashboard.DashboardNameLoader
import gecko.ui.screen.dashboard.DashboardScreen

fun DashboardView(context: Context): ViewPresenter<NavBackStackEntry> {
    val features = ViewPresenterConcat(
        DashboardContent(),
        ViewPresenterViewModelEffect(DashboardContentLoader(context)),
        ViewPresenterViewModelEffect(DashboardNameLoader(context)),
    )
    return DashboardScreen(features)
}