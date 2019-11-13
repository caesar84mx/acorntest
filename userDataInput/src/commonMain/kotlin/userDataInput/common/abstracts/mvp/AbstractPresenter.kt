package userDataInput.common.abstracts.mvp

abstract class AbstractPresenter<V: BaseView>: BasePresenter<V> {
    var view: V? = null

    override val isAttached: Boolean = view != null

    override fun attach(view: V) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }
}