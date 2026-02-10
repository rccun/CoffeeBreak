package org.coffeebreak.data.repository

import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.coffeebreak.data.data_source.InitSupabaseClient.client
import org.coffeebreak.data.data_source.local.dao.OrderDao
import org.coffeebreak.data.data_source.local.dao.UserDao
import org.coffeebreak.data.dto.BaristaModelDto
import org.coffeebreak.data.dto.ItemModelDto
import org.coffeebreak.data.dto.OrderModelDto
import org.coffeebreak.data.dto.RateOrderModelDto
import org.coffeebreak.data.dto.toDomain
import org.coffeebreak.data.dto.toDto
import org.coffeebreak.domain.model.BaristaModel
import org.coffeebreak.domain.model.ItemModel
import org.coffeebreak.domain.model.FullOrderModel
import org.coffeebreak.domain.model.OrderModel
import org.coffeebreak.domain.repository.OrderRepository
import org.coffeebreak.domain.repository.SessionRepository
import org.coffeebreak.domain.utils.CustomResult
import kotlin.collections.mapOf

class OrderRepositoryImpl(
    private val orderDao: OrderDao,
    private val userDao: UserDao,
    private val sessionRepository: SessionRepository
) : OrderRepository {
    override suspend fun getLastOrder(): CustomResult<FullOrderModel> {

        val userId = client.auth.currentUserOrNull()?.id ?: "9a5dc629-75b7-4493-9998-c491255ded68"
        return try {
            val res = client.postgrest["orders"].select {
                filter {
                    eq("user_id", userId)
                }
                order("created_at", Order.DESCENDING)
                limit(1)
            }.decodeSingle<OrderModelDto>().toDomain()
            CustomResult.Success(res)
        } catch(e: Exception) {
            CustomResult.Error(e.message ?: "")
        }
    }

    override suspend fun getBaristas(): CustomResult<List<BaristaModel>> {
        return try {
            val res = client.postgrest["baristas"].select().decodeList<BaristaModelDto>()
                .map { it.toDomain() }
            CustomResult.Success(res)
        } catch (e: Exception) {
            CustomResult.Error(e.message!!)
        }
    }

    override suspend fun setPreOrder(model: FullOrderModel): CustomResult<Unit> {

        val userId = client.auth.currentUserOrNull()?.id ?: return CustomResult.Error(
            "Пользователь не авторизован"
        )
        return try {
            val res =
                orderDao.insertPreOrderData(
                    model.toDto(
                        userId = userId,
                        isOrdered = false
                    )
                )
            CustomResult.Success(res)
        } catch (e: Exception) {
            CustomResult.Error(e.message!!)
        }
    }

    override suspend fun setOrder(model: FullOrderModel): CustomResult<Unit> {
        val userId = client.auth.currentUserOrNull()?.id ?: return CustomResult.Error(
            "Пользователь не авторизован"
        )
        return try {

            val preOrder = orderDao.getPreOrder(userId)

            if (preOrder == null) {
                orderDao.insertPreOrderData(model.toDto(userId = userId, isOrdered = true))
                val res2 = client.postgrest["orders"].insert(
                    buildJsonObject {
                        put("user_id", userId)
                        put("coffee_id", model.coffeeId)
//                        put("barista_id", model.baristaId)
//                        put("sort_id", model.sortId)
//                        put("supplement_id", model.supplementId)
                        put("count", model.count)
                        put("ristretto", model.ristretto)
                        put("place", model.place)
                        put("volume", model.volume)
                        put("spec_time", model.specTime)
//                        put("roasting",  model.roasting)
//                        put("grinding",  model.grinding)
//                        put("milk",  model.milk)
//                        put("syrup",  model.syrup)
//                        put("ice", model.ice)
                        put("total_coast", model.totalCoast)
//                        put("country_id", model.countryId)
                        put("time", model.time)
                    }
                ) {
                    select()
                }.decodeSingle<OrderModelDto>()
                orderDao.updateOrder(orderDao.getLastOrder(userId).copy(id = res2.id))

            } else {
                val res = client.postgrest["orders"].insert(
                    buildJsonObject {
                        put("user_id", userId)
                        put("coffee_id", preOrder.coffeeId)
                        put("barista_id", model.baristaId)
                        put("sort_id", model.sortId)
                        put("supplement_id", model.supplementId)
                        put("count", preOrder.count)
                        put("ristretto", preOrder.ristretto)
                        put("place", preOrder.place)
                        put("volume", preOrder.volume)
                        put("spec_time", preOrder.specTime)
                        put("roasting", model.roasting)
                        put("grinding", model.grinding)
                        put("milk", model.milk)
                        put("syrup", model.syrup)
                        put("ice", model.ice)
                        put("total_coast", model.totalCoast)
                        put("country_id", model.countryId)
                        put("time", preOrder.time)
                    }
                ) {
                    select()
                }.decodeSingle<OrderModelDto>()
                orderDao.updateOrder(
                    orderDao.getPreOrder(userId)!!.copy(isOrdered = true, id = res.id)
                )
            }
            CustomResult.Success(Unit)
        } catch (e: Exception) {
            CustomResult.Error(e.message!!)
        }
    }

    override suspend fun getOrderInfo(): CustomResult<Triple<String, String, String>> {
        val userId = client.auth.currentUserOrNull()?.id ?: sessionRepository.getSession()?.userId
        ?: return CustomResult.Error("Пользователь не авторизован")
        return try {
            val userName = userDao.getUserById(userId)!!.name
            val address = userDao.getUserById(userId)!!.address!!
            val time = orderDao.getLastOrder(userId).time!!
            val res = Triple(userName, time, address)
            CustomResult.Success(res)
        } catch (e: Exception) {
            CustomResult.Error(e.message!!)
        }
    }

    override suspend fun getItemsByCategory(category: String): CustomResult<List<ItemModel>> {
        return try {
            val res =
                client.postgrest["items"].select {
                    filter {
                        eq("category", category)
                    }
                }.decodeList<ItemModelDto>().map { it.toDomain() }
            CustomResult.Success(res)
        } catch (e: Exception) {
            CustomResult.Error(e.message!!)
        }
    }

    override suspend fun setRate(rate: Int): CustomResult<Unit> {
        return try {
            val orderId = orderDao.getLastOrder(
                sessionRepository.getSession()?.userId ?: return CustomResult.Error("Нет ID")
            ).id
            val res = client.postgrest["rate_order"].insert(
                RateOrderModelDto(
                    orderId = orderId!!,
                    rate = rate.toString()
                )
            )
            CustomResult.Success(Unit)
        } catch (e: Exception) {
            CustomResult.Error(e.message!!)
        }
    }

    override suspend fun getOrdersByUserId(): CustomResult<List<FullOrderModel>> {
        return try {
            val userId =
                sessionRepository.getSession()?.userId ?: return CustomResult.Error("Нет ID")
            val res = client.postgrest["orders"].select {
                filter {
                    eq("user_id", userId)
                }
            }.decodeList<OrderModelDto>().map { it.toDomain() }
            CustomResult.Success(res)
        } catch (e: Exception) {
            CustomResult.Error(e.message!!)
        }
    }
}