package app.fernando.covidrastreo.BaseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import app.fernando.covidrastreo.Utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {
    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_USUARIO);
        db.execSQL(Utilidades.CREAR_TABLA_CONTACTO);
        db.execSQL(Utilidades.CREAR_TABLA_USUARIOCONTACTO);
        db.execSQL(Utilidades.CREAR_TABLA_COPIA_USUARIOCONTACTO);
        db.execSQL(Utilidades.CREAR_TABLA_AJUSTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_CONTACTO);
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_USUARIO_CONTACTO);
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_COPIA_USUARIO_CONTACTO);
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_AJUSTE);
        onCreate(db);
    }
}
