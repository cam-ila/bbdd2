Trabajo Practico 2
==================

Parte 1: Bases de Datos NoSQL y Relacionales
--------------------------------------------

Si bien las BBDD NoSQL tienen diferencias fundamentales con los sistemas de BBDD
Relacionales o RDBMS, algunos conceptos comunes se pueden relacionar.
Responda las siguientes preguntas, considerando MongoDB en particular como Base
de Datos NoSQL.

1. ¿Cuáles de los siguientes conceptos de RDBMS existen en MongoDB? En caso de
   no existir, ¿hay alguna alternativa? ¿Cuál es?
• Base de Datos
• Tabla / Relación
• Fila / Tupla
• Columna

2. MongoDB tiene soporte para transacciones, pero no es igual que el de los
   RDBMS. ¿Cuál es el alcance de una transacción en MongoDB?

3. Para acelerar las consultas, MongoDB tiene soporte para índices. ¿Qué tipos
   de índices soporta?

4. ¿Existen claves foráneas en MongoDB?


Parte 2: Primeros pasos con MongoDB
-----------------------------------

5. Cree una nueva base de datos llamada turismo, y una colección llamada
   hoteles. En esa colección inserte un nuevo documento (un hotel) con los
   siguientes atributos:

```js
> use turismo
switched to db turismo
> db.hoteles.save({nombre:'Hotel Avenida', estrellas:3})
WriteResult({ "nInserted" : 1 })
> db.hoteles.find()
{ "_id" : ObjectId("5940af300a11111bb7d5e795"), "nombre" : "Hotel Avenida", "estrellas" : 3 }
> db.hoteles.find().pretty()
{
    "_id" : ObjectId("5940af300a11111bb7d5e795"),
      "nombre" : "Hotel Avenida",
        "estrellas" : 3
}
```

Se le agrega un Object Id.

6. Agregue los siguientes documentos a la colección de hoteles:
```js
> db.hoteles.save({nombre:'Hotel Lux', estrellas:3, amenities: ['piscina', 'gimnasio']})
WriteResult({ "nInserted" : 1 })
> db.hoteles.save({nombre:'Hotel Midas', estrellas:4, amenities: ['piscina']})
WriteResult({ "nInserted" : 1 })
> db.hoteles.save({nombre:'Genova Hotel', estrellas:3})
WriteResult({ "nInserted" : 1 })
> db.hoteles.save({nombre:'Paris Suites', estrellas:5, amenities: ['sauna']})
WriteResult({ "nInserted" : 1 })

> db.hoteles.find().pretty()
{
    "_id" : ObjectId("5940af300a11111bb7d5e795"),
      "nombre" : "Hotel Avenida",
        "estrellas" : 3
}
{
    "_id" : ObjectId("5940af8f0a11111bb7d5e796"),
      "nombre" : "Hotel Lux",
        "estrellas" : 3,
          "amenities" : [
              "piscina",
                  "gimnasio"
                    ]
}
{
    "_id" : ObjectId("5940af9f0a11111bb7d5e797"),
      "nombre" : "Hotel Midas",
        "estrellas" : 4,
          "amenities" : [
              "piscina"
                ]
}
{
    "_id" : ObjectId("5940afac0a11111bb7d5e798"),
      "nombre" : "Genova Hotel",
        "estrellas" : 3
}
{
    "_id" : ObjectId("5940afc10a11111bb7d5e799"),
      "nombre" : "Paris Suites",
        "estrellas" : 5,
          "amenities" : [
              "sauna"
                ]
}
```

Hoteles con 3 estrellas:
```js
> db.hoteles.find({"estrellas":3 })
{ "_id" : ObjectId("5940af300a11111bb7d5e795"), "nombre" : "Hotel Avenida", "estrellas" : 3 }
{ "_id" : ObjectId("5940af8f0a11111bb7d5e796"), "nombre" : "Hotel Lux", "estrellas" : 3, "amenities" : [ "piscina", "gimnasio" ] }
{ "_id" : ObjectId("5940afac0a11111bb7d5e798"), "nombre" : "Genova Hotel", "estrellas" : 3 }
```

Hoteles que incluyan la palabra 'Hotel' en su nombre:

```js
> db.hoteles.find({"nombre": /Hotel/})
{ "_id" : ObjectId("5940af300a11111bb7d5e795"), "nombre" : "Hotel Avenida", "estrellas" : 3 }
{ "_id" : ObjectId("5940af8f0a11111bb7d5e796"), "nombre" : "Hotel Lux", "estrellas" : 3, "amenities" : [ "piscina", "gimnasio" ] }
{ "_id" : ObjectId("5940af9f0a11111bb7d5e797"), "nombre" : "Hotel Midas", "estrellas" : 4, "amenities" : [ "piscina"] }
{ "_id" : ObjectId("5940afac0a11111bb7d5e798"), "nombre" : "Genova Hotel", "estrellas" : 3 }
```
Hoteles con 4 o mas estrellas:
```js
> db.hoteles.find({"estrellas":{ $gt: 3 }} )
{ "_id" : ObjectId("5940af9f0a11111bb7d5e797"), "nombre" : "Hotel Midas", "estrellas" : 4, "amenities" : [ "piscina" ] }
{ "_id" : ObjectId("5940afc10a11111bb7d5e799"), "nombre" : "Paris Suites", "estrellas" : 5, "amenities" : [ "sauna" ] }
```

