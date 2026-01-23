package org.coffeebreak.data.dto

import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.coffeebreak.domain.model.FullOrderModel

@Entity(tableName = "preOrders")
@Serializable
data class OrderModelDto(
    val isOrdered: Boolean? = null,
    @PrimaryKey(autoGenerate = true) val localId: Long? = null,
    val id: String? = null,
    @SerialName("user_id") val userId: String? = null,
    @SerialName("barista_id") val baristaId: String? = null,
    @SerialName("coffee_id") val coffeeId: String,
    @SerialName("sort_id") val sortId: String? = null,
    @SerialName("country_id") val countryId: String? = null,
    @SerialName("supplement_id") val supplementId: String? = null,
    val count: Int,
    val ristretto: String,
    val place: String,
    val volume: String,
    val roasting: String? = null,
    val grinding: String? = null,
    val milk: String? = null,
    val syrup: String? = null,
    val ice: String? = null,
    @SerialName("spec_time") val specTime: Boolean,
    val time: String? = null,
    @SerialName("total_coast") val totalCoast: Long,
    @SerialName("created_at") val createdAt: Instant? = null
)

fun OrderModelDto.toDomain(): FullOrderModel = (
        FullOrderModel(
            id = id,
            userId = userId,
            baristaId = baristaId,
            coffeeId = coffeeId,
            countryId = countryId,
            supplementId = supplementId,
            sortId = sortId,
            count = count,
            ristretto = ristretto,
            place = place,
            volume = volume,
            roasting = roasting,
            grinding = grinding,
            milk = milk,
            syrup = syrup,
            ice = ice,
            specTime = specTime,
            time = time,
            totalCoast = totalCoast,
            createdAt = createdAt
        )
        )

fun FullOrderModel.toDto(userId: String, isOrdered: Boolean): OrderModelDto = (
        OrderModelDto(
            isOrdered = isOrdered,
            id = id,
            userId = userId,
            baristaId = baristaId,
            coffeeId = coffeeId,
            countryId = countryId,
            supplementId = supplementId,
            sortId = sortId,
            count = count,
            ristretto = ristretto,
            place = place,
            volume = volume,
            roasting = roasting,
            grinding = grinding,
            milk = milk,
            syrup = syrup,
            ice = ice,
            specTime = specTime,
            time = time,
            totalCoast = totalCoast,
            createdAt = createdAt

        )
        )