package gecko.ui.presentation.detail

import gecko.ui.presentation.SuspendingPresenter
import gecko.ui.presentation.ViewPresenter
import gecko.ui.screen.detail.DetailViewModel

typealias DetailPresenter = ViewPresenter<DetailViewModel>
typealias DetailWorker = SuspendingPresenter<DetailViewModel>