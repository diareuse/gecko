package gecko.util

import java.util.*

inline fun <reified Service> loadService(): Service {
    val service = ServiceLoader.load(Service::class.java).firstOrNull()
    requireNotNull(service) {
        "Cannot find platform ${Service::class.java.simpleName}. Did you include platform library?"
    }
    return service
}

inline fun <reified Service> loadServices(): Iterable<Service> {
    return ServiceLoader.load(Service::class.java)
}