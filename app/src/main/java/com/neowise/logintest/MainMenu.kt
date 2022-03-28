package com.neowise.logintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neowise.logintest.Session.LoginPref
import com.neowise.logintest.api.LoginService
import com.neowise.logintest.api.RetrofitFactory
import com.neowise.logintest.api.model.userInfo.UserInfoModel
import com.neowise.logintest.api.model.userInfo.UserInfoRequest
import com.neowise.logintest.databinding.ActivityMainMenuBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainMenu : AppCompatActivity() {


    private lateinit var binding: ActivityMainMenuBinding
    private lateinit var loginService: LoginService



    lateinit var session: LoginPref



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginService = RetrofitFactory.loginService


        val txtBonus = binding.txtBonus
        val txtBalance = binding.txtBalance






        session = LoginPref(this)


        session.checkLogin()

        var user: HashMap<String, String> = session.getUserDetails()
        var userToken = user.get(LoginPref.AUTHOR_KEY)


        val requestInfo = UserInfoRequest("getBalance", userToken.toString())

        loginService.fetchPosts(requestInfo).enqueue(object : Callback<UserInfoModel> {
                override fun onFailure(call: Call<UserInfoModel>, t: Throwable) {
                    // Error fetching posts
                }

                override fun onResponse(
                    call: Call<UserInfoModel>,
                    response: Response<UserInfoModel>
                ) {

                    val info = response.body()

                    txtBalance.text = "Balansizngiz: ${info?.userInfo?.balanse}"


                }
            })
    }

}