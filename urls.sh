#! /bin/bash

echo -e "Conductores Detalles:"

curl -G -d "conductorId=1" "http://localhost:8080/MuberRESTful/rest/services/conductores/detalles"

echo -e "\n\nListado de pasajeros:"

curl http://localhost:8080/MuberRESTful/rest/services/pasajeros

echo -e "\n\nListado de conductores:"

curl http://localhost:8080/MuberRESTful/rest/services/conductores

echo -e "\n\nViajes Abiertos:"

curl http://localhost:8080/MuberRESTful/rest/services/abiertos

echo -e "\n\nTop 10"

curl http://localhost:8080/MuberRESTful/rest/services/conductores

echo -e "\n\n---------------------------- Casos de prueba -------------------------------------------"

#Se crea un nuevo viaje de Córdoba a Mar del Plata con el conductor Roberto (existente, de la Etapa 1). El costo es de $3500.

echo -e "\n\nAgregar viaje:"

curl -d "origen=Cordoba&destino='Mar del plata'&conductorId=1&costoTotal=3500&cantidadPasajeros=4" "http://localhost:8080/MuberRESTful/rest/services/viajes/nuevo"

#Margarita se suma al viaje, pero antes suma $4000 a su crédito. Un nuevo pasajero, Hugo, con un crédito inicial de $2300 se suma al viaje también.

echo -e "\n\nAgregar credito a margarita:"

curl -X PUT -d "pasajeroId=3&monto=4000" http://localhost:8080/MuberRESTful/rest/services/pasajeros/cargarCredito -G

echo -e "\n\nMargarita se suma al viaje:"
curl -X PUT -d "pasajeroId=3&viajeId=2" http://localhost:8080/MuberRESTful/rest/services/viajes/agregarPasajero -G

echo -e "\n\nHugo se suma al viaje:"
curl -X PUT -d "pasajeroId=5&viajeId=2" http://localhost:8080/MuberRESTful/rest/services/viajes/agregarPasajero -G

#Al finalizar el viaje los dos pasajeros califican el mismo.Margarita califica con un 4, y Hugo con un 5. Ambos dejan comentarios al respecto.
#Al finalizar el viaje se realizan los descuentos correspondientes al crédito de los dos pasajeros.

echo -e "\n\nFinaliza viaje:"
curl -X PUT -d "viajeId=2" http://localhost:8080/MuberRESTful/rest/services/viajes/finalizar -G

echo -e "\n\nAgregar Calificacion a viaje Margarita:"
curl -d "viajeId=2&pasajeroId=3&puntaje=4&comentario='Lo dejo margaita" "http://localhost:8080/MuberRESTful/rest/services/viajes/calificar"

echo -e "\n\nAgregar Calificacion a viaje Hugo:"
curl -d "viajeId=2&pasajeroId=5&puntaje=5&comentario='Lo dejo Hugo" "http://localhost:8080/MuberRESTful/rest/services/viajes/calificar"

