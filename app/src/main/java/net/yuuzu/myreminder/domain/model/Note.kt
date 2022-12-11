package net.yuuzu.myreminder.domain.model

import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.room.Entity
import androidx.room.PrimaryKey
import net.yuuzu.myreminder.ui.theme.BabyBlue
import net.yuuzu.myreminder.ui.theme.RedOrange
import net.yuuzu.myreminder.ui.theme.RedPink
import net.yuuzu.myreminder.ui.theme.Violet
import java.lang.Exception

@Entity
data class Note(
    val title:String,
    val content:String,
    val timesStamp:String,
    val color:Int,
    @PrimaryKey val id: Int? = null
){
    companion object{
        val noteColors = listOf(RedOrange, LightGray, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message:String): Exception(message)