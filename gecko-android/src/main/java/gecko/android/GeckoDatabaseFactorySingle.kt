package gecko.android

internal class GeckoDatabaseFactorySingle(
    private val factory: GeckoDatabaseFactory
) : GeckoDatabaseFactory {

    private var database: GeckoDatabase? = null

    override fun getDatabase() = database ?: synchronized(this) {
        database ?: factory.getDatabase().also { database = it }
    }

}