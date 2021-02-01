package fr.isen.gunia.androiderestaurant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import fr.isen.gunia.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.gunia.androiderestaurant.network.Dish
import fr.isen.gunia.androiderestaurant.network.MenuResult
import fr.isen.gunia.androiderestaurant.network.NetworkConstant
import org.json.JSONObject
import com.android.volley.Request
import fr.isen.gunia.androiderestaurant.category.CategoryAdapter
import fr.isen.gunia.androiderestaurant.details.DetailActivity


enum class ItemType {
    STARTER, MAIN, DESSERT;

    companion object {
        fun categoryTitle(item: ItemType?): String {
            return when (item) {
                STARTER -> "Entrées"
                MAIN -> "Plats"
                DESSERT -> "Desserts"
                else -> ""
            }
        }
    }
}

private lateinit var bindind: ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(bindind.root)

        val selectedItem = intent.getSerializableExtra(HomeActivity.CATEGORY) as? ItemType
        bindind.categoryTitle.text = getCategoryTitle(selectedItem)

        loadList(listOf<Dish>())
        makeRequest(selectedItem)
        Log.d("lifecycle", "onCreate")
    }

    private fun makeRequest(selectedItem: ItemType?) {
        resultFromCache()?.let {
            parseResult(it, selectedItem)
        } ?: run {
            val queue = Volley.newRequestQueue(this)
            val url = NetworkConstant.BASE_URL + NetworkConstant.PATH_MENU
            val jsonData = JSONObject()

            jsonData.put(NetworkConstant.ID_SHOP, "1")

            val request = JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonData,
                { response ->
                    cacheResult(response.toString())
                    parseResult(response.toString(), selectedItem)

                },
                { error ->
                    error.message?.let {
                        Log.d("request", it)
                    } ?: run {
                        Log.d("request", error.toString())
                    }
                }
            )

            queue.add(request)
        }
    }

    private fun cacheResult(response: String) {
        val sharedPreferences = getSharedPreferences(USER_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(REQUEST_CACHE, response)
        editor.apply()
    }

    private fun resultFromCache(): String? {
        val sharedPreferences = getSharedPreferences(USER_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(REQUEST_CACHE, null)
    }

    private fun loadList(dishes: List<Dish>?) {
        dishes?.let {
            val adapter = CategoryAdapter(it) { dish ->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(DetailActivity.DISH_EXTRA, dish)
                startActivity(intent)
            }
            bindind.recyclerView.layoutManager = LinearLayoutManager(this)
            bindind.recyclerView.adapter = adapter
        }
    }

        private fun parseResult(response: String, selectedItem: ItemType?) {
            val menuResult = GsonBuilder().create().fromJson(response, MenuResult::class.java)
            val items = menuResult.data.firstOrNull { it.name == ItemType.categoryTitle(selectedItem) }
            loadList(items?.items)
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

    companion object {
        const val USER_PREFERENCES_NAME = "USER_PREFERENCES_NAME"
        const val REQUEST_CACHE = "REQUEST_CACHE"
    }

}