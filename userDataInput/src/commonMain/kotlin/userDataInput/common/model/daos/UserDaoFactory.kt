package userDataInput.common.model.daos

class UserDaoFactory private constructor(){
    companion object {
        fun getDao(type: Type): UserDao {
            return when(type) {
                Type.MOCK_IN_MEMORY -> MockInMemoryUserDao.getInstance()
            }
        }
    }

    enum class Type {
        MOCK_IN_MEMORY
    }
}