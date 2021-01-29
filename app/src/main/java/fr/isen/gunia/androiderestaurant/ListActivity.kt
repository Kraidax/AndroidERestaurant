package fr.isen.gunia.androiderestaurant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.isen.gunia.androiderestaurant.databinding.ActivityListBinding

private lateinit var binding: ActivityListBinding

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_list)

        binding.titleList.text = intent.getStringExtra(HomeActivity.CATEGORY)
    }
}