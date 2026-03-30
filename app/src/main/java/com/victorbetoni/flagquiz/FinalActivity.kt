package com.victorbetoni.flagquiz

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FinalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_final)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bundle = intent.extras
        if(bundle != null) {
            val nome = bundle.getString("nome").toString()
            val pontos = bundle.getInt("pontos").toString()
            val labelNome = findViewById<TextView>(R.id.labelNome)
            val labelPlacar = findViewById<TextView>(R.id.labelPlacar)
            labelNome.text = "Parabéns, $nome"
            labelPlacar.text = "Você fez $pontos pontos."
        }

    }

    fun reiniciar(v: View) {
        finish()
    }
}