Hoteles con la palabra "Hotel" en su nombre y mas de 3 estrellas
```js
> db.hoteles.find({"nombre": /Hotel/, "estrellas":{$gt: 3}})
{ "_id" : ObjectId("5940af9f0a11111bb7d5e797"), "nombre" : "Hotel Midas", "estrellas" : 4, "amenities" : [ "piscina" ] }
```

Hoteles con piscina:
```js
> db.hoteles.find({"amenities": "piscina"})
{ "_id" : ObjectId("5940af8f0a11111bb7d5e796"), "nombre" : "Hotel Lux", "estrellas" : 3, "amenities" : [ "piscina", "gimnasio" ] }
{ "_id" : ObjectId("5940af9f0a11111bb7d5e797"), "nombre" : "Hotel Midas", "estrellas" : 4, "amenities" : [ "piscina" ] }
```

Hoteles sin amenities (es decir, que el atributo este ausente)
```js
> db.hoteles.find({"amenities": null })
{ "_id" : ObjectId("5940af300a11111bb7d5e795"), "nombre" : "Hotel Avenida", "estrellas" : 3 }
{ "_id" : ObjectId("5940afac0a11111bb7d5e798"), "nombre" : "Genova Hotel", "estrellas" : 3 }
```

Hoteles sin aminities, proyectando solo el nombre:
```js
> db.hoteles.find({"amenities": null }, {_id:0, nombre: 1})
{ "nombre" : "Hotel Avenida" }
{ "nombre" : "Genova Hotel" }
```

7. Actualice el “Hotel Lux” asignándole 4 estrellas.

```js
> db.hoteles.update({nombre: "Hotel Lux"}, { $set: {estrellas: 4}})
WriteResult({ "nMatched" : 1, "nUpserted" : 0, "nModified" : 1 })
> db.hoteles.find()
{ "_id" : ObjectId("5940af300a11111bb7d5e795"), "nombre" : "Hotel Avenida", "estrellas" : 3 }
{ "_id" : ObjectId("5940af8f0a11111bb7d5e796"), "nombre" : "Hotel Lux", "estrellas" : 4, "amenities" : [ "piscina", "gimnasio" ] }
{ "_id" : ObjectId("5940af9f0a11111bb7d5e797"), "nombre" : "Hotel Midas", "estrellas" : 4, "amenities" : [ "piscina" ] }
{ "_id" : ObjectId("5940afac0a11111bb7d5e798"), "nombre" : "Genova Hotel", "estrellas" : 3 }
{ "_id" : ObjectId("5940afc10a11111bb7d5e799"), "nombre" : "Paris Suites", "estrellas" : 5, "amenities" : [ "sauna" ] }
```


8. Agregue “sauna” al listado de amenities del “Hotel Midas”.

```js
> db.hoteles.update({nombre: "Hotel Midas"}, { $push: {amenities: "sauna"}})
WriteResult({ "nMatched" : 1, "nUpserted" : 0, "nModified" : 1 })
> db.hoteles.find()
{ "_id" : ObjectId("5940af300a11111bb7d5e795"), "nombre" : "Hotel Avenida", "estrellas" : 3 }
{ "_id" : ObjectId("5940af8f0a11111bb7d5e796"), "nombre" : "Hotel Lux", "estrellas" : 4, "amenities" : [ "piscina", "gimnasio" ] }
{ "_id" : ObjectId("5940af9f0a11111bb7d5e797"), "nombre" : "Hotel Midas", "estrellas" : 4, "amenities" : [ "piscina", "sauna" ] }
{ "_id" : ObjectId("5940afac0a11111bb7d5e798"), "nombre" : "Genova Hotel", "estrellas" : 3 }
{ "_id" : ObjectId("5940afc10a11111bb7d5e799"), "nombre" : "Paris Suites", "estrellas" : 5, "amenities" : [ "sauna" ] }
```

9. Agregue una estrella más a todos los hoteles con piscina.

