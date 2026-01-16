package org.coffeebreak.data.repository

import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.postgrest
import org.coffeebreak.data.data_source.InitSupabaseClient.client
import org.coffeebreak.data.data_source.local.dao.OrderDao
import org.coffeebreak.data.dto.BaristaModelDto
import org.coffeebreak.data.dto.ItemModelDto
import org.coffeebreak.data.dto.OrderModelDto
import org.coffeebreak.data.dto.toDomain
import org.coffeebreak.data.dto.toDto
import org.coffeebreak.domain.model.BaristaModel
import org.coffeebreak.domain.model.ItemModel
import org.coffeebreak.domain.model.OrderModel
import org.coffeebreak.domain.repository.OrderRepository
import org.coffeebreak.domain.utils.CustomResult

class OrderRepositoryImpl(
    private val orderDao: OrderDao
) : OrderRepository {
    override suspend fun getBaristas(): CustomResult<List<BaristaModel>> {
        return try {
            val res = client.postgrest["baristas"].select().decodeList<BaristaModelDto>()
                .map { it.toDomain() }
            CustomResult.Success(res)
        } catch (e: Exception) {
            CustomResult.Error(e.message!!)
        }
    }

    override suspend fun setPreOrder(model: OrderModel): CustomResult<Unit> {
        return try {
            val res =
                orderDao.insertPreOrderData(
                    model.toDto(
                        userId = client.auth.currentUserOrNull()!!.id,
                        isOrdered = true
                    )
                )
            CustomResult.Success(res)
        } catch (e: Exception) {
            CustomResult.Error(e.message!!)
        }
    }

    override suspend fun setOrder(model: OrderModel): CustomResult<Unit> {
        return try {
            val preOrder = orderDao.getPreOrder(client.auth.currentUserOrNull()!!.id)
            val res = client.postgrest["orders"].insert(
                mapOf(

                    "user_id" to client.auth.currentUserOrNull()!!.id,
                    "coffee_id" to preOrder.coffeeId,
                    "barista_id" to model.baristaId,
                    "sort_id" to model.sortId,
                    "supplement_id" to model.supplementId,
                    "count" to preOrder.count,
                    "ristretto" to preOrder.ristretto,
                    "place" to preOrder.place,
                    "volume" to preOrder.volume,
                    "spec_time" to preOrder.specTime,
                    "roasting" to model.roasting,
                    "grinding" to model.grinding,
                    "milk" to model.milk,
                    "syrup" to model.syrup,
                    "ice" to model.ice,
                    "total_coast" to model.totalCoast,
                    "country_id" to model.countryId,
                    "time" to preOrder.time
                )
            )
            CustomResult.Success(Unit)
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
}