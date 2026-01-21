//package org.coffeebreak.data.ai
//
//import android.content.Context
//import org.coffeebreak.data.mapper.CoffeePromptMapper
//import org.coffeebreak.domain.model.CoffeeAIModel
//import org.coffeebreak.domain.repository.CoffeeAIRepository
//import org.json.JSONObject
//
//class CoffeeAIRepositoryImpl(
////    private val runner: CoffeeModelRunner,
//    private val mapper: CoffeePromptMapper
//) : CoffeeAIRepository {
//
//    override suspend fun generateDescription(input: CoffeeAIModel): String {
//        TODO()
//    }
//    private fun loadByteEncoder(context: Context): Map<Int, String> {
//        val jsonString = context.assets.open("byte_encoder.json").bufferedReader().use { it.readText() }
//        val jsonObject = JSONObject(jsonString)
//        val map = mutableMapOf<Int, String>()
//
//        jsonObject.keys().forEach { key ->
//            map[key.toInt()] = jsonObject.getString(key)
//        }
//        return map
//    }
//
//    private fun createByteDecoder(encoder: Map<Int, String>): Map<String, Int> {
//        return encoder.entries.associate { it.value to it.key }
//    }
//}