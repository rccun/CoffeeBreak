package org.coffeebreak.data.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import org.coffeebreak.data.dto.OrderModelDto

@Dao
interface OrderDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertPreOrderData(model: OrderModelDto)

    @Query("select * from preOrders where isOrdered = 0 and userId = :userId order by localId desc limit 1")
    suspend fun getPreOrder(userId: String): OrderModelDto?

    @Update
    suspend fun updateOrder(model: OrderModelDto)

    @Query("select * from preOrders where userId = :userId order by localId desc limit 1")
    suspend fun getLastOrder(userId: String): OrderModelDto

}