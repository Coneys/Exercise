package pl.amistad.exercise.elevation.network

import okhttp3.ResponseBody
import pl.amistad.exercise.elevation.ElevationRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class ElevationNetworking {

    private val retrofit = createRetrofit()
    private val api = retrofit.create(ElevationApi::class.java)

    private fun createRetrofit(): Retrofit {
        val baseUrl = "https://tomcat.amistad.pl:8443/navigation-data/"

       TODO("Stwórz instancję Retrofit na podstawie poradnika: https://www.c-sharpcorner.com/article/how-to-use-retrofit-2-with-android-using-kotlin/")
    }

    //callback to zwykła lambda, która przyjmuje jako argument Double, ale nie zwraca żadnego wyniku
    fun fetchElevation(elevationRequest: ElevationRequest, resultCallback: (Double) -> Unit) {
        val call = api.getElevation(elevationRequest.latitude, elevationRequest.longitude)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>?) {

            }

        })
    }


}