package garciamaciel.sebastian.mydigimind.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import garciamaciel.sebastian.mydigimind.R

class PasswordActivityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

        val btn_restablecer: Button = findViewById(R.id.btn_restablecer)

        btn_restablecer.setOnClickListener {
            val et_correo: EditText = findViewById(R.id.et_correo_cont)

            var correo: String = et_correo.text.toString()

            if (!correo.isNullOrBlank()) {
                //enviarcorreo
            } else {
                Toast.makeText(
                    this, "Ingresar correo",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}