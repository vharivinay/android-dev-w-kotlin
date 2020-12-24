package earth.devgalileo.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {view -> clickDatePicker(view)}

    }

    fun clickDatePicker(view: View) {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->
            //Toast.makeText(this, "The Chosen Date is " +
                   // "$selectedYear/$selectedMonth/$selectedDayOfMonth", Toast.LENGTH_LONG).show()

            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

            tvSelectedDate.setText(selectedDate)

            val simpleDate = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = simpleDate.parse(selectedDate)

            val selectedDateInMinutes = theDate!!.time / 60000

            val currentDate = simpleDate.parse(simpleDate.format(System.currentTimeMillis()))

            val currentDateToMinutes = currentDate!!.time / 60000

            val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes

            val inDays = differenceInMinutes/1440

            val inHours = inDays * 24

            val inSeconds = differenceInMinutes * 60

            tvSelectedDateInMinutes.setText(differenceInMinutes.toString())
            tvSelectedDateInDays.setText(inDays.toString())
            tvSelectedDateInHours.setText(inHours.toString())
            tvSelectedDateInSeconds.setText(inSeconds.toString())

            llDate.visibility = View.VISIBLE
            llResults.visibility = View.VISIBLE

        },
            year,
            month,
            day)
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()

    }

}
