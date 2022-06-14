package app.fernando.covidrastreo.Utilidades;

public class Utilidades {

    //Constantes para tabla usuario
    public static final String TABLA_USUARIO = "usuario";
    public static final String CAMPO_IDUSUARIO = "idUsuario";
    public static final String CAMPO_NOMBRES = "nombres";
    public static final String CAMPO_APELLIDOS = "apellidos";
    public static final String CAMPO_FECHANACIMIENTO = "fechaNacimiento";
    public static final String CAMPO_SEXO = "sexc";
    public static final String CAMPO_CORREO = "correo";
    public static final String CAMPO_CONTRASENIA = "contrasenia";
    public static final String CAMPO_DIRECCIONBT = "direccionBT";
    public static final String CAMPO_ESTADOINFECCION = "estadoInfeccion";
    public static final String CAMPO_SEMINFUSUARIO = "semInfUsuario";

    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE " + TABLA_USUARIO + " (" + CAMPO_IDUSUARIO + " INTEGER, " + CAMPO_NOMBRES +
            " TEXT, " + CAMPO_APELLIDOS + " TEXT, " + CAMPO_FECHANACIMIENTO + " TEXT, " + CAMPO_SEXO + " INTEGER, " +
            CAMPO_CORREO + " TEXT, " + CAMPO_CONTRASENIA + " TEXT, " + CAMPO_DIRECCIONBT + " TEXT, " +
            CAMPO_ESTADOINFECCION + " INTEGER, " + CAMPO_SEMINFUSUARIO + " INTEGER)";


    //Constantes para tabla contacto
    public static final String TABLA_CONTACTO = "contacto";
    public static final String CAMPO_IDCONTACTO = "idContacto";
    public static final String CAMPO_DIRECCIONBTCONTACTO = "direccionBT";
    public static final String CAMPO_ESTADOINFECCIONCONTACTO = "estadoInfeccion";
    public static final String CAMPO_SEMINFCONTACTO = "semInfContacto";
    public static final String CAMPO_IDUSUARIOCONTACTO = "idUsuario";

    public static final String CREAR_TABLA_CONTACTO = "CREATE TABLE " + TABLA_CONTACTO + " (" + CAMPO_IDCONTACTO +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + CAMPO_DIRECCIONBTCONTACTO + " TEXT, " + CAMPO_ESTADOINFECCIONCONTACTO +
            " INTEGER, " + CAMPO_SEMINFCONTACTO + " INTEGER," + CAMPO_IDUSUARIOCONTACTO + " INTEGER)";


    //Constantes para tabla usuariocontacto
    public static final String TABLA_USUARIO_CONTACTO = "usuariocontacto";
    public static final String CAMPO_IDUSUARIO_CONTACTO = "idUsuarioContacto";
    public static final String CAMPO_NOMBRE_USUARIO = "nombreUsuario";
    public static final String CAMPO_APELLIDO_USUARIO = "apellidoUsuario";
    public static final String CAMPO_SEXO_USUARIO = "sexoUsuario";
    public static final String CAMPO_DIRECCIONBT_USUARIO = "direccionBTUsuario";
    public static final String CAMPO_ESTADOINFECCION_USUARIO = "estadoInfeccionUsuario";
    public static final String CAMPO_SEMANAINFECCION_USUARIO = "semanaInfeccionUsuario";
    public static final String CAMPO_DIRECCIONBT_CONTACTO = "direccionBTContacto";
    public static final String CAMPO_ESTADOINFECCION_CONTACTO = "estadoInfeccionContacto";
    public static final String CAMPO_SEMANAINFECCION_CONTACTO = "semanaInfeccionContacto";

    public static final String CREAR_TABLA_USUARIOCONTACTO = "CREATE TABLE " + TABLA_USUARIO_CONTACTO + " (" + CAMPO_IDUSUARIO_CONTACTO +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + CAMPO_NOMBRE_USUARIO + " TEXT, " + CAMPO_APELLIDO_USUARIO + " TEXT, " + CAMPO_SEXO_USUARIO +
            " INTEGER, " + CAMPO_DIRECCIONBT_USUARIO + " TEXT, " + CAMPO_ESTADOINFECCION_USUARIO + " INTEGER, " + CAMPO_SEMANAINFECCION_USUARIO +
            " INTEGER, " + CAMPO_DIRECCIONBT_CONTACTO + " TEXT, " + CAMPO_ESTADOINFECCION_CONTACTO + " INTEGER, " + CAMPO_SEMANAINFECCION_CONTACTO + " INTEGER)";

    //Constantes para tabla usuariocontactocopia
    public static final String TABLA_COPIA_USUARIO_CONTACTO = "usuariocontactoc";
    public static final String CAMPO_COPIA_IDUSUARIO_CONTACTO = "idUsuarioContacto";
    public static final String CAMPO_COPIA_NOMBRE_USUARIO = "nombreUsuario";
    public static final String CAMPO_COPIA_APELLIDO_USUARIO = "apellidoUsuario";
    public static final String CAMPO_COPIA_SEXO_USUARIO = "sexoUsuario";
    public static final String CAMPO_COPIA_DIRECCIONBT_USUARIO = "direccionBTUsuario";
    public static final String CAMPO_COPIA_ESTADOINFECCION_USUARIO = "estadoInfeccionUsuario";
    public static final String CAMPO_COPIA_SEMANAINFECCION_USUARIO = "semanaInfeccionUsuario";
    public static final String CAMPO_COPIA_DIRECCIONBT_CONTACTO = "direccionBTContacto";
    public static final String CAMPO_COPIA_ESTADOINFECCION_CONTACTO = "estadoInfeccionContacto";
    public static final String CAMPO_COPIA_SEMANAINFECCION_CONTACTO = "semanaInfeccionContacto";

    public static final String CREAR_TABLA_COPIA_USUARIOCONTACTO = "CREATE TABLE " + TABLA_COPIA_USUARIO_CONTACTO + " (" + CAMPO_COPIA_IDUSUARIO_CONTACTO +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + CAMPO_COPIA_NOMBRE_USUARIO + " TEXT, " + CAMPO_COPIA_APELLIDO_USUARIO + " TEXT, " +
            CAMPO_COPIA_SEXO_USUARIO + " INTEGER, " + CAMPO_COPIA_DIRECCIONBT_USUARIO + " TEXT, " + CAMPO_COPIA_ESTADOINFECCION_USUARIO + " INTEGER, " +
            CAMPO_COPIA_SEMANAINFECCION_USUARIO + " INTEGER, " +  CAMPO_COPIA_DIRECCIONBT_CONTACTO + " TEXT, " + CAMPO_COPIA_ESTADOINFECCION_CONTACTO +
            " INTEGER, " + CAMPO_COPIA_SEMANAINFECCION_CONTACTO + " INTEGER)";


    //Constantes para tabla ajuste
    public static final String TABLA_AJUSTE = "ajuste";
    public static final String CAMPO_IDAJUSTE = "idAjuste";
    public static final String CAMPO_ESCANEOAUTOMATICO = "escaneoAutomatico";
    public static final String CAMPO_HORAACTUALIZACION = "horaActualizacion";
    public static final String CAMPO_MINACTUALIZACION = "minActualizacion";
    public static final String CAMPO_BOTONCOVID = "botonCovid";
    public static final String CAMPO_SEMANABOTONCOVID = "semanaBotonCovid";
    public static final String CAMPO_IDUSUARIO_AJUSTE = "idUsuario";

    public static final String CREAR_TABLA_AJUSTE = "CREATE TABLE " + TABLA_AJUSTE + " (" + CAMPO_IDAJUSTE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CAMPO_ESCANEOAUTOMATICO + " INTEGER, " + CAMPO_HORAACTUALIZACION + " INTEGER, " + CAMPO_MINACTUALIZACION + " INTEGER, " +
            CAMPO_BOTONCOVID + " INTEGER, " + CAMPO_SEMANABOTONCOVID + " INTEGER, " + CAMPO_IDUSUARIO_AJUSTE + " INTEGER)";

}
