package gecko.android

internal class GeckoSourceFactorySingle(
    private val factory: GeckoSourceFactory
) : GeckoSourceFactory {

    private var source: GeckoSource? = null

    override fun getInstance(): GeckoSource {
        return source ?: synchronized(this) {
            source ?: factory.getInstance().also {
                source = it
            }
        }
    }

}