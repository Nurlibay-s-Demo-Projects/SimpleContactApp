package uz.nurlibaydev.contactapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDao {

    @Query("SELECT * FROM contact")
    fun getMyAllContacts(): List<Contact>

    @Insert
    fun addContact(contact: Contact)
}