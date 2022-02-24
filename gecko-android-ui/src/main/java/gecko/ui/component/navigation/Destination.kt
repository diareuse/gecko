package gecko.ui.component.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

data class Destination(
    val route: String
) {

    val arguments
        get() = Regex("\\{(.*)\\}")
            .findAll(route)
            .map { it.groupValues[1] }

    val namedArguments
        get() = arguments.map {
            navArgument(it) {
                nullable = false
                type = NavType.StringType
            }
        }

    fun asDestination(vararg arguments: Pair<String, String>): String {
        val availableArguments = this.arguments.toList()
        var path = route
        for ((key, value) in arguments) {
            check(key in availableArguments) {
                "Given key ($key) is not declared as destination argument"
            }
            path = path.replace("{$key}", value)
        }
        return path
    }

}
