package nf.devgalileo.countonit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var timesClicked = 0

// set on-click listener
       buttonReset.setOnClickListener() {
            timesClicked = 0
            textView.text = timesClicked.toString()
            Toast.makeText(this@MainActivity, "Reset To Zero!", Toast.LENGTH_SHORT).show()
        }
        buttonPlus.setOnClickListener() {
            timesClicked +=1
            textView.text = timesClicked.toString()
        }
        buttonMinus.setOnClickListener() {

            if(timesClicked == 0) {
                // code to run if condition is true
                textView.text = timesClicked.toString()
                Toast.makeText(this@MainActivity, "Wow! Peaceful, zero people.", Toast.LENGTH_SHORT).show()
            }
            else {
                // code to run if condition is false
                timesClicked -=1
                textView.text = timesClicked.toString()
            }

        }



    }
}
