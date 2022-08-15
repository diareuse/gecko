package gecko.ui.screen.dashboard

import androidx.paging.Pager
import androidx.paging.PagingConfig
import gecko.android.paging.GeckoPagingSource
import gecko.ui.presentation.dashboard.DashboardWorker
import kotlinx.coroutines.flow.collectLatest

internal class DashboardContentLoader : DashboardWorker {

    override suspend fun execute(model: DashboardViewModel) {
        model.refreshSignal.collectLatest {
            val config = PagingConfig(pageSize = 10)
            val pager = Pager(config) { GeckoPagingSource() }.flow
            pager.collectLatest {
                model.submitPagingData(it)
            }
        }
    }

}