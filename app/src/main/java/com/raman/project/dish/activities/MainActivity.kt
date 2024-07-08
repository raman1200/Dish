package com.raman.project.dish.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.raman.project.dish.R
import com.raman.project.dish.databinding.ActivityMainBinding
import com.raman.project.dish.fragments.CookFragment
import com.raman.project.dish.fragments.DeviceFragment
import com.raman.project.dish.fragments.FavouritesFragment
import com.raman.project.dish.fragments.ManualFragment
import com.raman.project.dish.fragments.PreferencesFragment
import com.raman.project.dish.fragments.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var iconImageView:ImageView
    private lateinit var iconText: TextView
    private var icon by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initialize()
        setUpNavigation()


    }
    private fun initialize(){
        binding.apply {
            linearLayout.elevation = 4 * resources.displayMetrics.density
            replaceFragment(CookFragment())
            setVar(navBar.cookIcon, navBar.cookText, R.drawable.cook_fill)
            select()
        }
    }

    private fun setUpNavigation() {
        clickListeners()
    }
    private fun clickListeners(){
        binding.apply {
            navBar.cook.setOnClickListener{
                replaceFragment(CookFragment())
                unSelect()
                setVar(navBar.cookIcon, navBar.cookText, R.drawable.cook_fill)
                select()
            }
            navBar.favourite.setOnClickListener{
                replaceFragment(FavouritesFragment())
                unSelect()
                setVar(navBar.favouriteIcon, navBar.favouriteText, R.drawable.heart_fill)
                select()
            }
            navBar.manual.setOnClickListener{
                replaceFragment(ManualFragment())
                unSelect()
                setVar(navBar.manualIcon, navBar.manualText, R.drawable.chef_hat)
                select()
            }
            navBar.device.setOnClickListener{
                replaceFragment(DeviceFragment())
                unSelect()
                setVar(navBar.deviceIcon, navBar.deviceText, R.drawable.device)
                select()
            }
            navBar.preference.setOnClickListener{
                replaceFragment(PreferencesFragment())
                unSelect()
                setVar(navBar.preferenceIcon, navBar.preferenceText, R.drawable.profile)
                select()
            }
            navBar.setting.setOnClickListener{
                replaceFragment(SettingsFragment())
                unSelect()
                setVar(navBar.settingIcon, navBar.settingText, R.drawable.setting)
                select()
            }

        }
    }
    private fun setVar(iv:ImageView, tv:TextView, icon:Int){
        iconImageView = iv
        iconText = tv
        this.icon = icon
    }
    private fun select() {
            iconImageView.setImageResource(icon)
            iconImageView.setColorFilter(ContextCompat.getColor(this@MainActivity, R.color.orange))
            iconText.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.orange))

    }
    private fun unSelect() {
        icon = when(icon){
            R.drawable.cook_fill -> R.drawable.cook
            R.drawable.heart_fill -> R.drawable.heart
            else -> {icon}
        }
        iconImageView.setImageResource(icon)
        iconImageView.setColorFilter(ContextCompat.getColor(this@MainActivity, R.color.blue))
        iconText.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.blue))
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }
}