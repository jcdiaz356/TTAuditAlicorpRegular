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
    public static int company_id = 119;
    // public static String albunName = "AlicorpPhoto";
    //public static String directory_images = "/Pictures/" + albunName;
    public static String directory_images = "/Pictures/" ;
    public static String type_aplication = "android";
    public static int[] poll_id = new int[]{
          2047, //1688, // 1605, //1463 , // 1235 , //	0	Se encuentra Abierto el punto?
          2048, //1689, // 1606, //1464 , // 1236 , //	1	¿Cliente permitió tomar información?
          2049, //1690, // 1607, //1465 , // 1237 , //	2	¿Ventana esta Trabajada? (Tiene fronterizador arriba y abajo)
          2050, //1691, // 1608, //1466 , // 1238 , //	3	¿Existe Ventana?
          2051, //1692, // 1609, //1467 , // 1239 , //	4	¿ La Ventana es visible ?
          2052, //1693, // 1610, //1468 , // 1240 , //	5	¿ Como se encuentra la Ventana ?
          2053, //1694, // 1611, //1469 , // 1241 , //	6	¿ Se encontro exhibidor ?
          2054, //1695, // 1612, //1470 , // 1242 , //	7	¿ Es cliente perfecto ?
          2055, //1696, // 1613, //1471 , // 1243 , //	8	¿Desde cuando es cliente perfecto?
} ;

    public static int[] audit_id = new int[]{
            50, // 0	Preguntas Hulk
            49, // 1	Visibilidad Hulk
    } ;

    public static final String JPEG_FILE_PREFIX = "_alicorp_reg_";
    public static final String JPEG_FILE_SUFFIX = ".jpg";

}


