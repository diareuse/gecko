package gecko.builder

import gecko.Gecko

fun interface GeckoWrapper {
    fun wrap(gecko: Gecko): Gecko
}