import android.content.Context
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.compose.rememberNavController
import com.example.keep.components.CreateNewNote.CreateNoteBottomBar
import com.example.keep.components.CreateNewNote.Header
import com.example.keep.components.EditableNote
import com.example.keep.models.Note
import com.example.keep.store.NoteDatabaseHelper
import com.example.keep.utils.getAndroidDeviceId
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CreateNewNote(
    context: Context,
    navHostController: NavHostController,
    noteId: String? = null
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycle = lifecycleOwner.lifecycle


    Log.d("noteIdnoteId", noteId.toString())

    var updatedItem by remember { mutableStateOf<Note?>(null) }
    var title by remember { mutableStateOf(TextFieldValue("Test Title")) }
    var content by remember {
        mutableStateOf(
            TextFieldValue(
                "*.iml\n" +
                        ".gradle\n" +
                        "/local.properties\n" +
                        "/.idea/caches\n" +
                        "/.idea/libraries\n" +
                        "/.idea/modules.xml\n" +
                        "/.idea/workspace.xml\n" +
                        "/.idea/navEditor.xml\n" +
                        "/.idea/assetWizardSettings.xml\n" +
                        ".DS_Store\n" +
                        "/build\n" +
                        "/captures\n" +
                        ".externalNativeBuild\n" +
                        ".cxx\n" +
                        "local.properties\n" +
                        "node\n"
            )
        )
    }

    fun saveNote() {

        if (title.text.isNotBlank() || content.text.isNotBlank()) {
            val id = getAndroidDeviceId(context);

            val currentDate =
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
            val newNote = Note(
                title = title.text,
                content = content.text,
                createdAt = currentDate,
                updatedAt = currentDate,
                id = null,
                deviceId = id
            )

            val dbHelper = NoteDatabaseHelper(context)

            val noteId = dbHelper.insertNote(newNote)
            if (noteId != -1L) {
                Log.d("NoteSaved", "Note saved successfully with ID: $noteId")
            } else {
                Log.e("NoteSaveError", "Failed to save the note")
            }

        }
    }

    LaunchedEffect(noteId) {
        if (noteId != null) {
            val db = NoteDatabaseHelper(context)
            val item = db.getById(noteId)
            if (item !== null) {
                updatedItem = item
                title = TextFieldValue(item.title)
                content = TextFieldValue(item.content)
            }
        }
    }

    BackHandler {
        saveNote()
        navHostController.popBackStack()
        updatedItem = null
    }

    LaunchedEffect(lifecycle.currentState) {
        Log.d("lifecycle.currentStat", lifecycle.currentState.toString())
        if (lifecycle.currentState == Lifecycle.State.CREATED) {
            saveNote()
        }
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF0E0E0E))
    ) {
        Header(navHostController)

        EditableNote(
            title = title,
            content = content,
            onTitleChange = { title = it },
            onContextChange = { content = it },
            modifier = Modifier.weight(1f)
        )

        CreateNoteBottomBar(navHostController)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCreateNewNote() {
    CreateNewNote(context = LocalContext.current, navHostController = rememberNavController())
}
