package userDataInput.common.model.entities

data class UserEntity (
        var id: Long = 0L,
        var firstName: String,
        var lastName: String,
        val nickName: String,
        val email: String = "",
        var password: String = "",
        val sex: String
)