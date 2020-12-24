package earth.devgalileo.travelogue.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import earth.devgalileo.travelogue.R
import earth.devgalileo.travelogue.adapters.TravelLogueAdapter
import earth.devgalileo.travelogue.database.DatabaseHandler
import earth.devgalileo.travelogue.models.TravelLogueModel
import earth.devgalileo.travelogue.utilities.SwipeToDeleteCallback
import earth.devgalileo.travelogue.utilities.SwipeToEditCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab_addNewLogue.setOnClickListener{
            val intent = Intent(this, AddNewLogue::class.java)
            startActivityForResult(intent, ADD_PLACE_ACTIVITY_REQUEST_CODE)
        }
        getTravelLogueListFromLocalDatabase()

    }

    private fun setupTravelLogueRecyclerView(
        travelLogueList: ArrayList<TravelLogueModel>){
        rv_travel_logue_list.layoutManager =
            LinearLayoutManager(this)
        rv_travel_logue_list.setHasFixedSize(true)

        val placesAdapter = TravelLogueAdapter(
            this,
            travelLogueList)

        rv_travel_logue_list.adapter = placesAdapter

        placesAdapter.setOnClickListener(object : TravelLogueAdapter.OnClickListener{
            override fun onClick(position: Int, model: TravelLogueModel) {
                val intent = Intent(this@MainActivity,
                    travelLogueDetailActivity::class.java)
                intent.putExtra(EXTRA_PLACE_DETAILS, model)
                startActivity(intent)
                }

            }
        )
        val editSwipeHandler = object : SwipeToEditCallback(this){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = rv_travel_logue_list.adapter as TravelLogueAdapter
                adapter.notifyEditItem(this@MainActivity, viewHolder.adapterPosition,
                    ADD_PLACE_ACTIVITY_REQUEST_CODE)
            }
        }
        val editItemTouchHelper = ItemTouchHelper(editSwipeHandler)
        editItemTouchHelper.attachToRecyclerView(rv_travel_logue_list)

        val deleteSwipeHandler = object : SwipeToDeleteCallback(this){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = rv_travel_logue_list.adapter as TravelLogueAdapter
                adapter.removeAt(viewHolder.adapterPosition)

                getTravelLogueListFromLocalDatabase()

            }
        }
        val deleteItemTouchHelper = ItemTouchHelper(deleteSwipeHandler)
        deleteItemTouchHelper.attachToRecyclerView(rv_travel_logue_list)

    }

    private fun getTravelLogueListFromLocalDatabase(){
        val dbHandler = DatabaseHandler(this)
        val getTravelLogueList: ArrayList<TravelLogueModel> = dbHandler.getTravelLogueList()

        if(getTravelLogueList.size > 0){
            rv_travel_logue_list.visibility = View.VISIBLE
            tv_no_records_found.visibility = View.GONE
            setupTravelLogueRecyclerView(getTravelLogueList)
        }else{
            rv_travel_logue_list.visibility = View.GONE
            tv_no_records_found.visibility = View.VISIBLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_PLACE_ACTIVITY_REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK){
                getTravelLogueListFromLocalDatabase()
            }else{
                Log.e("Activity", "Cancelled or BackPressed")
            }
        }
    }

    companion object{
        var ADD_PLACE_ACTIVITY_REQUEST_CODE = 1
        var EXTRA_PLACE_DETAILS = "extra_place_details"
    }
}
