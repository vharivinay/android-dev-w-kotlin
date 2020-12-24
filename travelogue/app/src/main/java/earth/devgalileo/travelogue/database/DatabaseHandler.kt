package earth.devgalileo.travelogue.database

import android.content.Context
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import earth.devgalileo.travelogue.models.TravelLogueModel

class DatabaseHandler(context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "TravelLogueDatabase"
        private const val TABLE_TRAVEL_LOGUE = "TravelLogueTable"

        //All Columns
        private const val KEY_ID = "_id"
        private const val KEY_TITLE = "title"
        private const val KEY_IMAGE = "image"
        private const val KEY_DESCRIPTION = "description"
        private const val KEY_DATE = "date"
        private const val KEY_LOCATION = "location"
        private const val KEY_LATITUDE = "latitude"
        private const val KEY_LONGITUDE = "longitude"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TRAVEL_LOGUE_TABLE = ("CREATE TABLE " + TABLE_TRAVEL_LOGUE + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TITLE + " TEXT,"
                + KEY_IMAGE + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_LOCATION + " TEXT,"
                + KEY_LATITUDE + " TEXT,"
                + KEY_LONGITUDE + " TEXT)")
        db?.execSQL(CREATE_TRAVEL_LOGUE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_TRAVEL_LOGUE")
        onCreate(db)
    }

    fun addNewLogue(newLogue: TravelLogueModel): Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_TITLE, newLogue.title)
        contentValues.put(KEY_IMAGE, newLogue.image)
        contentValues.put(KEY_DESCRIPTION, newLogue.description)
        contentValues.put(KEY_DATE, newLogue.date)
        contentValues.put(KEY_LOCATION, newLogue.location)
        contentValues.put(KEY_LATITUDE, newLogue.latitude)
        contentValues.put(KEY_LONGITUDE, newLogue.longitude)

        //Insert To Table
        val result = db.insert(TABLE_TRAVEL_LOGUE,
            null,
            contentValues)
        db.close()
        return result
    }

    fun updateNewLogue(newLogue: TravelLogueModel): Int{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_TITLE, newLogue.title)
        contentValues.put(KEY_IMAGE, newLogue.image)
        contentValues.put(KEY_DESCRIPTION, newLogue.description)
        contentValues.put(KEY_DATE, newLogue.date)
        contentValues.put(KEY_LOCATION, newLogue.location)
        contentValues.put(KEY_LATITUDE, newLogue.latitude)
        contentValues.put(KEY_LONGITUDE, newLogue.longitude)

        //Update Table
        val success = db.update(TABLE_TRAVEL_LOGUE,
            contentValues,
            KEY_ID + "=" + newLogue.id,
            null)
        db.close()
        return success
    }

    fun deleteTravelLogue(newLogue: TravelLogueModel): Int{
        val db = this.writableDatabase
        val success = db.delete(TABLE_TRAVEL_LOGUE,
            KEY_ID + "=" + newLogue.id,
        null)
        db.close()
        return success
    }

    fun getTravelLogueList():ArrayList<TravelLogueModel>{
        val travelLogueList = ArrayList<TravelLogueModel>()
        val selectQuery = "SELECT * FROM $TABLE_TRAVEL_LOGUE"
        val db = this.readableDatabase

        try {
            val cursor: Cursor = db.rawQuery(selectQuery, null)
            if(cursor.moveToFirst()){
                do {
                    val place = TravelLogueModel(
                        cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                        cursor.getString(cursor.getColumnIndex(KEY_TITLE)),
                        cursor.getString(cursor.getColumnIndex(KEY_IMAGE)),
                        cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(KEY_DATE)),
                        cursor.getString(cursor.getColumnIndex(KEY_LOCATION)),
                        cursor.getDouble(cursor.getColumnIndex(KEY_LATITUDE)),
                        cursor.getDouble(cursor.getColumnIndex(KEY_LONGITUDE))
                    )
                    travelLogueList.add(place)
                }while (cursor.moveToNext())
            }
            cursor.close()
        }catch(e: SQLiteException){
            db.execSQL(selectQuery)
            return ArrayList()
        }

        return travelLogueList
    }

}