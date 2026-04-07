# Indicar el script que se ejecuta con el interprete sh (shell)
#!/bin/sh
# Pasar unos argumentos a mi script:
HOST=$1
PORT=$2

# Eliminar los primeros 2 argumentos (host y port)
# /wait-for-db.sh mysql 3306 java -jar app.jar
shift 2

# Guarde el resto de los argumentos como comando final
CMD=$@

# nc = netcat
# -z = verifica que el puerto esté abierto
# Esto intenta conectarse a mysql:3306
# Mientras NO pueda conectarse: muestra un mensaje, espera 2 seg, vuelve a intentar
# Cuando logra conectarse: significa que MYSQL ya está listo
until nc -z $HOST $PORT; do
    echo "Waiting for database..."
    sleep 2
done

echo "Database is up!"
# Ejecutar el comando final de mi app: java -jar app.jar
exec $CMD # -> apunta a la salida del dockerfile

# depends_on -> "espera que el restaurante abra"
# wait-for-db.sh -> "espera que el chef esté listo para cocinar"
# script asegura la disponibilidad real antes de iniciar la app,
# actuando como una capa adicional de seguridad.