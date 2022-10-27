import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.example.broadcastreceiver.DashActivity
import com.example.broadcastreceiver.R

class SavePref {
    lateinit var pref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var con: Context
    var PRIVATEMODE: Int = 0

    constructor(con: Context) {
        this.con = con
        pref = con.getSharedPreferences(PREF_NAME, PRIVATEMODE)
        editor = pref.edit()
    }

    companion object {
        val PREF_NAME = "Data_preference"
        val IS_SAVE = "isLoggedin"
        val KEY_USERNAME = "username"
        val KEY_EMAIL = "email"
    }

    fun createSaveSession(username: String, email: String) {
        editor.putBoolean(IS_SAVE, true)
        editor.putString(KEY_USERNAME, username)
        editor.putString(KEY_EMAIL, email)
        editor.commit()
    }

    fun checkSave() {
        if (!this.isLoggedIn()) {
            var i: Intent = Intent(con, DashActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            con.startActivity(i)
        }
    }

    fun getUserDetails(): HashMap<String, String> {
        var user: Map<String, String> = HashMap<String, String>()
        (user as HashMap).put(KEY_USERNAME, pref.getString(KEY_USERNAME, null)!!)
        (user as HashMap).put(KEY_EMAIL, pref.getString(KEY_EMAIL, null)!!)
        return user
    }

    fun ExitUser() {
        editor.clear()
        editor.commit()
        var i: Intent = Intent(con, DashActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        con.startActivity(i)
    }

    fun isLoggedIn(): Boolean {
        return pref.getBoolean(IS_SAVE, false)
    }



}
