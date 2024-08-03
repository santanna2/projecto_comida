package tacora.ronald.tacoraronaldo_o.dataBase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import tacora.ronald.tacoraronaldo_o.recyclers.PlatoModel
import tacora.ronald.tacoraronaldo_o.dataModel.DetallePedidoModel
import tacora.ronald.tacoraronaldo_o.dataModel.ModelRestaurant
import tacora.ronald.tacoraronaldo_o.dataModel.UsuarioModel

class BiteDataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "usuario.db"
        private const val DATABASE_VERSION = 1

        //tabla usuarios
        const val TABLE_NAME = "Usuarios"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_APELLIDO = "apellido";
        const val COLUMN_USERNAME = "user_name"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_TELEFONO = "telefono"
        const val COLUMN_EMAIL = "email"

        // Tabla Restaurant
        const val TABLE_RESTAURANT = "Restaurant"
        const val COLUMN_RESTAURANT_ID= "restaurant_id"
        const val COLUMN_RESTAURANT_NAME = "nombre"
        const val COLUMN_RESTAURANT_CATEGORY = "categoria"
        const val COLUMN_RESTAURANT_RANK = "calificacion"
        const val COLUMN_RESTAURANT_IMG = "imagen"
        const val COLUMN_RESTAURANT_ADRESS = "direccion"
        const val COLUMN_RESTAURANT_PHONE = "telefono"

        // Tabla Plato
        const val TABLE_PLATO = "Plato"
        const val COLUMN_PLATO_ID= "plato_id"
        const val COLUMN_PLATO_NAME = "name"
        const val COLUMN_PLATO_PRECIO = "precio"
        const val COLUMN_PLATO_CATEGORY = "categoria"
        const val COLUMN_PLATO_RESTAURANT = "restaurant"
        const val COLUMN_PLATO_ESTADO = "estado"
        const val COLUMN_PLATO_RANK = "rank"
        const val COLUMN_PLATO_IMG = "imagen"
        const val COLUMN_PLATO_DESCRIPCION = "descripcion"

        // Tabla Carta
        const val TABLE_CARTA = "Carta"
        const val COLUMN_CARTA_ID = "carta_id"
        const val COLUMN_CARTA_PLATO = "plato_id"
        const val COLUMN_CARTA_USUARIO = "usuario_id"
        const val COLUMN_CARTA_HORA= "fecha"

        // Tabla Carta_Pago
        const val TABLE_CARTA_PAGO = "pagos"
        const val COLUMN_CARTA_PAGO_CARTA = "carta_id"
        const val COLUMN_CARTA_PAGO_FECHA = "estado"

        // Tabla RestaurantPlato (Relación entre Restaurant y Plato)
        const val TABLE_RESTAURANT_PLATO = "RestaurantPlato"
        const val COLUMN_RESTAURANT_PLATO_RESTAURANT_ID = "restaurant_id"
        const val COLUMN_RESTAURANT_PLATO_PLATO_ID = "plato_id"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Crear tabla usuarios
        val createTableUsuarios = ("CREATE TABLE $TABLE_NAME ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_NAME TEXT, "
                + "$COLUMN_APELLIDO TEXT, "
                + "$COLUMN_TELEFONO TEXT, "
                + "$COLUMN_USERNAME TEXT, "
                + "$COLUMN_PASSWORD TEXT, "
                + "$COLUMN_EMAIL TEXT)"
                )

        // Crear tabla restaurant
        val createTableRestaurant = ("CREATE TABLE $TABLE_RESTAURANT ("
                + "$COLUMN_RESTAURANT_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_RESTAURANT_NAME TEXT, "
                + "$COLUMN_RESTAURANT_CATEGORY TEXT, "
                + "$COLUMN_RESTAURANT_RANK REAL, "
                + "$COLUMN_RESTAURANT_IMG TEXT, "
                + "$COLUMN_RESTAURANT_ADRESS TEXT, "
                + "$COLUMN_RESTAURANT_PHONE TEXT)"
                )

        // Crear tabla plato
        val createTablePlato = ("CREATE TABLE $TABLE_PLATO ("
                + "$COLUMN_PLATO_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_PLATO_NAME TEXT, "
                + "$COLUMN_PLATO_PRECIO REAL, "
                + "$COLUMN_PLATO_CATEGORY TEXT, "
                + "$COLUMN_PLATO_RESTAURANT TEXT, "
                + "$COLUMN_PLATO_ESTADO TEXT, "
                + "$COLUMN_PLATO_RANK REAL, "
                + "$COLUMN_PLATO_IMG TEXT, "
                + "$COLUMN_PLATO_DESCRIPCION TEXT)"
                )

        // Crear tabla carta
        val createTableCarta = ("CREATE TABLE $TABLE_CARTA ("
                + "$COLUMN_CARTA_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_CARTA_PLATO INTEGER, "
                + "$COLUMN_CARTA_USUARIO INTEGER, "
                + "$COLUMN_CARTA_HORA TEXT, "
                + "FOREIGN KEY($COLUMN_CARTA_PLATO) REFERENCES $TABLE_PLATO($COLUMN_PLATO_ID), "
                + "FOREIGN KEY($COLUMN_CARTA_USUARIO) REFERENCES $TABLE_NAME($COLUMN_ID))"
                )

        // Crear tabla carta_pago
        val createTableCartaPago = ("CREATE TABLE $TABLE_CARTA_PAGO ("
                + "$COLUMN_CARTA_PAGO_CARTA INTEGER, "
                + "$COLUMN_CARTA_PAGO_FECHA TEXT, "
                + "PRIMARY KEY($COLUMN_CARTA_PAGO_CARTA, $COLUMN_CARTA_PAGO_FECHA), "
                + "FOREIGN KEY($COLUMN_CARTA_PAGO_CARTA) REFERENCES $TABLE_CARTA($COLUMN_CARTA_ID))"
                )

        // Crear tabla RestaurantPlato
        val createTableRestaurantPlato = ("CREATE TABLE $TABLE_RESTAURANT_PLATO ("
                + "$COLUMN_RESTAURANT_PLATO_RESTAURANT_ID INTEGER, "
                + "$COLUMN_RESTAURANT_PLATO_PLATO_ID INTEGER, "
                + "PRIMARY KEY($COLUMN_RESTAURANT_PLATO_RESTAURANT_ID, $COLUMN_RESTAURANT_PLATO_PLATO_ID), "
                + "FOREIGN KEY($COLUMN_RESTAURANT_PLATO_RESTAURANT_ID) REFERENCES $TABLE_RESTAURANT($COLUMN_RESTAURANT_ID), "
                + "FOREIGN KEY($COLUMN_RESTAURANT_PLATO_PLATO_ID) REFERENCES $TABLE_PLATO($COLUMN_PLATO_ID))"
                )


        // Ejecutar sentencias SQL
        db.execSQL(createTableUsuarios)
        db.execSQL(createTableRestaurant)
        db.execSQL(createTablePlato)
        db.execSQL(createTableCarta)
        db.execSQL(createTableCartaPago)
        db.execSQL(createTableRestaurantPlato)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_RESTAURANT")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PLATO")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CARTA")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CARTA_PAGO")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_RESTAURANT_PLATO")
        onCreate(db)
    }

    @SuppressLint("SuspiciousIndentation")
    fun MostrarUsuario(id: Int): UsuarioModel? {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
        "SELECT* FROM usuarios WHERE id = ?",
            arrayOf(id.toString())
        )
            val usuario = if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val nombre = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val apellido = cursor.getString(cursor.getColumnIndexOrThrow("apellido"))
            val usuario = cursor.getString(cursor.getColumnIndexOrThrow("user_name"))
            val contrasenya = cursor.getString(cursor.getColumnIndexOrThrow("password"))
            val telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono"))
            val email = cursor.getString(cursor.getColumnIndexOrThrow("email"))

                UsuarioModel(id, nombre, apellido, usuario, contrasenya, telefono, email)
            } else {
            null
            }
        cursor.close()
        return usuario
    }
    fun MostrarUsuario2(id: Int): UsuarioModel? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = ?", arrayOf(id.toString()))
        return if (cursor.moveToFirst()) {
            val usuario = UsuarioModel(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                NombreUsuario = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                ApellidoUsuario = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_APELLIDO)),
                UsuarioUsuario = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME)),
                ContraseñaUsuario = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD)),
                telefonoUsuario = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TELEFONO)),
                EmailUsuario = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL))
            )
            cursor.close()
            usuario
        } else {
            cursor.close()
            null
        }
    }
    fun ObtenerRestaurants(): List<ModelRestaurant> {
        val restaurantList = mutableListOf<ModelRestaurant>()
        val db = this.readableDatabase
        val cursor = db.query(TABLE_RESTAURANT, null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_RESTAURANT_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RESTAURANT_NAME))
                val category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RESTAURANT_CATEGORY))
                val rank = cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_RESTAURANT_RANK))
                val img = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RESTAURANT_IMG))
                val address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RESTAURANT_ADRESS))
                val phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RESTAURANT_PHONE))

                restaurantList.add(ModelRestaurant(id, name, category, rank,img , address, phone))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return restaurantList
    }
    fun insertarPlato(name: String, precio: Double, category: String, restaurant: String, estado: String, rank: Double, img: String, descripcion: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_PLATO_NAME, name)
        contentValues.put(COLUMN_PLATO_PRECIO, precio)
        contentValues.put(COLUMN_PLATO_CATEGORY, category)
        contentValues.put(COLUMN_PLATO_RESTAURANT, restaurant)
        contentValues.put(COLUMN_PLATO_ESTADO, estado)
        contentValues.put(COLUMN_PLATO_RANK, rank)
        contentValues.put(COLUMN_PLATO_IMG, img)
        contentValues.put(COLUMN_PLATO_DESCRIPCION, descripcion)

        val result = db.insert(TABLE_PLATO, null, contentValues)
        return result != -1L
    }

    fun obtenerHistorialPedidos(usuarioId: Int): List<DetallePedidoModel> {
        val detalles = mutableListOf<DetallePedidoModel>()
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_CARTA WHERE $COLUMN_CARTA_USUARIO = ?",
            arrayOf(usuarioId.toString())
        )

        if (cursor.moveToFirst()) {
            do {
                val detalle = DetallePedidoModel(
                    carta_id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CARTA_ID)),
                    plato_id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CARTA_PLATO)),
                    usuario_id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CARTA_USUARIO))
                )
                detalles.add(detalle)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return detalles
    }
    fun ActualizarUsuario(id: Int, nombre: String, apellido: String, usuario: String, contraseña: String, telefono: String, email: String): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME, nombre) // Usar COLUMN_NAME en lugar de "Nombre"
        contentValues.put(COLUMN_APELLIDO, apellido) // Usar COLUMN_APELLIDO en lugar de "Apellido"
        contentValues.put(COLUMN_USERNAME, usuario) // Usar COLUMN_USERNAME en lugar de "Usuario"
        contentValues.put(COLUMN_PASSWORD, contraseña) // Usar COLUMN_PASSWORD en lugar de "Contraseña"
        contentValues.put(COLUMN_TELEFONO, telefono) // Usar COLUMN_TELEFONO en lugar de "Telefono"
        contentValues.put(COLUMN_EMAIL, email) // Usar COLUMN_EMAIL en lugar de "Email"
        return db.update(TABLE_NAME, contentValues, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }
    fun menu(id: Int, categoria: String): List<PlatoModel> {
        val db = this.readableDatabase
        val query = """
    SELECT p.$COLUMN_PLATO_ID, p.$COLUMN_PLATO_NAME, p.$COLUMN_PLATO_PRECIO, p.$COLUMN_PLATO_CATEGORY, p.$COLUMN_PLATO_RESTAURANT, p.$COLUMN_PLATO_ESTADO, p.$COLUMN_PLATO_RANK, p.$COLUMN_PLATO_IMG, p.$COLUMN_PLATO_DESCRIPCION
    FROM $TABLE_PLATO p
    INNER JOIN $TABLE_RESTAURANT r
    ON p.$COLUMN_PLATO_RESTAURANT = r.$COLUMN_RESTAURANT_NAME
    WHERE r.$COLUMN_RESTAURANT_ID = ?
    AND p.$COLUMN_PLATO_CATEGORY = ?
    """
        val cursor = db.rawQuery(query, arrayOf(id.toString(), categoria))
        val platos = mutableListOf<PlatoModel>()
        if (cursor.moveToFirst()) {
            do {
                val platoId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PLATO_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLATO_NAME))
                val precio = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PLATO_PRECIO))
                val category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLATO_CATEGORY))
                val restaurant = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLATO_RESTAURANT))
                val estado = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLATO_ESTADO))
                val rank = cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_PLATO_RANK))
                val img = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLATO_IMG))
                val descripcion = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLATO_DESCRIPCION))

                platos.add(PlatoModel(platoId, name, precio, category, restaurant, estado, rank, img, descripcion))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return platos
    }


}