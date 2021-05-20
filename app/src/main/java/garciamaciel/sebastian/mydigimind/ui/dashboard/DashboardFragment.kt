package garciamaciel.sebastian.mydigimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import garciamaciel.sebastian.mydigimind.R
import garciamaciel.sebastian.mydigimind.ui.Task
import garciamaciel.sebastian.mydigimind.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {

    private lateinit var storage: FirebaseFirestore
    private lateinit var usuario: FirebaseAuth
    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        // Instanciar firabase
        storage = FirebaseFirestore.getInstance()
        usuario = FirebaseAuth.getInstance()

        val btn_time: Button = root.findViewById(R.id.btn_time)

        btn_time.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                btn_time.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(
                root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), true
            ).show()
        }

        var btn_save = root.findViewById(R.id.btn_save) as Button
        var et_titulo = root.findViewById(R.id.et_task) as EditText
        var checkMonday = root.findViewById(R.id.checkMonday) as CheckBox
        var checkTuesday = root.findViewById(R.id.checkTuesday) as CheckBox
        var checkWednesday = root.findViewById(R.id.checkWednesday) as CheckBox
        var checkThursday = root.findViewById(R.id.checkThursday) as CheckBox
        var checkFriday = root.findViewById(R.id.checkFriday) as CheckBox
        var checkSaturday = root.findViewById(R.id.checkSaturday) as CheckBox
        var checkSunday = root.findViewById(R.id.checkSunday) as CheckBox


        btn_save.setOnClickListener {
            var titulo = et_task.text.toString()
            var time = btn_time.text.toString()

            var days = ArrayList<String>()
            val actividad = hashMapOf(
                "actividad" to et_task.text.toString(),
                "email" to usuario.currentUser?.email.toString(),
                "lunes" to checkMonday.isChecked,
                "martes" to checkTuesday.isChecked,
                "miercoles" to checkWednesday.isChecked,
                "jueves" to checkThursday.isChecked,
                "viernes" to checkFriday.isChecked,
                "sabado" to checkSaturday.isChecked,
                "domingo" to checkSunday.isChecked,
                "tiempo" to btn_time.toString()
            )

            storage.collection("actividades")
                .add(actividad)
                .addOnSuccessListener {
                    Toast.makeText(
                        root.context,
                        "Task agregada.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener {
                    Toast.makeText(
                        root.context,
                        it.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }

        }

        return root
    }
}