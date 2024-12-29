package com.example.keep.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.keep.components.Form.TextInput
import com.example.keep.theme.AppColors

@Composable
fun EditableNote(
    title: TextFieldValue,
    content: TextFieldValue,
    onTitleChange: (TextFieldValue) -> Unit,
    onContextChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {

        TextInput(
            inputTextStyle = TextStyle.Default.copy(color = AppColors.Pink80, fontSize = 30.sp),
            placeholderTextStyle = TextStyle.Default.copy(
                color = AppColors.Pink80,
                fontSize = 30.sp
            ),
            onChange = { newText -> onTitleChange(newText) },
            value = title,
            placeholder = "Title"
        )

        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            value = content,
            onValueChange = onContextChange,
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done // Allow "Done" for content field
            ),
//            maxLines = 10,
            minLines = 3,
            singleLine = false,
            keyboardActions = KeyboardActions(
                onDone = {
                    Log.d("Enter", "Insert new line")
                    val newText = content.text
                    val cursorPosition = content.selection.start
                    val updatedText =
                        newText.substring(0, cursorPosition) + "\n" + newText.substring(
                            cursorPosition
                        )
                    onContextChange(TextFieldValue(updatedText, TextRange(cursorPosition + 1)))
                }
            ),
            cursorBrush = SolidColor(AppColors.Pink80),
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
        )
    }
}
