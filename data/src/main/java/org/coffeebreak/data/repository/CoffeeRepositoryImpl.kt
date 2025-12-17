package org.coffeebreak.data.repository

import android.util.Log
import io.github.jan.supabase.postgrest.postgrest
import org.coffeebreak.data.data_source.InitSupabaseClient.client
import org.coffeebreak.data.dto.CoffeeModelDto
import org.coffeebreak.data.dto.toDomain
import org.coffeebreak.domain.model.CoffeeModel
import org.coffeebreak.domain.repository.CoffeeRepository

class CoffeeRepositoryImpl() : CoffeeRepository {
    override suspend fun getCoffees(): Result<List<CoffeeModel>> {
        return try {
            val res = client.postgrest["coffees"].select().decodeList<CoffeeModelDto>().map { it.toDomain() }
            Result.success(res)
        } catch (e: Exception) {
            Log.e("CoffeeRepo", "Ошибка получения", e)
            Result.failure(e)
        }
    }
}