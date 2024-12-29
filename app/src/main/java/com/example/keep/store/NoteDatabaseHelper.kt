package com.example.keep.store

import android.content.Context
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.keep.models.Note
import android.database.Cursor

const val DATABASE_NAME = "notes_db"
const val DATABASE_VERSION = 1
const val TABLE_NAME = "notes"
const val COLUMN_ID = "id"
const val COLUMN_TITLE = "title"
const val COLUMN_CONTENT = "content"
const val COLUMN_CREATED_DATE = "created_at"
const val COLUMN_UPDATED_DATE = "updated_at"
const val COLUMN_DEVICE_ID = "device_id"

class NoteDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_TITLE TEXT,
                $COLUMN_CONTENT TEXT,
                $COLUMN_CREATED_DATE TEXT,
                $COLUMN_UPDATED_DATE TEXT,
                $COLUMN_DEVICE_ID TEXT
            )
        """
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertNote(note: Note): Long {
        val db = writableDatabase
        val contentValues = android.content.ContentValues().apply {
            put(COLUMN_TITLE, note.title)
            put(COLUMN_CONTENT, note.content)
            put(COLUMN_CREATED_DATE, note.createdAt)
            put(COLUMN_UPDATED_DATE, note.updatedAt)
            put(COLUMN_DEVICE_ID, note.deviceId)
        }
        return db.insert(TABLE_NAME, null, contentValues)
    }

    fun getAllNotes(): List<Note> {
        val db = readableDatabase
        val cursor: Cursor = db.query(TABLE_NAME, null, null, null, null, null, null)
        val notes = mutableListOf<Note>()

        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(COLUMN_ID))
                val title = getString(getColumnIndexOrThrow(COLUMN_TITLE))
                val content = getString(getColumnIndexOrThrow(COLUMN_CONTENT))
                val createdAt = getString(getColumnIndexOrThrow(COLUMN_CREATED_DATE))
                val updatedAt = getString(getColumnIndexOrThrow(COLUMN_UPDATED_DATE))
                val deviceId = getString(getColumnIndexOrThrow(COLUMN_DEVICE_ID))

                val note = Note(
                    title = title,
                    content = content,
                    createdAt = createdAt,
                    updatedAt = updatedAt,
                    id = id.toString(),
                    deviceId = deviceId
                )
                notes.add(note)
            }
        }
        cursor.close()
        return notes
    }

    fun getById(id: String): Note? {
        val db = readableDatabase
        val cursor: Cursor = db.query(
            TABLE_NAME,
            null, // Select all columns
            "$COLUMN_ID = ?", // WHERE clause to filter by id
            arrayOf(id), // The ID value to filter by
            null, // No GROUP BY
            null, // No HAVING
            null // No ORDER BY
        )

        var note: Note? = null

        with(cursor) {
            if (moveToFirst()) {
                val id = getLong(getColumnIndexOrThrow(COLUMN_ID))
                val title = getString(getColumnIndexOrThrow(COLUMN_TITLE))
                val content = getString(getColumnIndexOrThrow(COLUMN_CONTENT))
                val createdAt = getString(getColumnIndexOrThrow(COLUMN_CREATED_DATE))
                val updatedAt = getString(getColumnIndexOrThrow(COLUMN_UPDATED_DATE))
                val deviceId = getString(getColumnIndexOrThrow(COLUMN_DEVICE_ID))

                note = Note(
                    title = title,
                    content = content,
                    createdAt = createdAt,
                    updatedAt = updatedAt,
                    id = id.toString(),
                    deviceId = deviceId
                )
            }
        }
        cursor.close()
        return note
    }


    fun updateNote(note: Note): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, note.title)
            put(COLUMN_CONTENT, note.content)
            put(COLUMN_UPDATED_DATE, note.updatedAt)
        }
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(note.id.toString())
        return db.update(TABLE_NAME, values, whereClause, whereArgs)
    }

    fun deleteNote(noteId: Long): Int {
        val db = writableDatabase
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(noteId.toString())
        return db.delete(TABLE_NAME, whereClause, whereArgs)
    }
}
