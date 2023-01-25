<?xml version="1.0" encoding='ISO-8859-1' ?>
<!DOCTYPE helpset
  PUBLIC "-//Sun Microsystems Inc.//DTD JavaHelp HelpSet Version 1.0//EN"
         "http://java.sun.com/products/javahelp/helpset_1_0.dtd">
 

<helpset version="1.0">
        <title>Ayuda acerca de como funciona el gestor de copas</title>
        <maps>
                <!-- Pagina por defecto al mostrar la ayuda -->
                <homeID>Gestordecopas</homeID>
                <!-- Que mapa deseamos -->
                <mapref location="map_file.jhm"/>
        </maps>

        <!-- Las Vistas que deseamos mostrar en la ayuda -->
        <view>
                <name>Tabla contenidos</name>
                <label>tabla de contenidos</label>
                <type>javax.help.TOCView</type>
                <data>toc.xml</data>
        </view>	  
	<view>
    		<name>indice</name>
    		<label>el indice</label>
    		<type>javax.help.IndexView</type>
    		<data>indice.xml</data>
  	</view>
</helpset>

