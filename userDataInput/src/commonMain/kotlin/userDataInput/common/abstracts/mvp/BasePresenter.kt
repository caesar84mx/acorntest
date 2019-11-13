package userDataInput.common.abstracts.mvp

interface BasePresenter<V: BaseView> {
    val isAttached: Boolean

    fun attach(view: V)
    fun detach()
}