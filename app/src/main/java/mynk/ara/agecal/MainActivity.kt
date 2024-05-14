package mynk.ara.agecal

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private var tvselecteddate:TextView?=null
    private var tvAgeInMinutes:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvselecteddate=findViewById<TextView>(R.id.tvselecteddate)

        tvAgeInMinutes=findViewById<TextView>(R.id.tvAgeInMinutes)

        val BtnDatePicker:Button=findViewById(R.id.BtnDatePicker)

        BtnDatePicker.setOnClickListener{
            clickDatePicker()
        }
    }
    @SuppressLint("SuspiciousIndentation")
    fun clickDatePicker(){
        val myCalendar= Calendar.getInstance()

        val year=myCalendar.get(Calendar.YEAR)

        val month=myCalendar.get(Calendar.MONTH)

        val day=myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd=DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                Toast.makeText(
                    this,
                    "year was $year,month was ${month + 1}," +
                            "day of month was $dayOfMonth", Toast.LENGTH_LONG
                ).show()
                val selecteDate = "$dayOfMonth/ ${month + 1}/$year"

                tvselecteddate?.text=selecteDate.toString()

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selecteDate)
                theDate?.let {


                    val selectedDateInMinutes = theDate.time / 60000

                    val cdf = sdf.parse(sdf.format(System.currentTimeMillis()))
                    val currentDateInMinutes=cdf.time / 60000

                    val diffrenceDateinMinutes = currentDateInMinutes - selectedDateInMinutes

                    tvAgeInMinutes?.text = diffrenceDateinMinutes.toString()
                }

            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
                    dpd.show()

        Toast.makeText(this,"BtnDatePicker Pressed",Toast.LENGTH_LONG).show()

    }
}