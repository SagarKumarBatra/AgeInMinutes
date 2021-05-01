package com.developer.ageinminutes


import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.developer.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnDatePicker.setOnClickListener {
            clickButton(it)
        }

    }


    fun clickButton(view: View) {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)



        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this, "The chosen year is $selectedYear. The month is $selectedMonth " +
                        "and The Day is $selectedDayOfMonth", Toast.LENGTH_LONG).show()
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                binding.tvSelectedDate.text = selectedDate
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                var theDate = sdf.parse(selectedDate)
                var selectedDateInMinutes = theDate!!.time / 60000
                var currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                var currentDateInMinutes = currentDate!!.time / 60000
                var ageInMinutes = currentDateInMinutes - selectedDateInMinutes
                var ageInDays = ageInMinutes / 1440
                if (ageInDays >= 0) {
                    binding.tvSelectedInMinutes.text = "$ageInDays"
                }
                else {
                    binding.tvSelectedInMinutes.text = "N/A"
                }

            },
            year, month, day)
            dpd.datePicker.setMaxDate(Date().time - 86400000)
            dpd.show()
        }
    }

}