package com.dicoding.consumerapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import com.dicoding.consumerapp.alarm.AlarmReceiver
import com.dicoding.consumerapp.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySettingBinding
    private lateinit var sharedPreference : SharedPreferences
    private lateinit var alarmReceiver: AlarmReceiver

    companion object{
        private const val PREFS_NAME = "user_pref"
        private const val DAILY = "daily"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = resources.getString(R.string.setting)

        sharedPreference = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        alarmReceiver = AlarmReceiver()

        binding.switchReminder.isChecked = sharedPreference.getBoolean(DAILY, false)

        binding.switchReminder.setOnCheckedChangeListener{_, onNotif ->
            if (onNotif){
                alarmReceiver.setDailyNotification(
                    this, AlarmReceiver.TYPE_DAY, getString(R.string.dailyNotify))
            }else{
                alarmReceiver.cancelDailyNotification(this)
            }

            val editor = sharedPreference.edit()
            editor.putBoolean(DAILY, onNotif)
            editor.apply()
        }

        binding.changeLanguage.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}