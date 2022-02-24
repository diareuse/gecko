package gecko.ui.screen.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import gecko.android.model.GeckoMetadata
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DashboardViewModel : ViewModel() {

    private val _pagingData = MutableStateFlow<PagingData<GeckoMetadata>>(PagingData.empty())
    private val _appName = MutableStateFlow("")

    val pagingData = _pagingData.cachedIn(viewModelScope)
    val appName = _appName.asStateFlow()

    fun submitPagingData(data: PagingData<GeckoMetadata>) {
        _pagingData.update { data }
    }

    fun submitAppName(name: String) {
        _appName.update { name }
    }

}