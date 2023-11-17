package br.edu.scl.ifsp.sdm.contactlist.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.viewbinding.ViewBinding
import br.edu.scl.ifsp.sdm.contactlist.R
import br.edu.scl.ifsp.sdm.contactlist.databinding.TileContactBinding
import br.edu.scl.ifsp.sdm.contactlist.model.Contact

class ContactAdapter(context: Context, private val contactList: MutableList<Contact>) :
    ArrayAdapter<Contact>(context, R.layout.tile_contact, contactList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val contact = contactList[position]

        var contactTileView = convertView
        if(contactTileView == null) {
            val tcb = TileContactBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false
            )
            contactTileView = tcb.root

            val tileContactHolder = TileContactHolder(tcb.nameTv, tcb.emailTv)
            contactTileView.tag = tileContactHolder
        }
        val holder = contactTileView.tag as TileContactHolder
        holder.nameTv.text = contact.name
        holder.emailTv.text = contact.email

        // Funciona com findViewById mas tem um desempenho inferior em relação ao uso de um holder
        // contactTileView.findViewById<TextView>(R.id.nameTv).text = contact.name
        // Chamar o findViewById fora do if, significa chamá-lo todas as vezes que o valor
        // de uma célula for trocado seja uma célula nova ou reciclada
        // Usando o holder a chamada das referências é feita apenas na criação da célula com o holder

        return contactTileView
    }

    private data class TileContactHolder(val nameTv: TextView, val emailTv: TextView)
}