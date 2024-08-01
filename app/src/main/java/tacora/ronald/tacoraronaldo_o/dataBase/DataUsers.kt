package tacora.ronald.tacoraronaldo_o.dataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Patterns

class UserRepository(context: Context) {
    private val dbHelper = BiteDataBaseHelper(context)

    fun insertUser(name: String, apellido: String, username: String, password: String,telefono:String, email: String): Long {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(BiteDataBaseHelper.COLUMN_NAME, name)
            put(BiteDataBaseHelper.COLUMN_APELLIDO, name)
            put(BiteDataBaseHelper.COLUMN_USERNAME, username)
            put(BiteDataBaseHelper.COLUMN_PASSWORD, password)
            put(BiteDataBaseHelper.COLUMN_TELEFONO, telefono)
            put(BiteDataBaseHelper.COLUMN_EMAIL, email)
        }
        return db.insert(BiteDataBaseHelper.TABLE_NAME, null, values)
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}