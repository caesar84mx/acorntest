package userDataInput.jvm

import com.acornui.async.runMain
import com.acornui.component.stage
import com.acornui.lwjgl.LwjglApplication
import userDataInput.common.app.config
import userDataInput.main.view.MainView

fun main() = runMain {
    LwjglApplication().start(config) {
        stage.addElement(MainView(this))
    }
}