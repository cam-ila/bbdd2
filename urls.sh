#! /bin/bash

echo -e "Conductores Detalles:"

curl -G -d "conductorId=1" "http://localhost:8080/MuberRESTful/rest/services/conductores/detalles"

echo -e "\n\n Agregar pasajero:"

curl -d "viajeId=2&pasajeroId=5" "http://localhost:8080/MuberRESTful/rest/services/viajes/agregarPasajero"

echo -e "\n\nListado de pasajeros:"

curl http://localhost:8080/MuberRESTful/rest/services/pasajeros

echo -e "\n\n  Listado de conductores:"

curl http://localhost:8080/MuberRESTful/rest/services/conductores

echo -e "\n\n Viajes Abiertos:"

curl http://localhost:8080/MuberRESTful/rest/services/abiertos


