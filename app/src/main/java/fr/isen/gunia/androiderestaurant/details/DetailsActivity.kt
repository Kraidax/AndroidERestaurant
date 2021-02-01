package fr.isen.gunia.androiderestaurant.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.gunia.androiderestaurant.R
import fr.isen.gunia.androiderestaurant.databinding.ActivityDetailsBinding
import fr.isen.gunia.androiderestaurant.network.Dish

class DetailActivity : AppCompatActivity() {
    companion object {
        const val DISH_EXTRA = "DISH_EXTRA"
    }

    lateinit var binding: ActivityDetailsBinding
    private var itemCount = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dish = intent.getSerializableExtra(DISH_EXTRA) as? Dish
        dish?.let {
            setupView(it)
        }
    }

    private fun setupView(dish: Dish) {
        binding.dishTitleTextView.text = dish.name
        binding.ingredientTextView.text = dish.ingredients.map { it.name }?.joinToString(", ")
        binding.viewPager.adapter = PhotoAdapter(this, dish.images)
        refreshShop(dish)
    }

    private fun refreshShop(dish: Dish) {
        val price = itemCount * dish.prices.first().price.toInt()
        binding.itemCount.text = itemCount.toString()
        binding.shopButton.text = "${getString(R.string.total)} $price"
    }
}