package com.example.retrofit_mod8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofit_mod8.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = RvAdapter(this@MainActivity, arrayListOf())
        binding.rvMain.adapter = adapter
        binding.rvMain.setHasFixedSize(true)
        remoteGetdatamahasiswa()
    }
    private fun remoteGetdatamahasiswa() {
        APIClient.apiServices.getdatamahasiswa().enqueue(object :
            Callback<APIrespons> {
            override fun onResponse(call: Call<APIrespons>,
                                    response: Response<APIrespons>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    val data = apiResponse?.data
                    if (data != null) {
                        setDataToAdapter(data)
                    }
                }
            }
            override fun onFailure(call: Call<APIrespons>, t:
            Throwable) {
                Log.d("Error", t.stackTraceToString())
            }
        })
    }
    private fun setDataToAdapter(data: List<Mahasiswa>) {
        adapter.setData(data)
    }
}
