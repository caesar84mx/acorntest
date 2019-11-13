package userDataInput.userinput.presenter

import userDataInput.common.abstracts.mvp.AbstractPresenter
import userDataInput.common.model.daos.UserDao
import userDataInput.common.model.daos.UserDaoFactory
import userDataInput.common.model.entities.UserEntity
import userDataInput.userinput.UserInputContract
import userDataInput.common.model.extra.Sex

class UserDataInputPresenter private constructor() :
        AbstractPresenter<UserInputContract.View>(),
        UserInputContract.Presenter {

    private val userDao: UserDao = UserDaoFactory.getDao(UserDaoFactory.Type.MOCK_IN_MEMORY)

    override fun onClearPressed() {
        view?.clearAll()
        view?.showAlert("Success!", "Cleared")
    }

    override fun onSavePressed() {
        val firstName = view?.getFirstName() ?: ""
        val lastName = view?.getLastName() ?: ""
        val nickName = view?.getNickName() ?: ""
        val email = view?.getEmail() ?: ""
        val password = view?.getPassword() ?: ""
        val sex = view?.getSex() ?: Sex.MALE

        if (isDataCorrect(firstName, lastName, nickName, email, password)) {
            val result = userDao.save(
                    UserEntity(
                            firstName = firstName,
                            lastName = lastName,
                            nickName = nickName,
                            email = email,
                            password = password,
                            sex = sex.value))

            if (result == 0L) {
                view?.showAlert("Error!", "This email or nickname already exist!")
                return
            } else {
                view?.clearAll()
            }
        } else {
            return
        }

        view?.showAlert("Success!", "User saved")

    }

    private fun isDataCorrect(firstName: String, lastName: String, nickName: String, email: String, password: String): Boolean {
        val isAnyEmpty = listOf(firstName, lastName, nickName, email, password).any { it.isEmpty() }
        if (isAnyEmpty) {
            view?.showAlert("Error!", "You have empty fields. Please, fulfill all of them!")
        }
        return !isAnyEmpty
    }

    companion object {
        fun create() = UserDataInputPresenter()
    }
}