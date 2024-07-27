package tacora.ronald.tacoraronaldo_o.dataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BiteDataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "usuario.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "usuarios"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_APELLIDO = "apellido";
        const val COLUMN_USERNAME = "user_name"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_EMAIL = "email"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_NAME ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_NAME TEXT, "
                + "$COLUMN_APELLIDO TEXT, "
                + "$COLUMN_USERNAME TEXT, "
                + "$COLUMN_PASSWORD TEXT,"
                + "$COLUMN_EMAIL TEXT) "
                )
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}