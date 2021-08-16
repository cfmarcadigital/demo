El demo se debe ejecutar en el siguiente orden:

-----
Iniciar servidor apache-ftpserver
-----
1. Abrir la linea de comandos en la carpeta raiz del proyecto /demo
2. Ejecutar apache-ftpserver-1.1.1\bin\ftpd.bat res\conf\ftpd-typical.xml

-----
Ejecutar el servicio web http://localhost:8080/incomes
-----
3. Abrir la linea de comandos en la carpeta /demo/accounts
4. Ejecutar mvn spring-boot:run

-----
Ejecutar el proyecto http-get (Recupera el contenido de los servicios web local y externo en el servidor ftp)
-----
5. Abrir la linea de comandos en la carpeta /demo/http-get
6. Ejecutar mvn spring-boot:run

-----
Ejecutar el proyecto ftp (copia los archivos del servidor ftp a la carpeta local /downloads)
-----
7. Abrir la linea de comandos en la carpeta /demo/ftp
8. Ejecutar mvn spring-boot:run