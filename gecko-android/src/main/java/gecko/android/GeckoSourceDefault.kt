package gecko.android

import gecko.android.model.asGeckoData

internal class GeckoSourceDefault(
    private val call: DaoCall
) : GeckoSource {

    override fun getMetadata(id: Long) = call.select(id)
        ?.asGeckoData()
        .run(::requireNotNull)

}