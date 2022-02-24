package gecko.android

import gecko.android.adapter.MetadataAdapter

class GeckoSourceDefault internal constructor(
    private val call: DaoCall,
    private val adapter: MetadataAdapter
) : GeckoSource {

    override fun getMetadata(id: Long) = call.select(id)
        ?.run(adapter::adapt)
        .run(::requireNotNull)

}