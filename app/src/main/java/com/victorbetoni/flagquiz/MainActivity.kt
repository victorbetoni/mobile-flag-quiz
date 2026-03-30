package com.victorbetoni.flagquiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.victorbetoni.flagquiz.data.Flag

class MainActivity : AppCompatActivity() {

    private val flags: Array<Flag> = arrayOf(
        Flag("Angola", R.drawable.ao),
        Flag("Argentina", R.drawable.ar),
        Flag("Holanda", R.drawable.bq),
        Flag("Brasil", R.drawable.br),
        Flag("Guatemala", R.drawable.gt),
        Flag("Chile", R.drawable.cl),
        Flag("China", R.drawable.cn),
        Flag("Marrocos", R.drawable.ma),
        Flag("Cuba", R.drawable.cu),
        Flag("Filipinas", R.drawable.ph),
        Flag("Jamaica", R.drawable.jm),
        Flag("Portugal", R.drawable.pt),
        Flag("Egito", R.drawable.eg),
        Flag("Estados Unidos", R.drawable.um),
    )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    fun start(v: View) {
        val intent = Intent(this, QuizActivity::class.java)

        val nome = findViewById<EditText>(R.id.editTextText).text.toString().trim()
        if(nome.trim().isEmpty()) {
            Toast.makeText(this, "Digite seu nome", Toast.LENGTH_SHORT).show()
            return
        }
        intent.putExtra("flags", flags)
        intent.putExtra("nome", nome)

        startActivity(intent)
    }
}