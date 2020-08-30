package pl.amistad.exercise

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_intro.*
import pl.amistad.exercise.elevation.ElevationRequest
import pl.amistad.exercise.elevation.network.ElevationNetworking

class IntroActivity : AppCompatActivity() {

    private val elevationNetworking = ElevationNetworking()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // poniższa metoda nadaje naszemu Activity widok, który został
        // opisany poprzez plik xml "activity_intro"
        setContentView(R.layout.activity_intro)


        findViewById<View>(R.id.intro_send_button).setOnClickListener {
            fetchElevationFromServer()
        }


    }

    private fun fetchElevationFromServer() {
        try {
            val request = createRequest()
            elevationNetworking.fetchElevation(
                request,
                resultCallback = { showResult(it) },
                failureCallback = { showFailure(it) }
            )
        } catch (exception: java.lang.IllegalArgumentException) {
            showIncorrectInput()
        }
    }

    private fun showFailure(it: Throwable) {
        showToast(getString(R.string.msg_elevation_fetch_error))
    }

    private fun showIncorrectInput() {
        showToast(getString(R.string.msg_incorrect_coordinates))
    }

    private fun showResult(elevation: Double) {
        showToast(getString(R.string.msg_elevation_is) + elevation)
    }

    private fun createRequest(): ElevationRequest {

        val latitude = intro_latitude_value.text.toString().toDoubleOrNull()
        val longitude = intro_longitude_value.text.toString().toDoubleOrNull()

        latitude ?: throw IllegalArgumentException()
        longitude ?: throw IllegalArgumentException()

        return ElevationRequest(latitude, longitude)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
