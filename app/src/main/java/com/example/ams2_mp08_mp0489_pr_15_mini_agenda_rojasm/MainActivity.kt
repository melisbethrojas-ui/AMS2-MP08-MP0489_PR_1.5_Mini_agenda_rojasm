package com.example.ams2_mp08_mp0489_pr_15_mini_agenda_rojasm

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.LinearLayout
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(
            R.id.recyclerView
        )

        val contacts = ArrayList<Contact>()

        val nombres = listOf(
            "Carlos", "Laura", "David", "Marta", "Javier",
            "Lucía", "Daniel", "Paula", "Alejandro", "Sara"
        )

        val apellidos = listOf(
            "García", "Martínez", "López", "Sánchez", "Pérez",
            "Gómez", "Fernández", "Díaz", "Romero", "Torres"
        )

        for (i in 1..40) {

            val nombre = nombres.random()
            val apellido = apellidos.random()

            val telefono = Random.nextInt(100_000_000, 1_000_000_000)

            contacts.add(
                Contact(
                    "$nombre $apellido",
                    telefono.toString()
                )
            )
        }

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = ContactAdapter(contacts) { position ->
            Toast.makeText(
                this,
                "Contacto: ${position + 1}",
                Toast.LENGTH_SHORT
            ).show()
        }

        val btnAdd = findViewById<FloatingActionButton>(R.id.btnAdd)

        btnAdd.setOnClickListener {

            val nameInput = EditText(this)
            nameInput.hint = "Nombre"

            val phoneInput = EditText(this)
            phoneInput.hint = "Teléfono"

            val layout = LinearLayout(this)
            layout.orientation = LinearLayout.VERTICAL
            layout.setPadding(50, 20, 50, 10)

            layout.addView(nameInput)
            layout.addView(phoneInput)

            AlertDialog.Builder(this)
                .setTitle("Nuevo contacto")
                .setView(layout)
                .setPositiveButton("Guardar") { _, _ ->

                    val name = nameInput.text.toString()
                    val phone = phoneInput.text.toString()

                    if (name.isNotBlank() && phone.isNotBlank()) {
                        contacts.add(Contact(name, phone))
                        recyclerView.adapter?.notifyItemInserted(contacts.size - 1)
                    }
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }

    }
}
