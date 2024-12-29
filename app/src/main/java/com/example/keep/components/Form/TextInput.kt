package com.example.keep.components.Form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import com.example.keep.theme.AppColors

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    placeholder: String,
    onChange: (TextFieldValue) -> Unit,
    inputTextStyle: TextStyle = MaterialTheme.typography.bodyLarge.copy(color = AppColors.White),
    placeholderTextStyle: TextStyle = MaterialTheme.typography.bodyLarge.copy(color = AppColors.Gray40),
    backgroundColor: androidx.compose.ui.graphics.Color = AppColors.Transparent,
    cursorColor: androidx.compose.ui.graphics.Color = AppColors.Purple40
) {
    Box(
        modifier = modifier
            .background(backgroundColor)
            .fillMaxWidth()
    ) {
        BasicTextField(
            value = value,
            onValueChange = onChange,
            textStyle = inputTextStyle.copy(textAlign = TextAlign.Start),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions.Default,
            modifier = Modifier
                .fillMaxWidth(),
            cursorBrush = SolidColor(cursorColor)
        )

        if (value.text.isBlank()) {
            Text(
                text = placeholder,
                modifier = Modifier.align(Alignment.CenterStart),
                style = placeholderTextStyle
            )
        }
    }
}
