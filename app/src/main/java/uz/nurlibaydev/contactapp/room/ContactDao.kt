package uz.nurlibaydev.contactapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDao {

    /**
     * CRUD operations
     * C - CREATE
     * R - READ
     * U - UPDATE
     * D - DELETE
     */

    @Query("SELECT * FROM contact")
    fun getMyAllContacts(): List<Contact>

    @Insert(onConflict = OnConflictStrategy.NONE)
    fun addContact(contact: Contact)

    @Query("DELETE FROM contact WHERE id = :id")
    fun deleteContact(id: Int)

    @Query("SELECT * FROM contact WHERE name LIKE :name")
    fun findContactWithName(name: String): List<Contact>
    // "Nur%"
    // Nurlibay
    // Nursultan

    @Update
    fun updateContact(contact: Contact)

    @Query("DELETE FROM contact")
    fun deleteAll()
}