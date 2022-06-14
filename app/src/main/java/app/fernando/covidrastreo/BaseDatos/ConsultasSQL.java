package app.fernando.covidrastreo.BaseDatos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import app.fernando.covidrastreo.Entidades.Ajuste;
import app.fernando.covidrastreo.Entidades.Contacto;
import app.fernando.covidrastreo.Entidades.Usuario;
import app.fernando.covidrastreo.Entidades.UsuarioContacto;
import app.fernando.covidrastreo.Utilidades.Utilidades;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ConsultasSQL {

    public ConsultasSQL() {
    }

    //****************************  REGISTRAR USUARIO ****************************************
    public static void registrarUsuarioSQL(Usuario usuario){
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String insert = "INSERT INTO " + Utilidades.TABLA_USUARIO + " (" + Utilidades.CAMPO_IDUSUARIO + ", " +
                Utilidades.CAMPO_NOMBRES + ", " + Utilidades.CAMPO_APELLIDOS + ", " + Utilidades.CAMPO_FECHANACIMIENTO + ", " +
                Utilidades.CAMPO_SEXO + ", " + Utilidades.CAMPO_CORREO + ", " + Utilidades.CAMPO_CONTRASENIA + ", " +
                Utilidades.CAMPO_DIRECCIONBT + ", " + Utilidades.CAMPO_ESTADOINFECCION + ", " + Utilidades.CAMPO_SEMINFUSUARIO +
                ")" + " VALUES (" + usuario.idUsuario + ", '" + usuario.nombres + "', '" + usuario.apellidos + "', '" +
                usuario.fechaNacimiento + "', " + usuario.sexo + ", '" + usuario.correo + "', '" + usuario.contrasenia +
                "', '" + usuario.direccionBT + "', " + usuario.estadoInfeccion + ", " + usuario.semInfUsuario + ")";
        db.execSQL(insert);
        db.close();
    }

    public static void registrarContactoSQL(Contacto contacto){
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String insert = "INSERT INTO " + Utilidades.TABLA_CONTACTO+ " (" + Utilidades.CAMPO_DIRECCIONBTCONTACTO +
                ", " + Utilidades.CAMPO_ESTADOINFECCIONCONTACTO + ", " + Utilidades.CAMPO_SEMINFCONTACTO + ", " +
                Utilidades.CAMPO_IDUSUARIOCONTACTO + ")" + " VALUES ('" + contacto.direccionBT + "', " +
                contacto.estadoInfeccion + ", " + contacto.semInfContacto + ", " + contacto.idUsuario + ")";
        db.execSQL(insert);
        db.close();
        conexion.close();
    }

    public static void registrarUsuarioContacto(Usuario usuario, Contacto contacto){
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String insert = "INSERT INTO " + Utilidades.TABLA_USUARIO_CONTACTO+ " (" + Utilidades.CAMPO_NOMBRE_USUARIO + ", " +
                Utilidades.CAMPO_APELLIDO_USUARIO + ", " + Utilidades.CAMPO_SEXO_USUARIO + ", " + Utilidades.CAMPO_DIRECCIONBT_USUARIO + ", " +
                Utilidades.CAMPO_ESTADOINFECCION_USUARIO + ", " + Utilidades.CAMPO_SEMANAINFECCION_USUARIO + ", " +
                Utilidades.CAMPO_DIRECCIONBT_CONTACTO + ", " + Utilidades.CAMPO_ESTADOINFECCION_CONTACTO + ", " + Utilidades.CAMPO_SEMANAINFECCION_CONTACTO +
                ")" + " VALUES ('" + usuario.nombres + "', '" + usuario.apellidos + "'," + usuario.sexo + ", " + "'" + usuario.direccionBT + "'," +
                usuario.estadoInfeccion + ", " + usuario.semInfUsuario + ", '" + contacto.direccionBT + "'," + contacto.estadoInfeccion + ", " +
                contacto.semInfContacto + ")";
        db.execSQL(insert);
        db.close();
        conexion.close();
    }

    public static void registrarUsuarioContactoDesdeWS(UsuarioContacto usuarioContacto){
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String insert = "INSERT INTO " + Utilidades.TABLA_USUARIO_CONTACTO+ " (" + Utilidades.CAMPO_NOMBRE_USUARIO + ", " +
                Utilidades.CAMPO_APELLIDO_USUARIO + ", " + Utilidades.CAMPO_SEXO_USUARIO + ", " + Utilidades.CAMPO_DIRECCIONBT_USUARIO + ", " +
                Utilidades.CAMPO_ESTADOINFECCION_USUARIO + ", " + Utilidades.CAMPO_SEMANAINFECCION_USUARIO + ", " + Utilidades.CAMPO_DIRECCIONBT_CONTACTO +
                ", " + Utilidades.CAMPO_ESTADOINFECCION_CONTACTO + ", " + Utilidades.CAMPO_SEMANAINFECCION_CONTACTO + ")" +
                " VALUES ('" + usuarioContacto.nombreUsuario + "', '" + usuarioContacto.apellidoUsuario + "'," + usuarioContacto.sexoUsuario + ", " + "'" +
                usuarioContacto.direccionBTUsuario + "'," + usuarioContacto.estadoInfeccionUsuario + ", " + usuarioContacto.semanaInfeccionUsuario + ", '" +
                usuarioContacto.direccionBTContacto + "'," + usuarioContacto.estadoInfeccionContacto + ", " + usuarioContacto.semanaInfeccionContacto + ")";
        db.execSQL(insert);
        db.close();
        conexion.close();
    }

    public static void registrarUsuarioContactoCopia(UsuarioContacto usuarioContacto){
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String insert = "INSERT INTO " + Utilidades.TABLA_COPIA_USUARIO_CONTACTO + " (" + Utilidades.CAMPO_COPIA_NOMBRE_USUARIO + ", " +
                Utilidades.CAMPO_COPIA_APELLIDO_USUARIO + ", " + Utilidades.CAMPO_COPIA_SEXO_USUARIO + ", " + Utilidades.CAMPO_COPIA_DIRECCIONBT_USUARIO + ", " +
                Utilidades.CAMPO_COPIA_ESTADOINFECCION_USUARIO + ", " + Utilidades.CAMPO_COPIA_SEMANAINFECCION_USUARIO + ", " +
                Utilidades.CAMPO_COPIA_DIRECCIONBT_CONTACTO + ", " + Utilidades.CAMPO_COPIA_ESTADOINFECCION_CONTACTO + ", " +
                Utilidades.CAMPO_COPIA_SEMANAINFECCION_CONTACTO + ")" + " VALUES ('" + usuarioContacto.nombreUsuario + "', '" + usuarioContacto.apellidoUsuario +
                "'," + usuarioContacto.sexoUsuario + ", " + "'" + usuarioContacto.direccionBTUsuario + "'," + usuarioContacto.estadoInfeccionUsuario + ", " +
                usuarioContacto.semanaInfeccionUsuario + ", '" + usuarioContacto.direccionBTContacto + "'," + usuarioContacto.estadoInfeccionContacto + ", " +
                usuarioContacto.semanaInfeccionContacto + ")";
        db.execSQL(insert);
        db.close();
        conexion.close();
    }

    //****************************  RETORNAR USUARIO ****************************************
    public static Usuario retornaUsuario(){
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        SQLiteDatabase db = conexion.getReadableDatabase();
        String[] parametros = {"1"};
        Usuario usuario = new Usuario();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_USUARIO + " WHERE " +
                    Utilidades.CAMPO_IDUSUARIO + " = ?", parametros);
            cursor.moveToFirst();
            usuario.setIdUsuario(cursor.getInt(0));
            usuario.setNombres(cursor.getString(1));
            usuario.setApellidos(cursor.getString(2));
            usuario.setFechaNacimiento(cursor.getString(3));
            usuario.setSexo(cursor.getInt(4));
            usuario.setCorreo(cursor.getString(5));
            usuario.setContrasenia(cursor.getString(6));
            usuario.setDireccionBT(cursor.getString(7));
            usuario.setEstadoInfeccion(cursor.getInt(8));
            usuario.setSemInfUsuario(cursor.getInt(9));
            db.close();
        }catch (Exception e){

        }
        conexion.close();
        return usuario;
    }

    public static boolean verificaUsuario(){
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        boolean registrado = false;
        SQLiteDatabase db = conexion.getReadableDatabase();
        String[] parametros = {"1"};
        try {
            Cursor cursor = db.rawQuery("SELECT " + Utilidades.CAMPO_IDUSUARIO + " FROM " +
                    Utilidades.TABLA_USUARIO + " WHERE " + Utilidades.CAMPO_IDUSUARIO + " = ?", parametros);
            cursor.moveToFirst();
            if(cursor.getInt(0) == 1)
                registrado = true;
            db.close();
        }catch (Exception e){

        }
        conexion.close();
        return registrado;
    }

    public static int retornaNumeroinfectadosSemana(int semana){
        int numeroInfectados = 0;
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        boolean registrado = false;
        SQLiteDatabase db = conexion.getReadableDatabase();
        String[] parametros = {semana + ""};
        try {
            Cursor cursor = db.rawQuery("SELECT COUNT(*)" + " FROM " + Utilidades.TABLA_COPIA_USUARIO_CONTACTO + " WHERE " +
                    Utilidades.CAMPO_COPIA_ESTADOINFECCION_CONTACTO + " = 1 AND " + Utilidades.CAMPO_COPIA_SEMANAINFECCION_CONTACTO + " = ?", parametros);
            cursor.moveToFirst();
            numeroInfectados = cursor.getInt(0);
            db.close();
        }catch (Exception e){

        }
        conexion.close();
        return numeroInfectados;
    }

    public static List<Contacto> obtenerlistaContactosBD(List<Contacto> listaContactos){
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        SQLiteDatabase db = conexion.getReadableDatabase();
        String[] parametros = {"1"};
        Contacto contacto = new Contacto();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_CONTACTO + " WHERE " + Utilidades.CAMPO_IDUSUARIOCONTACTO + " = ?", parametros);
            while (cursor.moveToNext()){
                Toast.makeText(getApplicationContext(), "Dirección Bluetooth: " + cursor.getString(1), Toast.LENGTH_SHORT).show();
                contacto.setIdContacto(cursor.getInt(0));
                contacto.setDireccionBT(cursor.getString(1));
                contacto.setEstadoInfeccion(cursor.getInt(2));
                contacto.setSemInfContacto(cursor.getInt(3));
                contacto.setIdUsuario(cursor.getInt(4));
                listaContactos.add(contacto);
            }
            db.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error en datos", Toast.LENGTH_SHORT).show();
        }
        conexion.close();
        return listaContactos;
    }

    public static List<UsuarioContacto> obtenerListaUsuarioContactoBD(List<UsuarioContacto> listaUsuarioContacto, Usuario usuario){
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        SQLiteDatabase db = conexion.getReadableDatabase();
        String[] parametros = {usuario.getDireccionBT()};
        UsuarioContacto usuarioContacto = new UsuarioContacto();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_USUARIO_CONTACTO + " WHERE " + Utilidades.CAMPO_DIRECCIONBT_USUARIO +
                    " = ?", parametros);
            while (cursor.moveToNext()){
                usuarioContacto.setIdUsuarioContacto(cursor.getInt(0));
                usuarioContacto.setNombreUsuario(cursor.getString(1));
                usuarioContacto.setApellidoUsuario(cursor.getString(2));
                usuarioContacto.setSexoUsuario(cursor.getInt(3));
                usuarioContacto.setDireccionBTUsuario(cursor.getString(4));
                usuarioContacto.setEstadoInfeccionUsuario(cursor.getInt(5));
                usuarioContacto.setSemanaInfeccionUsuario(cursor.getInt(6));
                usuarioContacto.setDireccionBTContacto(cursor.getString(7));
                usuarioContacto.setEstadoInfeccionContacto(cursor.getInt(8));
                usuarioContacto.setSemanaInfeccionContacto(cursor.getInt(9));
                listaUsuarioContacto.add(usuarioContacto);
            }
            db.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error en datos", Toast.LENGTH_SHORT).show();
        }
        conexion.close();
        return listaUsuarioContacto;
    }

    public static boolean buscarDireccionBD(String direccionBD){
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        boolean datoRepetido = false;
        SQLiteDatabase db = conexion.getReadableDatabase();
        String[] parametros = {direccionBD};
        try {
            Cursor cursor = db.rawQuery("SELECT " + Utilidades.CAMPO_DIRECCIONBTCONTACTO + " FROM " +
                    Utilidades.TABLA_CONTACTO + " WHERE " + Utilidades.CAMPO_DIRECCIONBTCONTACTO + " = ?", parametros);
            cursor.moveToFirst();
            if(cursor.getString(0).equals(direccionBD))
                datoRepetido = true;

            db.close();
        }catch (Exception e){

        }
        conexion.close();
        return datoRepetido;
    }

    public static boolean direccionBTContactoRepetido(UsuarioContacto usuarioContacto){
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        boolean datoRepetido = false;
        SQLiteDatabase db = conexion.getReadableDatabase();
        String[] parametros = {usuarioContacto.direccionBTContacto};
        try {
            Cursor cursor = db.rawQuery("SELECT " + Utilidades.CAMPO_DIRECCIONBT_CONTACTO + " FROM " +
                    Utilidades.TABLA_USUARIO_CONTACTO + " WHERE " + Utilidades.CAMPO_DIRECCIONBT_CONTACTO + " = ?", parametros);
            cursor.moveToFirst();

            if(cursor.getString(0).equals(usuarioContacto.direccionBTContacto))
                datoRepetido = true;

            db.close();
        }catch (Exception e){

        }
        conexion.close();
        return datoRepetido;
    }

    public static boolean contactoRepetido(UsuarioContacto usuarioContacto){
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        boolean datoRepetido = false;
        SQLiteDatabase db = conexion.getReadableDatabase();
        String[] parametros = {usuarioContacto.direccionBTContacto};
        try {
            Cursor cursor = db.rawQuery("SELECT " + Utilidades.CAMPO_COPIA_DIRECCIONBT_CONTACTO + " FROM " +
                    Utilidades.TABLA_COPIA_USUARIO_CONTACTO + " WHERE " + Utilidades.CAMPO_COPIA_DIRECCIONBT_CONTACTO + " = ?", parametros);
            cursor.moveToFirst();
            if(cursor.getString(0).equals(usuarioContacto.direccionBTContacto))
                datoRepetido = true;
            db.close();
        }catch (Exception e){

        }
        conexion.close();
        return datoRepetido;
    }

    //****************************  ACTUALIZAR ****************************************
    public static void actualizarTablaUsuarioContacto(UsuarioContacto usuarioContacto){
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String update = "UPDATE " + Utilidades.TABLA_USUARIO_CONTACTO + " SET " + Utilidades.CAMPO_ESTADOINFECCION_CONTACTO + " = " +
                usuarioContacto.estadoInfeccionContacto + ", " + Utilidades.CAMPO_SEMANAINFECCION_CONTACTO + " = " + usuarioContacto.semanaInfeccionContacto +
                " WHERE " + Utilidades.CAMPO_DIRECCIONBT_CONTACTO + " = '" + usuarioContacto.direccionBTContacto + "'";
        db.execSQL(update);
        db.close();
        conexion.close();
    }

    public static void actualizarTablaUsuarioContactoCopia(UsuarioContacto usuarioContacto){
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String update = "UPDATE " + Utilidades.TABLA_COPIA_USUARIO_CONTACTO + " SET " + Utilidades.CAMPO_COPIA_ESTADOINFECCION_CONTACTO + " = " +
                usuarioContacto.estadoInfeccionContacto + ", " + Utilidades.CAMPO_COPIA_SEMANAINFECCION_CONTACTO + " = " +
                usuarioContacto.semanaInfeccionContacto + " WHERE " + Utilidades.CAMPO_COPIA_DIRECCIONBT_CONTACTO + " = '" +
                usuarioContacto.direccionBTContacto + "'";
        db.execSQL(update);
        db.close();
        conexion.close();
    }

    public static void actualizarUsuarioEstadoPositivoCovid(Usuario usuario){
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String update = "UPDATE " + Utilidades.TABLA_USUARIO + " SET " + Utilidades.CAMPO_ESTADOINFECCION + " = " + usuario.estadoInfeccion + ", " +
                Utilidades.CAMPO_SEMINFUSUARIO + " = " + usuario.semInfUsuario + " WHERE " + Utilidades.CAMPO_IDUSUARIO + " = 1";
        db.execSQL(update);
        db.close();
        conexion.close();
    }

    public static void actualizarUsuarioContactoEstadoPositivoCovid(Usuario usuario){
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String update = "UPDATE " + Utilidades.TABLA_USUARIO_CONTACTO + " SET " + Utilidades.CAMPO_ESTADOINFECCION_USUARIO +
                "=" + usuario.estadoInfeccion + ", " + Utilidades.CAMPO_SEMANAINFECCION_USUARIO + " = " + usuario.semInfUsuario + " WHERE " +
                Utilidades.CAMPO_DIRECCIONBT_USUARIO + "='" + usuario.direccionBT + "'";
        db.execSQL(update);
        db.close();
        conexion.close();
    }

    //*************************************  CONSULTAS SQL TABLA AJUSTES ************************************************
    public static void registrarAjustes(Ajuste ajuste){
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String insert = "INSERT INTO " + Utilidades.TABLA_AJUSTE + " (" + Utilidades.CAMPO_ESCANEOAUTOMATICO + ", " +
                Utilidades.CAMPO_HORAACTUALIZACION + ", " + Utilidades.CAMPO_MINACTUALIZACION + ", " + Utilidades.CAMPO_BOTONCOVID + ", " +
                Utilidades.CAMPO_SEMANABOTONCOVID + ", " + Utilidades.CAMPO_IDUSUARIO_AJUSTE + ")" + " VALUES (" + ajuste.escaneoAutomatico + ", " +
                ajuste.horaActualizacion + ", " + ajuste.minActualizacion + ", " + ajuste.botonCovid + ", " + ajuste.semanaBotonCovid + ", "
                + ajuste.idUsuario + ")";
        db.execSQL(insert);
        db.close();
        conexion.close();
    }

    public static void actualizarHoraAjustes(Ajuste ajuste){
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String update = "UPDATE " + Utilidades.TABLA_AJUSTE + " SET " + Utilidades.CAMPO_HORAACTUALIZACION + " = " + ajuste.horaActualizacion + ", " +
                Utilidades.CAMPO_MINACTUALIZACION + " = " + ajuste.minActualizacion + " WHERE " + Utilidades.CAMPO_IDUSUARIO_AJUSTE + "=" + ajuste.idUsuario;
        db.execSQL(update);
        db.close();
        conexion.close();
    }

    public static Ajuste consultarAjuste(){
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        SQLiteDatabase db = conexion.getReadableDatabase();
        String[] parametros = {"1"};
        Ajuste ajuste = new Ajuste();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_AJUSTE + " WHERE " +
                    Utilidades.CAMPO_IDUSUARIO_AJUSTE + " = ?", parametros);
            cursor.moveToFirst();
            ajuste.setIdAjuste(cursor.getInt(0));
            ajuste.setEscaneoAutomatico(cursor.getInt(1));
            ajuste.setHoraActualizacion(cursor.getInt(2));
            ajuste.setMinActualizacion(cursor.getInt(3));
            ajuste.setBotonCovid(cursor.getInt(4));
            ajuste.setSemanaBotonCovid(cursor.getInt(5));
            ajuste.setIdUsuario(cursor.getInt(6));
            db.close();
        }catch (Exception e){

        }
        conexion.close();
        return ajuste;
    }

    public static boolean escaneoAutomatico(){
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        SQLiteDatabase db = conexion.getReadableDatabase();
        String[] parametros = {"1"};
        int escaneoAutomatico = 0;
        boolean bandera = false;
        try {
            Cursor cursor = db.rawQuery("SELECT " + Utilidades.CAMPO_ESCANEOAUTOMATICO + " FROM " + Utilidades.TABLA_AJUSTE + " WHERE " +
                    Utilidades.CAMPO_IDUSUARIO_AJUSTE + " = ?", parametros);
            cursor.moveToFirst();
            escaneoAutomatico = cursor.getInt(0);
            if(escaneoAutomatico == 1)
                bandera = true;
            db.close();
        }catch (Exception e){

        }
        conexion.close();
        return bandera;
    }

    public static void actualizarEscaneoAjustes(boolean estado){
        int escaneoAutomatico = 0;
        if(estado)
            escaneoAutomatico = 1;
        else
            escaneoAutomatico = 0;
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String update = "UPDATE " + Utilidades.TABLA_AJUSTE + " SET " + Utilidades.CAMPO_ESCANEOAUTOMATICO+ " = " + escaneoAutomatico + " WHERE " +
                Utilidades.CAMPO_IDUSUARIO_AJUSTE + " = 1";
        db.execSQL(update);
        db.close();
        conexion.close();
    }

    public static boolean retornaEscaneoAutomático(){
        boolean estadoEscaneoAutomatico = false;
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        SQLiteDatabase db = conexion.getReadableDatabase();
        String[] parametros = {"1"};
        try {
            Cursor cursor = db.rawQuery("SELECT " + Utilidades.CAMPO_ESCANEOAUTOMATICO + " FROM " + Utilidades.TABLA_AJUSTE + " WHERE " +
                    Utilidades.CAMPO_IDUSUARIO_AJUSTE + " = ?", parametros);
            cursor.moveToFirst();
            if(cursor.getInt(0) == 1){
                estadoEscaneoAutomatico = true;
            }
            db.close();
        }catch (Exception e){

        }
        conexion.close();
        return estadoEscaneoAutomatico;
    }

    public static boolean retornaEstadoBotonCovid(){
        boolean estadoBotonCovid = false;
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        SQLiteDatabase db = conexion.getReadableDatabase();
        String[] parametros = {"1"};
        try {
            Cursor cursor = db.rawQuery("SELECT " + Utilidades.CAMPO_BOTONCOVID + " FROM " + Utilidades.TABLA_AJUSTE + " WHERE " +
                    Utilidades.CAMPO_IDUSUARIO_AJUSTE + " = ?", parametros);
            cursor.moveToFirst();
            if(cursor.getInt(0) == 1){
                estadoBotonCovid = true;
            }
            db.close();
        }catch (Exception e){

        }
        conexion.close();
        return estadoBotonCovid;
    }

    public static int retornaSemanaBotonCovid(){
        int semanaBotonCovid = 0;
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        SQLiteDatabase db = conexion.getReadableDatabase();
        String[] parametros = {"1"};
        try {
            Cursor cursor = db.rawQuery("SELECT " + Utilidades.CAMPO_SEMANABOTONCOVID + " FROM " + Utilidades.TABLA_AJUSTE + " WHERE " +
                    Utilidades.CAMPO_IDUSUARIO_AJUSTE + " = ?", parametros);
            cursor.moveToFirst();
            semanaBotonCovid= cursor.getInt(0);
            db.close();
        }catch (Exception e){

        }
        conexion.close();
        return semanaBotonCovid;
    }

    public static void actualizarEstadoBotonCovid(Ajuste ajuste){
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String update = "UPDATE " + Utilidades.TABLA_AJUSTE + " SET " + Utilidades.CAMPO_BOTONCOVID + " = " + ajuste.botonCovid + ", " +
                Utilidades.CAMPO_SEMANABOTONCOVID + " = " + ajuste.semanaBotonCovid + " WHERE " + Utilidades.CAMPO_IDUSUARIO_AJUSTE + " = " +
                ajuste.idUsuario;
        db.execSQL(update);
        db.close();
        conexion.close();
    }

    public static void desactivarBotonCovid(){
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_covidRastreo", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String update = "UPDATE " + Utilidades.TABLA_AJUSTE + " SET " + Utilidades.CAMPO_BOTONCOVID + " = 0 WHERE " + Utilidades.CAMPO_IDUSUARIO_AJUSTE +
                " = 1";
        db.execSQL(update);
        db.close();
        conexion.close();
    }

}
