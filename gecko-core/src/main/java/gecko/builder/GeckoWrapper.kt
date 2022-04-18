package gecko.builder

import gecko.Gecko

/**
 * Simple wrapper function to allow bypassing or adding features to Gecko.
 * */
fun interface GeckoWrapper {

    /**
     * Wrapping is a process of extending or bypassing a feature.
     *
     * To extend Gecko, you should pass provided gecko as your constructor parameter and call it
     * in all of its interface methods (or use delegation).
     *
     * To bypass Gecko, you should either not pass or selectively not call its interface methods.
     * */
    fun wrap(gecko: Gecko): Gecko

}