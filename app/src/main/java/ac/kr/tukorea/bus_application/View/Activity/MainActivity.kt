package ac.kr.tukorea.bus_application.View.Activity

import ac.kr.tukorea.bus_application.Data.DB.Database.AppDatabase
import ac.kr.tukorea.bus_application.View.Adapter.BookmarkAdapter
import ac.kr.tukorea.bus_application.databinding.ActivityMainBinding
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var BookmarkAdapter : BookmarkAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        var db = AppDatabase.getInstance(binding.root.context)

        binding.run {
            rvBookmark.apply {
                this.layoutManager = LinearLayoutManager(context)
            }

            Thread(Runnable {
                var items = db.bookmarkDao().getAllBookmark()
                runOnUiThread {
                    rvBookmark.adapter = BookmarkAdapter(items, db)
                }
            }).start()

            radioBtnBookmark.setOnClickListener {
                rvBookmark.visibility = View.VISIBLE
                textRide.visibility = View.GONE
                textGetoff.visibility = View.GONE

                Thread(Runnable {
                    var items = db.bookmarkDao().getAllBookmark()
                    runOnUiThread {
                        rvBookmark.adapter = BookmarkAdapter(items, db)
                    }
                }).start()
            }

            radioBtnRide.setOnClickListener {
                rvBookmark.visibility = View.GONE
                textRide.visibility = View.VISIBLE
                textGetoff.visibility = View.GONE
            }
            radioBtnGetoff.setOnClickListener {
                rvBookmark.visibility = View.GONE
                textRide.visibility = View.GONE
                textGetoff.visibility = View.VISIBLE
            }

            searchBarMain.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    var intent = Intent(this@MainActivity, SearchActivity::class.java)
                    intent.putExtra("query", query)
                    startActivity(intent)


                    return false
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    return false
                }

            })
        }
    }
}



