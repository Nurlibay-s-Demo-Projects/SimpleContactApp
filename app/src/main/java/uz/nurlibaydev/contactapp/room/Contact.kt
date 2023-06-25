package uz.nurlibaydev.contactapp.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
@Entity(tableName = "contact")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo("name") var contactName: String,
    var surname: String? = null,
    @ColumnInfo(name = "phone") var phoneNumber: String,
    var state: Boolean = false
): Parcelable