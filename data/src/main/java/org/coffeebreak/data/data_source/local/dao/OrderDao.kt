package org.coffeebreak.data.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import org.coffeebreak.data.dto.OrderModelDto

@Dao
interface OrderDao {
    @Insert(onConflict = REPLACE)
    fun insertPreOrderData(model: OrderModelDto)

    @Query("select * from preOrders where isOrdered = true and userId = :userId")
    fun getPreOrder(userId: String): OrderModelDto

}