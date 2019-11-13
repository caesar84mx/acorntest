package userDataInput.js

import com.acornui.async.runMain
import com.acornui.component.stage
import com.acornui.webgl.WebGlApplication
import userDataInput.common.app.config
import userDataInput.main.view.MainView

fun main() {
    runMain {
        WebGlApplication("userDataInputDemoRoot").start(config) {
            stage.addElement(MainView(this))
        }
    }
}