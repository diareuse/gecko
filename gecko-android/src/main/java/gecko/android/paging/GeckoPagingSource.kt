package gecko.android.paging

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import gecko.android.GeckoDatabase
import gecko.android.GeckoDatabaseFactory
import gecko.android.model.GeckoMetadata
import gecko.android.model.asGeckoMetadata
import gecko.android.selectSuspendCatching
import gecko.android.tooling.nextKey
import gecko.android.tooling.prevKey

class GeckoPagingSource internal constructor(
    database: GeckoDatabase
) : PagingSource<Int, GeckoMetadata>() {

    internal constructor(
        factory: GeckoDatabaseFactory
    ) : this(
        database = factory.getDatabase()
    )

    constructor(
        context: Context
    ) : this(
        factory = GeckoDatabaseFactory.getInstance(context)
    )

    private val call by lazy { database.call() }

    override fun getRefreshKey(state: PagingState<Int, GeckoMetadata>): Int? {
        val anchor = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchor) ?: return null
        return page.prevKey?.plus(state.config.pageSize)
            ?: page.nextKey?.minus(state.config.pageSize)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GeckoMetadata> {
        return call
            .selectSuspendCatching(params.key ?: 0, params.loadSize)
            .map { list -> list.map { it.asGeckoMetadata() } }
            .fold(
                onSuccess = { LoadResult.Page(it, params.prevKey(), params.nextKey(it)) },
                onFailure = { LoadResult.Error(it) }
            )
    }

}