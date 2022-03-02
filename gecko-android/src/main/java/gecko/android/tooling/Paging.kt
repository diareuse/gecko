package gecko.android.tooling

import androidx.paging.PagingSource

internal fun PagingSource.LoadParams<Int>.prevKey() =
    key?.minus(loadSize)?.coerceAtLeast(0)?.takeUnless { it == 0 }

internal fun PagingSource.LoadParams<Int>.nextKey(list: List<*>) =
    ((key ?: 0) + loadSize).takeUnless { list.isEmpty() }