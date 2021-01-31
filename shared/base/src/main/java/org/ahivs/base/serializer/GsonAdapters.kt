package org.ahivs.base.serializer

import com.google.gson.JsonDeserializer
import com.google.gson.TypeAdapter
import java.lang.reflect.Type
import javax.inject.Inject

//Add your custom serializers to customize the Json serialization and deserialization
class GsonAdapters @Inject constructor() {
    fun getDeserializerTypeAdapters(): Map<Type, JsonDeserializer<*>> = mapOf()
    fun getTypeAdapters(): Map<Type, TypeAdapter<*>> = mapOf()

    /*This is just a sample
    data class Movie(val id: Int, var imagePath: String)
    class MovieDeserializer : JsonDeserializer<Movie> {
        val gson = GsonBuilder().create()
        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Movie {
            val movie = gson.fromJson(json, Movie::class.java)
            movie.imagePath = movie.imagePath.replace("*", "")
            return movie
        }
    }
    fun getDeserializerTypeAdapters(): Map<Type, JsonDeserializer<*>> = mutableMapOf(
            Movie::class.java to MovieDeserializer()
    )*/

}
