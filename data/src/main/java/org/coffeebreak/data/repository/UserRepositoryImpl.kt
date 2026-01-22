package org.coffeebreak.data.repository

import android.util.Log
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.realtime.PrimaryKey
import io.github.jan.supabase.realtime.selectAsFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import org.coffeebreak.data.data_source.InitSupabaseClient.client
import org.coffeebreak.data.data_source.local.dao.UserDao
import org.coffeebreak.data.dto.UserModelDto
import org.coffeebreak.data.dto.toDomain
import org.coffeebreak.domain.model.UserModel
import org.coffeebreak.domain.repository.SessionRepository
import org.coffeebreak.domain.repository.UserRepository
import org.coffeebreak.domain.utils.CustomResult

class UserRepositoryImpl(
    private val userDao: UserDao,
    private val sessionRepository: SessionRepository
) : UserRepository {
    override suspend fun getUserById(id: String?): CustomResult<UserModel> {
        return try {
            val userId = sessionRepository.getSession()
            Log.e("TAG userId", "$userId: ");
            val res = if (id.isNullOrBlank()) {
                userDao.getUserById(userId!!.userId)
            } else {
                userDao.getUserById(id)
            }
            CustomResult.Success(res!!.toDomain())
        } catch (e: Exception) {
            e.printStackTrace()
            CustomResult.Error(e.message ?: e.localizedMessage ?: "")
        }
    }

    override suspend fun updateAddress(address: String): CustomResult<Unit> {
        return try {
            val res = userDao.updateUser(
                userDao.getUserById(client.auth.currentUserOrNull()!!.id)!!.copy(address = address)
            )

            CustomResult.Success(Unit)
        } catch (e: Exception) {
            CustomResult.Error(e.message!!)
        }
    }

//    fun getFlow(id: String) = channelFlow<UserModelDto> {
//
//        val job = launch(Dispatchers.IO) {
//            userDao.getFlowUserById(id)
//                .collect {
//                    send(it)
//                }
//        }
//
////
////        result?.let {
////            send(it)
////            // update
////        }
//
//        awaitClose {
//            job.cancel()
//        }
//    }
}