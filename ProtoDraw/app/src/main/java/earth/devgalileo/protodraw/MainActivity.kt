package earth.devgalileo.protodraw

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_brush_size.*
import java.io.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private var mImageButtonCurrentPaint: ImageButton? = null
    private lateinit var mProgressDialog: Dialog
    private lateinit var mFile: File
    private lateinit var mOutputStream: FileOutputStream
    private lateinit var mBitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawing_view.setSizeForBrush(5.toFloat())

        mImageButtonCurrentPaint = ll_paint_colors[1] as ImageButton
        mImageButtonCurrentPaint!!.setImageDrawable(
            ContextCompat.getDrawable(this, R.drawable.pallet_pressed)
        )

        ib_brush.setOnClickListener{
            showBrushSizeChooserDialog()
        }

        ib_gallery.setOnClickListener{
            if(isReadStorageAllowed()){
                //Run code to get the image from gallery
                val pickPhotoIntent = Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

                startActivityForResult(pickPhotoIntent, GALLERY)
            }else{
                requestStoragePermission()
            }
        }

        ib_undo.setOnClickListener{
            drawing_view.onClickUndo()
        }
        ib_redo.setOnClickListener{
            drawing_view.onClickRedo()
        }
        ib_clear.setOnClickListener{
            drawing_view.onClickClearAll()
            iv_background.setImageBitmap(null)

        }
        ib_save.setOnClickListener{
            if(isReadStorageAllowed()){
                saveDrawing()
            }else{
                requestStoragePermission()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == GALLERY){
                try {
                    if(data!!.data != null){
                        iv_background.visibility = View.VISIBLE
                        iv_background.setImageURI(data.data)
                    }else{
                        Toast.makeText(this,
                            "Error selecting Image Or Unsupported file format",
                            Toast.LENGTH_SHORT).show()
                    }
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    private fun showBrushSizeChooserDialog(){
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.dialog_brush_size)
        brushDialog.setTitle("Brush Size: ")

        val xxsmallbtn = brushDialog.ib_xxsmall_brush
        xxsmallbtn.setOnClickListener{
            drawing_view.setSizeForBrush(2.5.toFloat())
            brushDialog.dismiss()
        }

        val xsmallbtn = brushDialog.ib_xsmall_brush
        xsmallbtn.setOnClickListener{
            drawing_view.setSizeForBrush(5.toFloat())
            brushDialog.dismiss()
        }

        val smallbtn = brushDialog.ib_small_brush
        smallbtn.setOnClickListener{
            drawing_view.setSizeForBrush(10.toFloat())
            brushDialog.dismiss()
        }

        val mediumbtn = brushDialog.ib_medium_brush
        mediumbtn.setOnClickListener{
            drawing_view.setSizeForBrush(20.toFloat())
            brushDialog.dismiss()
        }

        val largebtn = brushDialog.ib_large_brush
        largebtn.setOnClickListener{
            drawing_view.setSizeForBrush(30.toFloat())
            brushDialog.dismiss()
        }

        brushDialog.show()

    }

    fun paintClicked(view: View) {
        if (view !== mImageButtonCurrentPaint) {
            val imageButton = view as ImageButton

            val colorTag = imageButton.tag.toString()
            drawing_view.setColor(colorTag)
            imageButton.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.pallet_pressed)
            )
            mImageButtonCurrentPaint!!.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.pallet_normal)
            )
            mImageButtonCurrentPaint = view
        }

    }

    private fun requestStoragePermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE).toString())){
                   Toast.makeText(this, "Need Storage Permission to add a Background",
                   Toast.LENGTH_SHORT).show()
        }
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE), STORAGE_PERMISSION_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == STORAGE_PERMISSION_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Storage Access Granted",
                    Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Storage Access Denied",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isReadStorageAllowed(): Boolean{
        val result = ContextCompat.checkSelfPermission(this,
        Manifest.permission.READ_EXTERNAL_STORAGE)

        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun getBitmapFromView(view: View): Bitmap {
        val returnedBitmap = Bitmap.createBitmap(view.width,
            view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if(bgDrawable != null){
            bgDrawable.draw(Canvas())
        }else{
            canvas.drawColor(Color.WHITE)
        }

        view.draw(canvas)
        mBitmap = returnedBitmap
        return mBitmap
    }

   private fun saveDrawing(){
       mBitmap = getBitmapFromView(drawing_view)
       if (mBitmap != null) {
           mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, mOutputStream)
           var path = Environment.getExternalStorageDirectory().absolutePath
           val picsFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
           val dir = File(("$path/ProtoDraw"))
           dir.mkdir()
           mFile = File(dir.toString()
                   + File.separator + "ProtoDraw_" + System.currentTimeMillis() / 1000 + ".jpg")
           try {
               showProgressDialog()
               mOutputStream = FileOutputStream(mFile)
               cancelProgressDialog()

           }catch (e: FileNotFoundException){
               e.printStackTrace()
               cancelProgressDialog()
           }
       }
   }



    private fun showProgressDialog(){
        mProgressDialog = Dialog(this@MainActivity)
        mProgressDialog.setContentView(R.layout.dialog_custom_progress)
        mProgressDialog.show()
    }

    private fun cancelProgressDialog(){
        mProgressDialog.dismiss()
    }

    private fun notifyContentResolver(path: String) {
        try {
            val values = ContentValues()
            values.put(MediaStore.Images.Media.DATA, path)
            val contentUri = MediaStore.Images.Media.INTERNAL_CONTENT_URI
            contentResolver.insert(contentUri, values)
        } catch (e: Exception) {
        }
    }

    companion object{
        private const val STORAGE_PERMISSION_CODE = 1
        private const val GALLERY = 2
    }
}



