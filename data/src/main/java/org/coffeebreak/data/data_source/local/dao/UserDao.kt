package org.coffeebreak.data.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.coffeebreak.data.dto.UserModelDto
import org.coffeebreak.domain.model.UserModel


@Dao
interface UserDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertUserData(userModel: UserModelDto)

    @Query("select * from users")
    suspend fun selectUserData(): UserModelDto?

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: String): UserModelDto?

    @Query("select * from users where id =:id")
    fun getFlowUserById(id: String): Flow<UserModelDto>

    @Update
    suspend fun updateUser(user: UserModelDto)
}
