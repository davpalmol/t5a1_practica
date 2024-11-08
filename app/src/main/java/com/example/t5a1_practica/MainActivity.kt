package com.example.t5a1_practica

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t5a1_practica.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var comidaAdapter: ComidaAdapter
    private lateinit var itemDecoration: DividerItemDecoration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        comidaAdapter = ComidaAdapter(getComidas()) { comida ->
            val intent = Intent(this, WebActivity::class.java)
            intent.putExtra("url", comida.wikipediaUrl)
            startActivity(intent)
        }

        itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = comidaAdapter
            addItemDecoration(itemDecoration)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getComidas():MutableList<Comida>{
        val comidas = mutableListOf<Comida>()

        val pizza = Comida (1, "Pizza", "https://cdn-icons-png.flaticon.com/128/1404/1404945.png", "https://en.wikipedia.org/wiki/Pizza")
        val paella = Comida (2, "Paella", "https://cdn-icons-png.flaticon.com/128/2182/2182813.png", "https://en.wikipedia.org/wiki/Paella")
        val kebab = Comida (3, "Kebab", "https://cdn-icons-png.flaticon.com/128/6978/6978201.png","https://en.wikipedia.org/wiki/Kebab")
        val hamburgesa = Comida (4, "Hamburguesa", "https://cdn-icons-png.flaticon.com/128/1522/1522452.png","https://en.wikipedia.org/wiki/Hamburguesa")
        val pasta = Comida (5, "Pasta", "https://cdn-icons-png.flaticon.com/128/3480/3480618.png","https://en.wikipedia.org/wiki/Pasta")

        comidas.add(pizza)
        comidas.add(paella)
        comidas.add(kebab)
        comidas.add(hamburgesa)
        comidas.add(pasta)

        return comidas

    }
}