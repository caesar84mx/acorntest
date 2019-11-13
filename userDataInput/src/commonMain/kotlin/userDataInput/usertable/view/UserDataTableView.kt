package userDataInput.usertable.view

import com.acornui.collection.ActiveList
import com.acornui.collection.addAll
import com.acornui.component.UiComponent
import com.acornui.component.datagrid.DataGrid
import com.acornui.component.datagrid.StringColumn
import com.acornui.component.datagrid.dataGrid
import com.acornui.component.layout.HAlign
import com.acornui.component.layout.algorithm.FlowLayoutStyle
import com.acornui.component.layout.algorithm.VerticalLayoutContainer
import com.acornui.component.scroll.ScrollPolicy
import com.acornui.component.style.addStyleRule
import com.acornui.component.style.and
import com.acornui.component.text.TextField
import com.acornui.component.text.text
import com.acornui.di.Owned
import com.acornui.math.Pad
import com.acornui.popup.alert
import userDataInput.usertable.UserDataTableContract
import userDataInput.usertable.model.dto.UserTableData
import userDataInput.usertable.presenter.UserDataTablePresenter

class UserDataTableView(owner: Owned) : VerticalLayoutContainer(owner), UserDataTableContract.View {
    private val presenter = UserDataTablePresenter.create()

    lateinit var table: DataGrid<UserTableData>

    init {
        presenter.attach(this)
        buildView(presenter.data)
    }

    private fun buildView(data: ActiveList<UserTableData>) {
        style.padding = Pad(5f, 5f, 5f, 5f)
        table = +dataGrid(data) {
            val headerFlowStyle = FlowLayoutStyle()
            headerFlowStyle.multiline = true
            addStyleRule(headerFlowStyle, TextField and DataGrid.HEADER_CELL)
            hScrollPolicy = ScrollPolicy.AUTO

            columns.addAll(
                    object : StringColumn<UserTableData>() {
                        init {
                            flexible = true
                            sortable = true
                            width = 250f
                            minWidth = 150f
                            editable = true
                        }

                        override fun createHeaderCell(owner: Owned): UiComponent = owner.text("First Name")

                        override fun getCellData(row: UserTableData): String = row.firstName

                        override fun setCellData(row: UserTableData, value: String) {
                            row.firstName = value
                            presenter.update(row)
                        }
                    },
                    object : StringColumn<UserTableData>() {
                        init {
                            flexible = true
                            sortable = true
                            width = 250f
                            minWidth = 150f
                            editable = true
                        }

                        override fun createHeaderCell(owner: Owned): UiComponent = owner.text("Last Name")

                        override fun getCellData(row: UserTableData): String = row.lastName

                        override fun setCellData(row: UserTableData, value: String) {
                            row.lastName = value
                            presenter.update(row)
                        }
                    },
                    object : StringColumn<UserTableData>() {
                        init {
                            flexible = true
                            sortable = true
                            width = 200f
                            minWidth = 100f
                            editable = false
                        }

                        override fun createHeaderCell(owner: Owned): UiComponent = owner.text("Nickame")

                        override fun getCellData(row: UserTableData): String = row.nickName

                        override fun setCellData(row: UserTableData, value: String) {}
                    },
                    object : StringColumn<UserTableData>() {
                        init {
                            flexible = true
                            sortable = true
                            width = 80f
                            minWidth = 50f
                            editable = false
                        }

                        override fun createHeaderCell(owner: Owned): UiComponent = owner.text("Sex")

                        override fun getCellData(row: UserTableData): String = row.sex

                        override fun setCellData(row: UserTableData, value: String) {}
                    }
            )
        } layout { fill(); horizontalAlign = HAlign.CENTER }
    }

    override fun showUpdateUnsuccessful() {
        alert("Error", "Failed to update user!")
    }

    override fun dispose() {
        super.dispose()
        presenter.detach()
    }
}