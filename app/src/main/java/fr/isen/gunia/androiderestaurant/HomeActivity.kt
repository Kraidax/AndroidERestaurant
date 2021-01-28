package fr.isen.gunia.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }
        binding.BPlats.setOnClickListener{
            val toast = Toast.makeText(applicationContext,"Vous êtes sur les plats", Toast.LENGTH_SHORT)
            toast.show()
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }
        binding.BDesserts.setOnClickListener{
            val toast = Toast.makeText(applicationContext,"Vous êtes sur les desserts", Toast.LENGTH_SHORT)
            toast.show()
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }
    }
}