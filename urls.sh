#! /bin/bash

echo -e "Conductores Detalles:"

curl -G -d "conductorId=1" "http://localhost:8080/MuberRESTful/rest/services/conductores/detalles"

echo -e "\n\n Agregar pasajero:"

curl -d "viajeId=2&pasajeroId=5" "http://localhost:8080/MuberRESTful/rest/services/viajes/agregarPasajero"

echo -e "\n\n Agregar Calificacion a viaje:"

curl -d "viajeId=1&pasajeroId=5&puntaje=1&comentario='tara raea erjk" "http://localhost:8080/MuberRESTful/rest/services/viajes/calificar"

echo -e "\n\n Agregar viaje:"

curl -d "origen=La Plata&destino=Capital&conductorId=1&costoTotal=300&cantidadPasajeros=3" "http://localhost:8080/MuberRESTful/rest/services/viajes/nuevo"

echo -e "\n\nListado de pasajeros:"

curl http://localhost:8080/MuberRESTful/rest/services/pasajeros

echo -e "\n\n  Listado de conductores:"

curl http://localhost:8080/MuberRESTful/rest/services/conductores

echo -e "\n\n Viajes Abiertos:"

curl http://localhost:8080/MuberRESTful/rest/services/abiertos


