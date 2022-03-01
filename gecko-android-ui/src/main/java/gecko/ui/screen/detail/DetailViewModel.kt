package gecko.ui.screen.detail

import androidx.lifecycle.ViewModel
import gecko.android.model.GeckoData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal class DetailViewModel(val id: String) : ViewModel() {

    private val _metadata = MutableStateFlow<GeckoData?>(null)
    val metadata = _metadata

    fun submitMetadata(metadata: GeckoData) {
        this._metadata.update { metadata }
    }

}