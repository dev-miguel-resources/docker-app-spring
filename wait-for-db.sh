# 👉 Indica que el script se ejecuta con el intérprete sh (shell)
#!/bin/sh
# 👉 Toma argumentos que le pasas al script:
# HOST=$1
# PORT=$2
HOST=$1
PORT=$2
# 👉 Elimina los primeros 2 argumentos (host y puerto)
# /wait-for-db.sh mysql 3306 java -jar app.jar
# Es para que después deje solo la definición de comandos finales
shift 2
# 👉 Guarda el resto de los argumentos como comando final
CMD=$@

# nc = netcat
# -z = solo verifica si el puerto está abierto,
# Esto intenta conectarse a: mysql:3306
# 👉 Mientras NO pueda conectarse: muestra mensaje, espera 2 segundos, vuelve a intentar
# 🔁 Loop infinito hasta que la DB esté lista
# 👉 Cuando logra conectarse: significa que MySQL ya está listo
until nc -z $HOST $PORT; do
  echo "Waiting for database..."
  sleep 2
done

echo "Database is up!"
# 👉 Ejecuta el comando final (tu app): java -jar app.jar
# esto se llama del dockerfile
exec $CMD

# depends_on → “espera que el restaurante abra”
# wait-for-db → “espera que el chef esté listo para cocinar”
# 👉 Verifica conexión real con tu app
# “El healthcheck permite a Docker determinar cuándo un servicio está saludable, 
# mientras que el script wait-for asegura la disponibilidad real antes de 
# iniciar la aplicación, actuando como una capa adicional de seguridad.”