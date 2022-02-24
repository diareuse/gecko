package gecko.ui.presentation.dashboard

import gecko.ui.presentation.SuspendingPresenter
import gecko.ui.presentation.ViewPresenter
import gecko.ui.screen.dashboard.DashboardViewModel

typealias DashboardPresenter = ViewPresenter<DashboardViewModel>
typealias DashboardWorker = SuspendingPresenter<DashboardViewModel>