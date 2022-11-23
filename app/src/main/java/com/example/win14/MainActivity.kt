package com.example.win14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.win14.adapter.QuizAdapter
import com.example.win14.databinding.ActivityMainBinding
import com.example.win14.model.FirstListModel
import com.example.win14.model.FirstQuestions
import com.example.win14.room.AppDatabase
import com.example.win14.service.QuizApi
import com.onesignal.OneSignal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : FragmentActivity() {

    private lateinit var adapter: QuizAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var appDatabase: AppDatabase
    lateinit var questionsList:List<FirstQuestions>
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId("714b9f14-381d-4fc4-a93c-28d480557381")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDatabase = AppDatabase.getDatabase(this)
        val api = QuizApi::class.java
        val response = Retrofit.Builder()
            .baseUrl("http://49.12.202.175/win14/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
        CoroutineScope(Dispatchers.IO).launch {
            val data = response.getFistList().awaitResponse()
            if (data.isSuccessful){
                questionsList=data.body()!!.firstListQuestions
                Log.i("LIST", data.body()!!.firstListQuestions.size.toString())
                launch(Dispatchers.Main){
                    for (i in questionsList){
                        appDatabase.questionsDao().insert(i)
                    }
                    adapter = QuizAdapter(this@MainActivity, questionsList)
                    viewPager = binding.viewPager
                    viewPager.adapter = adapter
                    binding.imageViewArrowRight.setOnClickListener {
                        viewPager.currentItem = viewPager.currentItem+1
                    }
                    binding.imageViewArrowLeft.setOnClickListener {
                        viewPager.currentItem = viewPager.currentItem-1
                    }
                }
            }
        }
    }
}