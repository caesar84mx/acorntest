package userDataInput.usertable

import com.acornui.collection.ActiveList
import userDataInput.common.abstracts.mvp.BasePresenter
import userDataInput.common.abstracts.mvp.BaseView
import userDataInput.usertable.model.dto.UserTableData

interface UserDataTableContract {
    interface Presenter: BasePresenter<View> {
        val data: ActiveList<UserTableData>
        fun update(userTableData: UserTableData)
    }

    interface View: BaseView {
        fun showUpdateUnsuccessful()
    }
}