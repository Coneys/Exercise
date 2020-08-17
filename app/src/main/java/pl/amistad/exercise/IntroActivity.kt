package pl.amistad.exercise

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class IntroActivity : AppCompatActivity() {

    // private val elevationNetworking = ElevationNetworking()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // poniższa metoda nadaje naszemu Activity widok, który został
        // opisany poprzez plik xml "activity_intro"
        setContentView(R.layout.activity_intro)


        // TODO Podpowiedź, aby uzyskać referencję do widoku androidowego użyj funkcji findViewById

    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
