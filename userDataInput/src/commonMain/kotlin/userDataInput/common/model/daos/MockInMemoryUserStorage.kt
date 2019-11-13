package userDataInput.common.model.daos

import com.acornui.collection.ActiveList
import userDataInput.common.model.entities.UserEntity

class MockInMemoryUserStorage private constructor() {
    val dataStorage = ActiveList<UserEntity>()

    companion object {
        private var instance: MockInMemoryUserStorage? = null

        private fun getInstance(): MockInMemoryUserStorage {
            instance?.let {
                return@getInstance it
            }

            val temp = MockInMemoryUserStorage()
            instance = temp
            return temp
        }

        fun data() = getInstance().dataStorage
    }
}