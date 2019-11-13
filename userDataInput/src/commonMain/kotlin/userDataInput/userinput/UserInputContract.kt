package userDataInput.userinput

import userDataInput.common.abstracts.mvp.BasePresenter
import userDataInput.common.abstracts.mvp.BaseView
import userDataInput.common.model.extra.Sex

interface UserInputContract {
    interface Presenter: BasePresenter<View> {
        fun onClearPressed()
        fun onSavePressed()
    }

    interface View: BaseView {
        fun getFirstName(): String
        fun getLastName(): String
        fun getNickName(): String
        fun getEmail(): String
        fun getPassword(): String
        fun getSex(): Sex
        fun showAlert(title: String, text: String)
        fun clearAll()
    }
}