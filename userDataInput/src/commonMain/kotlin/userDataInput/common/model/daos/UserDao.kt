package userDataInput.common.model.daos

import com.acornui.collection.ObservableList
import userDataInput.common.model.entities.UserEntity

interface UserDao {
    fun get(id: Long): UserEntity?
    fun save(userEntity: UserEntity): Long
    fun update(userEntity: UserEntity): Boolean
    fun delete(id: Long): Boolean
    fun getAll(): MutableList<UserEntity>
    fun getAllObservable(): ObservableList<UserEntity>
}