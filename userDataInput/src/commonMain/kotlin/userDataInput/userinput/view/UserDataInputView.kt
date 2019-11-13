package userDataInput.userinput.view

import com.acornui.component.*
import com.acornui.component.layout.HAlign
import com.acornui.component.layout.algorithm.form
import com.acornui.component.layout.algorithm.formLabel
import com.acornui.component.layout.algorithm.hGroup
import com.acornui.component.layout.algorithm.vGroup
import com.acornui.component.text.TextInput
import com.acornui.component.text.textInput
import com.acornui.di.Owned
import com.acornui.input.interaction.click
import com.acornui.math.Pad
import com.acornui.popup.alert
import com.acornui.skins.BasicUiSkin
import com.acornui.skins.Theme
import com.acornui.tween.Tween
import userDataInput.userinput.UserInputContract
import userDataInput.common.model.extra.Sex
import userDataInput.userinput.presenter.UserDataInputPresenter

class UserDataInputView(owner: Owned) : StackLayoutContainer(owner), UserInputContract.View {
    private lateinit var tiFirstName: TextInput
    private lateinit var tiLastName: TextInput
    private lateinit var tiNickName: TextInput
    private lateinit var tiEmail: TextInput
    private lateinit var tiPassword: TextInput
    private lateinit var rgSex: RadioGroup<Sex>
    private lateinit var btnSave: Button
    private lateinit var btnClear: Button

    private val presenter: UserDataInputPresenter = UserDataInputPresenter.create()

    init {
        presenter.attach(this)
        buildView()
        setHandlers()
    }

    private fun buildView() {
        +vGroup {
            style.padding = Pad(5f, 5f, 5f, 5f)

            +headingGroup {
                label = "New User"

                +form {
                    style.verticalGap = 10f
                    style.padding = Pad(3f, 3f, 3f, 3f)

                    +formLabel("First Name")
                    tiFirstName = +textInput()

                    +formLabel("Last Name")
                    tiLastName = +textInput()

                    +formLabel("Sex")
                    +hGroup {
                        rgSex = radioGroup {
                            +radioButton(Sex.MALE) { label = "Male" }
                            +radioButton(Sex.FEMALE) { label = "Female" }
                            selectedData = Sex.MALE
                        }
                    }

                    +formLabel("Nickname")
                    tiNickName = +textInput()

                    +formLabel("Email")
                    tiEmail = +textInput()

                    +formLabel("Password")
                    tiPassword = +textInput()

                    btnSave = +button("Save")
                    btnClear = +button("Clear")
                } layout { horizontalAlign = HAlign.LEFT }
            } layout { widthPercent = 1f }
        } layout { fill() }
    }

    private fun setHandlers() {
        btnSave.click().add { presenter.onSavePressed() }
        btnClear.click().add { presenter.onClearPressed() }
    }

    override fun getFirstName(): String = tiFirstName.text

    override fun getLastName(): String = tiLastName.text

    override fun getNickName(): String = tiNickName.text

    override fun getEmail(): String = tiEmail.text

    override fun getPassword(): String = tiPassword.text

    override fun getSex(): Sex = rgSex.selectedData ?: Sex.MALE

    override fun showAlert(title: String, text: String) { alert(title, text) }

    override fun clearAll() {
        listOf(tiFirstName, tiLastName, tiNickName, tiEmail, tiPassword).map { it.clear() }
        rgSex.selectedData = Sex.MALE
    }

    override fun dispose() {
        super.dispose()
        presenter.detach()
    }
}