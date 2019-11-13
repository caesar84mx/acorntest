package userDataInput.common.model.daos

import com.acornui.collection.ObservableList
import userDataInput.common.model.entities.UserEntity

class MockInMemoryUserDao private constructor(): UserDao {
    private val dataStorage = MockInMemoryUserStorage.data()
    private var lastIndex = 0L

    override fun get(id: Long): UserEntity? = dataStorage.find { it.id == id }

    override fun save(userEntity: UserEntity): Long {
        dataStorage
                .find { it.email == userEntity.email || it.nickName == userEntity.nickName }
                ?.let { return@save 0L}

        ++lastIndex
        userEntity.id = lastIndex
        dataStorage.add(userEntity)

        return lastIndex
    }

    override fun update(userEntity: UserEntity): Boolean {
        if (userEntity.id == 0L) {
            return false
        }

        dataStorage.find { user -> user.id == userEntity.id }?.let { user ->
            user.firstName = userEntity.firstName
            user.lastName = userEntity.lastName
            user.password = userEntity.password

            return@update true
        }

        return false
    }

    override fun delete(id: Long): Boolean = dataStorage.removeAll { it.id == id }

    override fun getAllObservable(): ObservableList<UserEntity> = dataStorage

    override fun getAll(): MutableList<UserEntity> = dataStorage.toMutableList()

    companion object {
        private var instance: MockInMemoryUserDao? = null

        fun getInstance(): UserDao {
            instance?.let { dao ->
                return@getInstance dao
            }

            val temp = MockInMemoryUserDao()
            instance = temp
            return temp
        }
    }
}