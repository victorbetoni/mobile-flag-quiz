package com.victorbetoni.flagquiz

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.victorbetoni.flagquiz.data.Flag

class QuizActivity : AppCompatActivity() {

    lateinit var flags: List<Flag>;
    lateinit var currentFlag: Flag;
    var corretas = 0
    var contador = 0
    lateinit var nome: String;

    override fun onResume() {
        super.onResume()
        flags = FlagRegistry.flags.toList()
        corretas = 0
        contador = 0
        iniciarTentativa()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var bundle = intent.extras
        if (bundle != null) {
            flags = ((if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getSerializable("flags", Array<Flag>::class.java)
            } else {
                bundle.getSerializable("flags")
            }) as Array<Flag>).toList()

            nome = bundle.getString("nome").toString();

            iniciarTentativa()

        }

    }

    fun iniciarTentativa() {
        contador++
        val btnAdivinhar = findViewById<Button>(R.id.btnAdivinhar)
        val labelTentativa = findViewById<TextView>(R.id.labelTentativa)
        val labelResultado = findViewById<TextView>(R.id.textResultado)
        val editNm = findViewById<EditText>(R.id.editNmPais)

        editNm.setText("");
        labelTentativa.text = "Bandeira $contador de 5"

        val btnAvancar = findViewById<Button>(R.id.btnAvancar)
        btnAdivinhar.isEnabled = true

        btnAvancar.visibility = View.INVISIBLE
        labelResultado.visibility = View.INVISIBLE

        this.currentFlag = flags.random()
        flags = flags.filter { it.nome != currentFlag.nome }
        findViewById<ImageView>(R.id.imageView).setImageResource(this.currentFlag.drawableId)
    }

    fun enviarTentativa(v: View) {
        val nome = findViewById<EditText>(R.id.editNmPais).text.toString().trim()
        val textResultado = findViewById<TextView>(R.id.textResultado)

        if (nome.trim().isEmpty()) {
            Toast.makeText(this, "Digite algo!", Toast.LENGTH_SHORT).show();
            return;
        }

        textResultado.visibility = View.VISIBLE;
        if(nome.equals(this.currentFlag.nome, true)) {
            corretas++
            textResultado.setTextColor(Color.parseColor("#00FF00"))
            textResultado.text = "Correto!"
        } else {
            Toast.makeText(this, "Resposta correta: " + this.currentFlag.nome, Toast.LENGTH_SHORT).show();
            textResultado.setTextColor(Color.parseColor("#FF0000"))
            textResultado.text = "Incorreto!"

        }
        val btnAvancar = findViewById<Button>(R.id.btnAvancar)
        val btnAdivinhar = findViewById<Button>(R.id.btnAdivinhar)
        btnAvancar.visibility = View.VISIBLE
        btnAdivinhar.isEnabled = false
    }

    fun avancar(v: View) {
        if(contador == 5) {
            val intent = Intent(this, FinalActivity::class.java)
            intent.putExtra("nome", nome)
            intent.putExtra("pontos", corretas*20)
            startActivity(intent)
            return
        }
        iniciarTentativa()
    }

}