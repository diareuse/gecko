package gecko.ui.component.navigation

object Destinations {

    val Dashboard = Destination("/dashboard")
    fun Dashboard() = Dashboard.asDestination()

    val CallDetail = Destination("/calls/{id}")
    fun CallDetail(id: String) = CallDetail.asDestination("id" to id)

}