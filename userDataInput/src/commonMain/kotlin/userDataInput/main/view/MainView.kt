package userDataInput.main.view

import com.acornui.component.hr
import com.acornui.component.layout.algorithm.VerticalLayoutContainer
import com.acornui.di.Owned
import com.acornui.skins.BasicUiSkin
import com.acornui.skins.Theme
import com.acornui.tween.Tween
import userDataInput.userinput.view.UserDataInputView
import userDataInput.usertable.view.UserDataTableView

class MainView(owner: Owned): VerticalLayoutContainer(owner) {
    init {
        Tween.prepare()
        BasicUiSkin(stage, Theme()).apply()

        +UserDataInputView(owner).layout { widthPercent = 1f; heightPercent = 0.5f }
        +hr().layout { widthPercent = 1f }
        +UserDataTableView(owner).layout { widthPercent = 1f; heightPercent = 0.5f }
    }
}