```js
> db.hoteles.update({"amenities": "piscina"}, { $inc: {estrellas: 1}}, {multi:true})
WriteResult({ "nMatched" : 2, "nUpserted" : 0, "nModified" : 2 })
> db.hoteles.find()
{ "_id" : ObjectId("5940af300a11111bb7d5e795"), "nombre" : "Hotel Avenida", "estrellas" : 3 }
{ "_id" : ObjectId("5940af8f0a11111bb7d5e796"), "nombre" : "Hotel Lux", "estrellas" : 5, "amenities" : [ "piscina", "gimnasio" ] }
{ "_id" : ObjectId("5940af9f0a11111bb7d5e797"), "nombre" : "Hotel Midas", "estrellas" : 5, "amenities" : [ "piscina", "sauna" ] }
{ "_id" : ObjectId("5940afac0a11111bb7d5e798"), "nombre" : "Genova Hotel", "estrellas" : 3 }
{ "_id" : ObjectId("5940afc10a11111bb7d5e799"), "nombre" : "Paris Suites", "estrellas" : 5, "amenities" : [ "sauna" ] }
```


Si amenities no existe
$exists:false

Parte 3: 
--------

10. Busque en la colección de hoteles si existe algún índice definido.

```
> db.hoteles.getIndexes()
	[
		{
			"v" : 2,
			"key" : {
				"_id" : 1
			},
			"name" : "_id_",
			"ns" : "turismo.hoteles"
		}
	]

```

11. Antes de agregarle un indice nuevo

```js
> db.hoteles.find({nombre: /11/}).explain("executionStats")
...
...
...
	"totalDocsExamined" : 50000,
		"executionStages" : {
			"stage" : "COLLSCAN",
			"filter" : {
				"nombre" : {
					"$regex" : "11"
				}
			},
			"nReturned" : 2291,
			"executionTimeMillisEstimate" : 30,
...
...
```

Agrego un indice

```js
> db.hoteles.createIndex({nombre:1})
{
	"createdCollectionAutomatically" : false,
	"numIndexesBefore" : 1,
	"numIndexesAfter" : 2,
	"ok" : 1
}
> db.hoteles.getIndexes()
[
	{
		"v" : 2,
		"key" : {
			"_id" : 1
		},
		"name" : "_id_",
		"ns" : "turismo.hoteles"
	},
	{
		"v" : 2,
		"key" : {
			"nombre" : 1
		},
		"name" : "nombre_1",
		"ns" : "turismo.hoteles"
	}
]

> db.hoteles.find({nombre: /11/}).explain("executionStats")

		"totalKeysExamined" : 50000,
		"totalDocsExamined" : 2291,
		"executionStages" : {
			"stage" : "FETCH",
			"nReturned" : 2291,
			"executionTimeMillisEstimate" : 50,

```

12. Busque los hoteles dentro de la provincia de La Pampa. Para esto, puede
    obtener el polígono del archivo provisto lapampa.geojson y definir una
    variable en la terminal para facilitar la consulta. Cree un índice
    geoespacial de tipo 2dsphere para el campo location de la colección hoteles
    y, de la misma forma que en el punto 11, compare la performance de la
    consulta con y sin dicho índice.


Guardamos en una variable todo el contenido del archivo.
```js
>lapampa = <archivo provisto lapampa.geojson>
```
Generamos una coleccion para ver en compass. Para asegurarnos que los hoteles que trae la consulta son todos de la pampa.

```js
>db.hoteles.aggregate([ { $match: {location: {$geoWithin: {$geometry: lapampa }}}}, {$out: "laPampa"} ])
```

Sin definir un indice

```js
> db.hoteles.find({location: {$geoWithin: {$geometry: lapampa }}}).explain("executionStats")
	...
	"executionTimeMillisEstimate" : 181,
	"docsExamined" : 50000

```
Creo un indice

```js
> db.hoteles.createIndex( { location: "2dsphere" })
{
	"createdCollectionAutomatically" : false,
	"numIndexesBefore" : 2,
	"numIndexesAfter" : 3,
	"ok" : 1
}

"executionTimeMillisEstimate" : 111,
	"docsExamined" : 10541,

```
Con indice 'deberia' buscar mas rapido, pero si no usa expresion regular.

Parte 4
-------

13. Obtenga 5 hoteles aleatorios 

```js
> db.hoteles.aggregate([{$sample: {size: 5} }])
{ "_id" : ObjectId("59429ccf02e77ed66a7430b5"), "nombre" : "Hotel 47315", "estrellas" : 3, "amenities" : [ "gimnasio", "piscina", "estacionamiento" ], "location" : { "type" : "Point", "coordinates" : [ -67.04242101372091, -37.49769252631296 ] } }
{ "_id" : ObjectId("59429cb502e77ed66a73a0e7"), "nombre" : "Hotel 10501", "estrellas" : 4, "amenities" : [ ], "location" : { "type" : "Point", "coordinates" : [ -64.78467452798112, -33.389257797048295 ] } }
{ "_id" : ObjectId("59429ccc02e77ed66a741b87"), "nombre" : "Hotel 41893", "estrellas" : 1, "amenities" : [ "piscina", "gimnasio" ], "location" : { "type" : "Point", "coordinates" : [ -68.55078803685062, -34.08194441167584 ] } }
{ "_id" : ObjectId("59429ccd02e77ed66a742049"), "nombre" : "Hotel 43111", "estrellas" : 5, "amenities" : [ ], "location" : { "type" : "Point", "coordinates" : [ -69.56124775250838, -31.308154820039448 ] } }
{ "_id" : ObjectId("59429ccb02e77ed66a7414e0"), "nombre" : "Hotel 40190", "estrellas" : 4, "amenities" : [ "piscina" ], "location" : { "type" : "Point", "coordinates" : [ -64.28921593158327, -37.61871037039382 ] } }
```

