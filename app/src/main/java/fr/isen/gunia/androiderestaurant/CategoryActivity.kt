package fr.isen.gunia.androiderestaurant

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.gunia.androiderestaurant.databinding.ActivityCategoryBinding

enum class ItemType {
    STARTER, MAIN, DESSERT
}

private lateinit var bindind: ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(bindind.root)

        val selectedItem = intent.getSerializableExtra(HomeActivity.CATEGORY) as? ItemType
        bindind.categoryTitle.text = getCategoryTitle(selectedItem)

        loadList()
        Log.d("lifecycle", "onCreate")
    }

    private fun loadList() {
        var entries = listOf<String>("salade", "boeuf", "glace")
        val adapter = CategoryAdapter(entries)
        bindind.recyclerView.layoutManager = LinearLayoutManager(this)
        bindind.recyclerView.adapter = adapter
    }


    private fun getCategoryTitle(item: ItemType?): String {
        return when(item) {
            ItemType.STARTER -> getString(R.string.entr_es)
            ItemType.MAIN -> getString(R.string.plats)
            ItemType.DESSERT -> getString(R.string.desserts)
            else -> ""
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle", "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("lifecycle", "onRestart")
    }

    override fun onDestroy() {
        Log.d("lifecycle", "onDestroy")
        super.onDestroy()
    }

}