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
        val baseUrl = "https://services.amistad.pl/elevation"

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .build()
    }

    fun fetchElevation(
        elevationRequest: ElevationRequest,
        resultCallback: (Double) -> Unit,
        failureCallback: (Throwable) -> Unit
    ) {
        val call = api.getElevation(elevationRequest.latitude, elevationRequest.longitude)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable) {
                failureCallback.invoke(t)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>?) {
                val responseBody = response!!.body()
                val elevationAsString = responseBody.string()

                resultCallback.invoke(elevationAsString.toDouble())
            }

        })
    }


}