package gecko.ui.composition

import android.content.Context
import android.net.Uri
import androidx.navigation.NavBackStackEntry
import gecko.android.GeckoSource
import gecko.android.GeckoSourceFactory
import gecko.ui.presentation.SimplePresenter
import gecko.ui.presentation.ViewPresenter
import gecko.ui.presentation.ViewPresenterConcat
import gecko.ui.presentation.ViewPresenterViewModelEffect
import gecko.ui.presentation.action.CopyUriPresenter
import gecko.ui.presentation.action.OpenUriPresenter
import gecko.ui.screen.detail.DetailContent
import gecko.ui.screen.detail.DetailContentLoader
import gecko.ui.screen.detail.DetailScreen

fun DetailView(context: Context): ViewPresenter<NavBackStackEntry> {
    val features = ViewPresenterConcat(
        DetailContent(getLink(context), getCopy(context)),
        ViewPresenterViewModelEffect(DetailContentLoader(getSource(context)))
    )
    return DetailScreen(features)
}

private fun getSource(context: Context): GeckoSource {
    return GeckoSourceFactory.getInstance(context).getInstance()
}

private fun getLink(context: Context): SimplePresenter<Uri> {
    return OpenUriPresenter(context)
}

private fun getCopy(context: Context): SimplePresenter<Uri> {
    return CopyUriPresenter(context)
}
