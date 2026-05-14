package com.example.tarefassegundoplano

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    private lateinit var edNota: EditText
    private lateinit var btnSalvar: Button
    lateinit var txtStatus: TextView
    lateinit var db: NoteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        edNota = findViewById<EditText>(R.id.edNota)
        btnSalvar = findViewById(R.id.btn_salvar)
        txtStatus = findViewById(R.id.txt_Status)
        db = Room.databaseBuilder(applicationContext,
            NoteDatabase::class.java,"notes_db").build()

        //Primeira forma de tarefas em segundo plano

        btnSalvar.setOnClickListener {
            val texto = edNota.text.toString()

            if (texto.isNotEmpty()){
                SaveNoteTask(this, texto).execute()
            }else{
                txtStatus.text = "Digite uma nota"
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}