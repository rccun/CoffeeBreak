package org.coffeebreak.data.repository

import io.github.jan.supabase.postgrest.postgrest
import org.coffeebreak.data.data_source.InitSupabaseClient.client
import org.coffeebreak.data.dto.BaristaModelDto
import org.coffeebreak.data.dto.ItemModelDto
import org.coffeebreak.data.dto.toDomain
import org.coffeebreak.domain.model.BaristaModel
import org.coffeebreak.domain.model.ItemModel
import org.coffeebreak.domain.repository.OrderRepository
import org.coffeebreak.domain.utils.CustomResult

class OrderRepositoryImpl() : OrderRepository {
    override suspend fun getBaristas(): CustomResult<List<BaristaModel>> {
        return try {
            val res = client.postgrest["baristas"].select().decodeList<BaristaModelDto>()
                .map { it.toDomain() }
            CustomResult.Success(res)
        } catch (e: Exception) {
            CustomResult.Error(e.message!!)
        }
    }

    override suspend fun getItemsByCategory(category: String): CustomResult<List<ItemModel>> {
        return try {
            val res =
                client.postgrest["items"].select{
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