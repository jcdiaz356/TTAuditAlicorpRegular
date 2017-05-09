package com.dataservicios.ttauditalicorpregular.util;
/**
 * Created by usuario on 11/11/2014.
 */
public final class GlobalConstant {
    public static String dominio = "http://ttaudit.com";

    public static final String LOGIN_URL = dominio + "/loginUser";
    public static final String KEY_USERNAME = "username";
    public static String inicio,fin;
    public static  double latitude_open, longitude_open;
    public static  int global_close_audit =0;
    public static int company_id = 75;
    // public static String albunName = "AlicorpPhoto";
    //public static String directory_images = "/Pictures/" + albunName;
    public static String directory_images = "/Pictures/" ;
    public static String type_aplication = "android";
    public static int[] poll_id = new int[]{
            1235 , //	0	Se encuentra Abierto el punto?
            1236 , //	1	¿Cliente permitió tomar información?
            1237 , //	2	¿Ventana esta Trabajada? (Tiene fronterizador arriba y abajo)
            1238 , //	3	¿Existe Ventana?
            1239 , //	4	¿ La Ventana es visible ?
            1240 , //	5	¿ Como se encuentra la Ventana ?
            1241 , //	6	¿ Se encontro exhibidor ?
            1242 , //	7	¿ Es cliente perfecto ?
            1243 , //	8	¿Desde cuando es cliente perfecto?
} ;

    public static int[] audit_id = new int[]{
            50, // 0	Preguntas Hulk
            49, // 1	Visibilidad Hulk
    } ;

    public static final String JPEG_FILE_PREFIX = "_alicorp_h_";
    public static final String JPEG_FILE_SUFFIX = ".jpg";
}


