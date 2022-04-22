package com.neowise.logintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.neowise.logintest.Adapter.MetanAdapter
import com.neowise.logintest.Session.LoginPref
import com.neowise.logintest.api.LoginService
import com.neowise.logintest.api.RetrofitFactory
import com.neowise.logintest.api.model.Metan
import com.neowise.logintest.api.model.userInfo.UserInfo
import com.neowise.logintest.api.model.userInfo.UserInfoRequest
import com.neowise.logintest.fragment.HomeFragment
import com.neowise.logintest.fragment.InfoFragment
import com.neowise.logintest.fragment.SettingFragment
import com.neowise.logintest.request.MetanRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainMenu : AppCompatActivity() {


    private lateinit var loginService: LoginService
    private lateinit var metanAdapter: MetanAdapter
    private lateinit var metanModel: ArrayList<Metan>

    lateinit var session: LoginPref

    private val homeFragment = HomeFragment()
    private val settingFragment = SettingFragment()
    private val infoFragment = InfoFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

//        replaceFragment(homeFragment)


        session = LoginPref(this)
        session.checkLogin()

        loginService = RetrofitFactory.loginService


        val txtBonus = findViewById<TextView>(R.id.txt_bonus)
        val txtBalance = findViewById<TextView>(R.id.txt_balance)




//        val bottom_nav = binding.bottomNav
//
//        bottom_nav.setOnNavigationItemSelectedListener {
//            when(it.itemId){
//                R.id.ic_home -> replaceFragment(homeFragment)
//                R.id.ic_chek -> replaceFragment(infoFragment)
//                R.id.ic_bonus -> replaceFragment(settingFragment)
//            }
//            true
//        }


        val requestInfo = UserInfoRequest("getBalance", "${session.fetchAuthToken()}")

        loginService.fetchPosts(requestInfo).enqueue(object : Callback<UserInfo> {
            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                // Error fetching posts
            }


            override fun onResponse(
                call: Call<UserInfo>,
                response: Response<UserInfo>
            ) {

                val info = response.body()

                txtBalance.text = "Balansizngiz: ${info?.balanse}"


            }
        })

        val getrequest = MetanRequest("getMetan", "${session.fetchAuthToken()}")

        loginService.getMetan(getrequest).enqueue(object : Callback<Metan> {
            override fun onResponse(call: Call<Metan>, response: Response<Metan>) {
                val gMetan = response.body()

                addDataSet()
                initRecyclerView()


            }

            override fun onFailure(call: Call<Metan>, t: Throwable) {

            }
        })

    }


    private fun addDataSet(){

    }


    private fun initRecyclerView() {

        val recyclerMetan = findViewById<RecyclerView>(R.id.recyclerMetan)

        recyclerMetan.apply {
            layoutManager = LinearLayoutManager(this@MainMenu)
            metanAdapter = MetanAdapter()
            adapter = metanAdapter
        }
    }


}