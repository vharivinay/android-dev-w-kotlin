package earth.devgalileo.travelogue.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import earth.devgalileo.travelogue.R
import earth.devgalileo.travelogue.models.TravelLogueModel
import kotlinx.android.synthetic.main.activity_travel_logue_detail.*

class travelLogueDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel_logue_detail)

        var travelLogueDetailModel: TravelLogueModel? = null

        if (intent.hasExtra(MainActivity.EXTRA_PLACE_DETAILS)){
            travelLogueDetailModel = intent.getParcelableExtra(
                MainActivity.EXTRA_PLACE_DETAILS) as TravelLogueModel
        }

        if (travelLogueDetailModel != null){
            setSupportActionBar(toolbar_travel_logue_detail)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title = travelLogueDetailModel.title

            toolbar_travel_logue_detail.setNavigationOnClickListener {
                onBackPressed()
            }

            iv_place_image.setImageURI(Uri.parse(travelLogueDetailModel.image))
            tv_description.text = travelLogueDetailModel.description
            tv_location.text = travelLogueDetailModel.location

            btn_view_on_map.setOnClickListener{
                val intent = Intent(this, MapActivity::class.java)
                intent.putExtra(MainActivity.EXTRA_PLACE_DETAILS, travelLogueDetailModel)
                startActivity(intent)
            }
        }

    }
}
