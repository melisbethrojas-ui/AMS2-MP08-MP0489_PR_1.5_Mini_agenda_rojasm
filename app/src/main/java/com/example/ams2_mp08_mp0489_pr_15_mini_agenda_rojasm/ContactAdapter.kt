package com.example.ams2_mp08_mp0489_pr_15_mini_agenda_rojasm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(
    private val contacts: ArrayList<Contact>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    class ContactViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val name: TextView = view.findViewById(R.id.txtName)
        val phone: TextView = view.findViewById(R.id.txtPhone)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_item, parent, false)

        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ContactViewHolder,
        position: Int
    ) {
        val contact = contacts[position]

        holder.name.text = contact.name
        holder.phone.text = contact.phone

        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
}
