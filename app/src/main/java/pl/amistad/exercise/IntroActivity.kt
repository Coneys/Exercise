package pl.amistad.exercise

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_intro.*
import pl.amistad.exercise.database.Database
import pl.amistad.exercise.database.introScreenShowed.IntroScreenShowedEntity
import pl.amistad.exercise.elevation.ElevationRequest
import pl.amistad.exercise.elevation.network.ElevationNetworking
import java.util.*

class IntroActivity : AppCompatActivity() {

    private val elevationNetworking = ElevationNetworking()
    private val introShowedDao = Database.db.introShowedDao()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_intro)


        findViewById<View>(R.id.intro_send_button).setOnClickListener {
            fetchElevationFromServer()
        }

        saveIntroShowed()

        printShowedIntroEvents()

    }

    private fun printShowedIntroEvents() {
        introShowedDao.getAll().forEach {
            println("Intro showed " + it.id + it.timeStamp)
        }
    }

    private fun saveIntroShowed() {
        val currentTime = Date().time
        val entity = IntroScreenShowedEntity(currentTime)
        introShowedDao.insert(entity)
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
