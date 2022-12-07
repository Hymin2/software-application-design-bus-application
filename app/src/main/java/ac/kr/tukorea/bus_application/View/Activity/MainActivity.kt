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
    private lateinit var db : AppDatabase


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
                btnRide.visibility = View.GONE
                btnGetoff.visibility = View.GONE

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
                btnGetoff.visibility = View.GONE
                Thread(Runnable {
                    var alarmRiding = db.alarmRidingDao()
                    var text = "설정된 알람이 없습니다."
                    var bool = alarmRiding.isEmptyAlarmRiding()
                    if(!bool){
                        text = alarmRiding.getAlarmRiding().route_name + "\n" + alarmRiding.getAlarmRiding().stop_name
                    }

                    runOnUiThread {
                        if(bool) {
                            textRide.text = text
                            btnRide.visibility = View.GONE
                        }
                        else{
                            textRide.text = text
                            btnRide.visibility = View.VISIBLE
                        }
                    }
                }).start()
            }
            radioBtnGetoff.setOnClickListener {
                rvBookmark.visibility = View.GONE
                textRide.visibility = View.GONE
                textGetoff.visibility = View.VISIBLE
                btnRide.visibility = View.GONE

                Thread(Runnable {
                    val alarmGettingOff = db.alarmGettingOffDao()
                    var bool = alarmGettingOff.isEmptyAlarmGettingOff()
                    var text = "설정된 알람이 없습니다."

                    if(!bool){
                        text = alarmGettingOff.getAlarmGetiingOff().route_name
                    }
                    runOnUiThread {
                        if(bool){
                            textGetoff.text = text
                            btnGetoff.visibility = View.GONE
                        }
                        else{
                            textGetoff.text = text
                            btnGetoff.visibility = View.VISIBLE
                        }

                    }
                }).start()
            }

            btnRide.setOnClickListener {
                Thread(Runnable {
                    var alarmRidingEntity = db.alarmRidingDao().getAlarmRiding()

                    db.alarmRidingDao().deleteAlarmRiding(alarmRidingEntity)

                    runOnUiThread {
                        textRide.text = "설정된 알람이 없습니다."
                        btnRide.visibility = View.GONE
                    }
                }).start()
            }

            btnGetoff.setOnClickListener {
                Thread(Runnable {
                    var alarmGettingOffEntity = db.alarmGettingOffDao().getAlarmGetiingOff()

                    db.alarmGettingOffDao().deleteAlarmGettingOff(alarmGettingOffEntity)

                runOnUiThread {
                    textGetoff.text = "설정된 알람이 없습니다."
                    btnGetoff.visibility = View.GONE
                    }
                }).start()
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



