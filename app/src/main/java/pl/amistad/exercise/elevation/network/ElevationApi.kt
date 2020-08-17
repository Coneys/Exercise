package pl.amistad.exercise.elevation.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ElevationApi {
    @GET("elevation")
    fun getElevation(@Query("latitude") lat: Double, @Query("longitude") lon: Double): Call<ResponseBody>
}
