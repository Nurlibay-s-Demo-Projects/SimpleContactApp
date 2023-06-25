package uz.nurlibaydev.contactapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.nurlibaydev.contactapp.databinding.ItemContactBinding
import uz.nurlibaydev.contactapp.room.Contact

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    var contacts = mutableListOf<Contact>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ContactViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Contact) {
            binding.apply {
                tvContactName.text = item.contactName
                tvContactPhone.text = item.phoneNumber

                btnDelete.setOnClickListener {
                    deleteBtnClicked.invoke(item)
                }

                rootItem.setOnLongClickListener {
                    rootItem.alpha = 0.3f
                    true
                }

                btnEdit.setOnClickListener {
                    editBtnClicked.invoke(item)
                }
            }
        }
    }
    
    var deleteBtnClicked: (contact: Contact) -> Unit = {}
    fun deleteBtnClicked(block: (Contact) -> Unit) {
        deleteBtnClicked = block
    }

    var editBtnClicked: (contact: Contact) -> Unit = {}
    fun editBtnClicked(block: (Contact) -> Unit) {
        editBtnClicked = block
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = contacts.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contacts[position])
    }
}