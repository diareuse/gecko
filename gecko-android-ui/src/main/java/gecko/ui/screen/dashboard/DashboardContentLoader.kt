package gecko.ui.screen.dashboard

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import gecko.android.paging.GeckoPagingSource
import gecko.ui.presentation.dashboard.DashboardWorker
import kotlinx.coroutines.flow.collectLatest

class DashboardContentLoader(
    private val context: Context
) : DashboardWorker {

    override fun present(model: DashboardViewModel): suspend () -> Unit = {
        val config = PagingConfig(pageSize = 10)
        val pager = Pager(config) { GeckoPagingSource(context.applicationContext) }.flow
        pager.collectLatest {
            model.submitPagingData(it)
        }
    }

}