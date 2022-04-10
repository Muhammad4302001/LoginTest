package com.neowise.logintest.Session

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.neowise.logintest.MainActivity
import com.neowise.logintest.MainMenu

class LoginPref {

    lateinit var pref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var con : Context

    var PRIVATEMODE : Int = 0


    constructor(con : Context){
        this.con = con
        pref = con.getSharedPreferences(PREF_PHONE, PRIVATEMODE)
        editor = pref.edit()
    }


    companion object{
        val PREF_PHONE = "Login_Preference"
        val IS_LOGIN = "isLoggedin"
        val KEY_PHONE = "user_Phone"
        val KEY_PASSWORD = "user_password"
        const val AUTHOR_KEY = "author_token"
    }


    fun creatlogginSession(user_Phone : String, user_password : String){
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(KEY_PHONE, user_Phone)
        editor.putString(KEY_PASSWORD,user_password)
        editor.commit()
    }

    fun checkLogin(){
        if (!this.isLoggedIn()){
            var i : Intent = Intent(con, MainMenu::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            con.startActivity(i)
        }
    }

    fun getUserDetails():HashMap<String, String>{
        var user: Map<String, String> = HashMap<String, String>()
        (user as HashMap).put(KEY_PHONE, pref.getString(AUTHOR_KEY, null)!!)
        return user
    }

    fun logoutUser(){
        editor.clear()
        editor.commit()
        var i : Intent = Intent(con, MainActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        con.startActivity(i)
    }

    fun isLoggedIn():Boolean{
        return pref.getBoolean(IS_LOGIN, false)
    }

    fun saveAuthToken(token: String) {
        val editor = pref.edit()
        editor.putString(AUTHOR_KEY, token)
        editor.apply()
    }

    fun fetchAuthToken(): String?{
        return pref.getString(AUTHOR_KEY,null)
    }

}