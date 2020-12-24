package earth.devgalileo.a7minuteworkout

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_calc.*
import java.math.BigDecimal
import java.math.RoundingMode

class calcActivity : AppCompatActivity() {

    val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
    val US_UNITS_VIEW = "US_UNIT_VIEW"

    var currentVisibleView: String = METRIC_UNITS_VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)

        setSupportActionBar(findViewById(R.id.toolbar_bmi_activity))

        val actionBar = supportActionBar
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "Calculate BMI"
        }
        toolbar_bmi_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        btnCalculateMetricUnits.setOnClickListener {
            if(currentVisibleView == METRIC_UNITS_VIEW){
                calculateMetricBMI()
            }else if(currentVisibleView == US_UNITS_VIEW){
                calculateUSBMI()
            }
        }

        makeVisibleMetricUnitsView()
        rgUnits.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == R.id.rbMetricUnits){
                makeVisibleMetricUnitsView()
            }else{
                makeVisibleUSUnitsView()
            }
        }

    }

    private fun makeVisibleMetricUnitsView(){
        currentVisibleView = METRIC_UNITS_VIEW

        tiUSUnitWeight.visibility = View.GONE
        llUsUnitHeight.visibility = View.GONE

        etMetricUnitWeight.text!!.clear()
        etMetricUnitHeight.text!!.clear()

        tiMetricUnitWeight.visibility = View.VISIBLE
        tiMetricUnitHeight.visibility = View.VISIBLE

        llDisplayBMIResult.visibility = View.GONE

    }

    private fun makeVisibleUSUnitsView(){
        currentVisibleView = US_UNITS_VIEW
        tiMetricUnitWeight.visibility = View.GONE
        tiMetricUnitHeight.visibility = View.GONE

        etUSUnitWeight.text!!.clear()
        etUSUnitHeight_Ft.text!!.clear()
        etUSUnitHeight_In.text!!.clear()

        tiUSUnitWeight.visibility = View.VISIBLE
        llUsUnitHeight.visibility = View.VISIBLE

        llDisplayBMIResult.visibility = View.GONE

    }

    private fun calculateMetricBMI(){
        if (validateMetricUnits()){
            val heightValue : Float = etMetricUnitHeight.text.toString().toFloat()/100
            val weightValue : Float = etMetricUnitWeight.text.toString().toFloat()

            val bmi = weightValue/(heightValue*heightValue)
            displayBMIResult(bmi)
        }else{
            Toast.makeText(this,
                "Please enter valid values",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateUSBMI(){
        if (validateUsUnits()) {
            val weightLbs: Float = etUSUnitWeight.text.toString().toFloat()

            val heightFt: Float = (etUSUnitHeight_Ft.text.toString()).toFloat()
            val heightIn: Float = (etUSUnitHeight_In.text.toString()).toFloat()

            val heightUS: Float = ((heightFt *12) + heightIn)

            val bmi = 703 * (weightLbs / (heightUS * heightUS))
            displayBMIResult(bmi)
        }else{
            Toast.makeText(this,
                "Please enter valid values",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun displayBMIResult(bmi: Float){
        var bmiLabel: String = ""
        var bmiDescription: String = ""

        if (bmi.compareTo(15f) <= 0){
            bmiLabel = "Severely underweight"
            bmiDescription = "Eat a lot more, eat right. Take better care of yourself!!!"
        }else if(bmi.compareTo(15f) > 0 && bmi.compareTo(18.5f) <= 0){
            bmiLabel = "Underweight"
            bmiDescription = "Eat a little more, eat right. Take better care of yourself!!"
        }else if(bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0){
            bmiLabel = "Normal"
            bmiDescription = "Good, You are alright!"
        }else if(bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0){
            bmiLabel = "Overweight"
            bmiDescription = "Eat a little less, eat right. Take a stroll or go for a run!!"
        }else if(bmi.compareTo(30f) > 0){
            bmiLabel = "Obese"
            bmiDescription = "Eat a lot less, eat right. Take a stroll or go for a run!!!"
        }

        llDisplayBMIResult.visibility = View.VISIBLE
        tvBMIComment.visibility = View.GONE

        /*tvYourBMI.visibility = View.VISIBLE
        tvBMIValue.visibility = View.VISIBLE
        tvBMIType.visibility = View.VISIBLE
        tvBMIComment.visibility = View.VISIBLE*/

        val bmiValue = BigDecimal(bmi.toDouble()).
        setScale(2, RoundingMode.HALF_EVEN).toString()

        tvBMIValue.text = bmiValue
        tvBMIType.text = bmiLabel
        tvBMIComment.text = bmiDescription

    }

    private fun validateMetricUnits(): Boolean{
        var isValid = true

        if (etMetricUnitWeight.text.toString().isEmpty())
            isValid = false
        else if (etMetricUnitHeight.text.toString().isEmpty())
            isValid = false

        return isValid
    }

    private fun validateUsUnits(): Boolean{
        var isValid = true

        if (etUSUnitWeight.text.toString().isEmpty()){
            isValid = false
        } else if (etUSUnitHeight_Ft.text.toString().isEmpty()){
            isValid = false
        }else if(etUSUnitHeight_In.text.toString().isEmpty()) {
            isValid = false
        }

        return isValid
    }

}
