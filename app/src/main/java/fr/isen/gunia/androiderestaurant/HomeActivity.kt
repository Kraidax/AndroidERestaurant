package fr.isen.gunia.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import fr.isen.gunia.androiderestaurant.databinding.ActivityHomeBinding

private lateinit var binding: ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.BEntrees.setOnClickListener{
            val toast = Toast.makeText(applicationContext,"Vous êtes sur les entrées", Toast.LENGTH_SHORT)
            toast.show()
            startCategoryActivity(ItemType.STARTER)
        }
        binding.BPlats.setOnClickListener{
            val toast = Toast.makeText(applicationContext,"Vous êtes sur les plats", Toast.LENGTH_SHORT)
            toast.show()
            startCategoryActivity(ItemType.MAIN)
        }
        binding.BDesserts.setOnClickListener{
            val toast = Toast.makeText(applicationContext,"Vous êtes sur les desserts", Toast.LENGTH_SHORT)
            toast.show()
            startCategoryActivity(ItemType.DESSERT)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("HomeActivity","Destroyed")


    }

    private fun startCategoryActivity(item: ItemType) {
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra(CATEGORY, item)
        startActivity(intent)
    }

    companion object {
        const val CATEGORY = "category"
    }
}