package net.yuuzu.myreminder.presentation.add_edit_notes.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction


@Composable
fun TransparentHintTextField(
    text:String,
    hint:String,
    modifier:Modifier = Modifier,
    isHintVisible:Boolean = true,
    onValueChange:(String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine:Boolean = false,
    onFocusChange:(FocusState) -> Unit
){
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier
    ){
      BasicTextField(
          value = text,
          onValueChange = onValueChange,
          singleLine = singleLine,
          textStyle = textStyle,
          keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
          keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
          modifier = Modifier
              .fillMaxWidth()
              .onFocusChanged {
                  onFocusChange(it)
              }
         )
        if(isHintVisible){
            Text(text = hint, style = textStyle, color = Color.DarkGray)
        }
    }
}