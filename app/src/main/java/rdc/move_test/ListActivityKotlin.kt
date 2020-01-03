package rdc.move_test

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.android.Main
import kotlinx.coroutines.launch
import rdc.move_test.propertyapi.JSONPropertyAPI
import rdc.move_test.util.getTestList

class ListActivityKotlin : AppCompatActivity() {

    private val jsonPropertyApi = JSONPropertyAPI.getApi()

    private lateinit var recyclerView: RecyclerView
    private lateinit var propertyProgress: ProgressBar
    private lateinit var listAdapter: ListAdapterKotlin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_main)
        recyclerView = findViewById(R.id.list_recyclerview)
        propertyProgress= findViewById(R.id.progress)

        listAdapter = ListAdapterKotlin(this)
        //listAdapter.setListItems(getTestList())


        /*recyclerView.adapter = listAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        listAdapter.notifyDataSetChanged()*/

        getPropertyAPI(this)
    }


    // This uses the couroutine call to get the data from the backend.
    fun getPropertyAPI(context: Context) {
        GlobalScope.launch(Dispatchers.Main) {
            //progress.visibility = View.VISIBLE
            val propertiesResponse = jsonPropertyApi.getProperties().await()
            //progress.visibility = View.GONE
            if (propertiesResponse.isSuccessful) {
                // This code needs proper correction.
                propertiesResponse.body()?.properties?.let { listAdapter.setListItems(it) }
                recyclerView.adapter = listAdapter
                recyclerView.layoutManager = LinearLayoutManager(context)
                listAdapter.notifyDataSetChanged()
                //adapter.items = postsResponse.body() ?: listOf()
                listAdapter.notifyDataSetChanged()
            } else {
                //Toast.makeText(this@MainActivity, "Error ${postsResponse.code()}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