14. Usando el framework de agregación, obtenga los hoteles que estén a 15km (o
    menos) del centro geográfico de la ciudad de Córdoba ([-64.1888,
    -31.4201]).

> 9.321 millas = 15 kilometros
  3963.2 = approximate equatorial radius of the earth
  Dividiendo esos 2 se consigue la distancia en radianes.

```js
> db.hoteles.find( { location: { $geoWithin: { $centerSphere: [ [-64.1888, -31.4201], 9.321/3963.2 ] } } } )

> db.hoteles.aggregate([ { $match: {location: {$geoWithin: {$centerSphere: [ [-64.1888, -31.4201], 9.321/3963.2 ]}}}}, {$out: "cordoba"} ])
```

15. Para los hoteles hallados en el punto anterior, obtener una colección con
    cada hotel agregando un atributo habitaciones que contenga un array con
    todas sus habitaciones. Note que sólo es posible ligarlas por el nombre del
    hotel.

Esto lo da la catedra para convertir los precios de String a Float.
```js
> db.habitaciones.find().forEach( function (x) {x.precio = parseFloat(x.precio); db.habitaciones.save(x); });
```

Usando la coleccion que habiamos creado, le agregamos las habitaciones guardandolas es cordobaConHabitaciones
```js
> db.cordoba.aggregate([ { $lookup:{from: "habitaciones", localField: "nombre", foreignField: "nombreHotel", as: "habitaciones" }}, {$out: "cordobaConHabitaciones"}])
```

16.Usando la colección del punto anterior, obtenga el promedio de precio de habitación para cada hotel.

```js
> db.cordobaConHabitaciones.aggregate([
    { 
        "$addFields": { 
            "precio_promedio": {
                "$divide": [
                    { // devuelve la suma de precios
                        "$reduce": {
                            "input": "$habitaciones",
                            "initialValue": 0,
                            "in": { "$add": ["$$value", "$$this.precio"] }
                        }
                    },
                    { // devuelve el total de habitaciones
                        "$cond": [
                            { "$ne": [ { "$size": "$habitaciones" }, 0 ] },
                            { "$size": "$habitaciones" }, 
                            1
                        ]
                    }
                ]
            }
        }
    }           
])
```
```js
db.cordoba_conHabitaciones.aggregate([{"$addFields": {"precio_promedio": {"$divide": [{"$reduce": {"input": "$habitaciones", "initialValue": 0, "in": { "$add": ["$$value", "$$this.precio"] } } }, {"$cond": [{ "$ne": [ { "$size": "$habitaciones" }, 0 ] }, { "$size": "$habitaciones" }, 1 ] } ] } } } ]).pretty()
```

Una parte como ejemplo de lo que devolvio la consulta
```js
{
	"_id" : ObjectId("59429cbd02e77ed66a73cbbe"),
	"nombre" : "Hotel 21468",
	"estrellas" : 4,
	"amenities" : [ ],
	"location" : {
		"type" : "Point",
		"coordinates" : [
			-64.28398898868876,
			-31.50038173939423
		]
	},
	"habitaciones" : [
		{
			"_id" : ObjectId("59429d9f02e77ed66a75379e"),
			"nombreHotel" : "Hotel 21468",
			"tipo" : "Suite",
			"capacidad" : 4,
			"precio" : 143.31
		},
		{
			"_id" : ObjectId("59429d9f02e77ed66a75379f"),
			"nombreHotel" : "Hotel 21468",
			"tipo" : "Suite",
			"capacidad" : 4,
			"precio" : 143.31
		},
		{
			"_id" : ObjectId("59429d9f02e77ed66a7537a0"),
			"nombreHotel" : "Hotel 21468",
			"tipo" : "Suite",
			"capacidad" : 4,
			"precio" : 143.31
		},
		{
			"_id" : ObjectId("59429d9f02e77ed66a7537a1"),
			"nombreHotel" : "Hotel 21468",
			"tipo" : "Suite",
			"capacidad" : 4,
			"precio" : 143.31
		}
	],
	"precio_promedio" : 143.31
}

```
