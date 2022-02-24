package gecko.android

internal class GeckoSourceFactoryDefault(
    private val database: GeckoDatabaseFactory
) : GeckoSourceFactory {

    override fun getInstance(): GeckoSource = GeckoSourceDefault(
        call = database.getDatabase().call(),
        adapter = database.getMetadata()
    )

}