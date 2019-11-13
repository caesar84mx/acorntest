package userDataInput.usertable.presenter

import com.acornui.collection.ActiveList
import com.acornui.collection.addAll
import userDataInput.common.abstracts.mvp.AbstractPresenter
import userDataInput.common.model.daos.UserDao
import userDataInput.common.model.daos.UserDaoFactory
import userDataInput.common.model.entities.UserEntity
import userDataInput.usertable.UserDataTableContract
import userDataInput.usertable.model.dto.UserTableData

class UserDataTablePresenter private constructor():
        AbstractPresenter<UserDataTableContract.View>(),
        UserDataTableContract.Presenter {
    private val userDao: UserDao = UserDaoFactory.getDao(UserDaoFactory.Type.MOCK_IN_MEMORY)

    override val data: ActiveList<UserTableData> = ActiveList()

    init {
        data.addAll(
                userDao.getAll().map {
                    UserTableData(
                            it.id,
                            it.firstName,
                            it.lastName,
                            it.nickName,
                            it.sex)
                })
        val observables = userDao.getAllObservable()
        observables.added.add(::onEntityAdded)
        observables.modified.add(::onEntityModified)
    }

    private fun onEntityAdded(pos: Int, entity: UserEntity) {
        data.add(UserTableData(
                entity.id,
                entity.firstName,
                entity.lastName,
                entity.nickName,
                entity.sex))
    }

    private fun onEntityModified(pos: Int, entity: UserEntity) {
        data[pos] = UserTableData(
                entity.id,
                entity.firstName,
                entity.lastName,
                entity.nickName,
                entity.sex)
    }

    override fun update(userTableData: UserTableData) {
        userDao.get(userTableData.id)?.let { entity ->
            entity.firstName = userTableData.firstName
            entity.lastName = userTableData.lastName
            val isUpdateSuccessful = userDao.update(entity)
            if (isUpdateSuccessful.not()) {
                view?.showUpdateUnsuccessful()
            }
        }
    }

    companion object {
        fun create() = UserDataTablePresenter()
    }
}