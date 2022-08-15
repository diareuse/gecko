package gecko.android

interface GeckoSourceFactory {

    fun getInstance(): GeckoSource

    companion object {

        private var instance: GeckoSourceFactory? = null

        @Suppress("ComplexRedundantLet")
        fun getInstance() = instance ?: synchronized(GeckoSourceFactory) {
            instance ?: GeckoSourceFactoryDefault(GeckoDatabaseFactory.getInstance())
                .let { GeckoSourceFactorySingle(it) }
                .also { instance = it }
        }

    }

}