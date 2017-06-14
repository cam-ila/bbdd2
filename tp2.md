## Trabajo Practico 2

Parte 2: Primeros pasos con MongoDB
===================================

5.

> use turismo
switched to db turismo
> db.hoteles.save({nombre:’Hotel Avenida’, estrellas:3})
2017-06-14T00:36:02.762-0300 E QUERY    [thread1] SyntaxError: illegal character @(shell):1:24
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

Se le agrega un Object Id.

6.
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

Hoteles con 3 estrellas:
> db.hoteles.find({"estrellas":3 })
{ "_id" : ObjectId("5940af300a11111bb7d5e795"), "nombre" : "Hotel Avenida", "estrellas" : 3 }
{ "_id" : ObjectId("5940af8f0a11111bb7d5e796"), "nombre" : "Hotel Lux", "estrellas" : 3, "amenities" : [ "piscina", "gimnasio" ] }
{ "_id" : ObjectId("5940afac0a11111bb7d5e798"), "nombre" : "Genova Hotel", "estrellas" : 3 }

hoteles que incluyan la palabra 'Hotel' en su nombre:
> db.hoteles.find({"nombre": /Hotel/})
{ "_id" : ObjectId("5940af300a11111bb7d5e795"), "nombre" : "Hotel Avenida", "estrellas" : 3 }
{ "_id" : ObjectId("5940af8f0a11111bb7d5e796"), "nombre" : "Hotel Lux", "estrellas" : 3, "amenities" : [ "piscina", "gimnasio" ] }
{ "_id" : ObjectId("5940af9f0a11111bb7d5e797"), "nombre" : "Hotel Midas", "estrellas" : 4, "amenities" : [ "piscina"] }
{ "_id" : ObjectId("5940afac0a11111bb7d5e798"), "nombre" : "Genova Hotel", "estrellas" : 3 }

hoteles con 4 o mas estrellas:
> db.hoteles.find({"estrellas":{ $gt: 3 }} )
{ "_id" : ObjectId("5940af9f0a11111bb7d5e797"), "nombre" : "Hotel Midas", "estrellas" : 4, "amenities" : [ "piscina" ] }
{ "_id" : ObjectId("5940afc10a11111bb7d5e799"), "nombre" : "Paris Suites", "estrellas" : 5, "amenities" : [ "sauna" ] }

hoteles con la palabra "Hotel" en su nombre y mas de 3 estrellas
> db.hoteles.find({"nombre": /Hotel/, "estrellas":{$gt: 3}})
{ "_id" : ObjectId("5940af9f0a11111bb7d5e797"), "nombre" : "Hotel Midas", "estrellas" : 4, "amenities" : [ "piscina" ] }

hoteles con piscina:
> db.hoteles.find({"amenities": "piscina"})
{ "_id" : ObjectId("5940af8f0a11111bb7d5e796"), "nombre" : "Hotel Lux", "estrellas" : 3, "amenities" : [ "piscina", "gimnasio" ] }
{ "_id" : ObjectId("5940af9f0a11111bb7d5e797"), "nombre" : "Hotel Midas", "estrellas" : 4, "amenities" : [ "piscina" ] }

hoteles sin amenities (es decir, que el atributo este ausente)
> db.hoteles.find({"amenities": null })
{ "_id" : ObjectId("5940af300a11111bb7d5e795"), "nombre" : "Hotel Avenida", "estrellas" : 3 }
{ "_id" : ObjectId("5940afac0a11111bb7d5e798"), "nombre" : "Genova Hotel", "estrellas" : 3 }

hoteles sin aminities, proyectando solo el nombre:
> db.hoteles.find({"amenities": null }, {_id:0, nombre: 1})
{ "nombre" : "Hotel Avenida" }
{ "nombre" : "Genova Hotel" }


7. Actualice el “Hotel Lux” asignándole 4 estrellas.

> db.hoteles.update({nombre: "Hotel Lux"}, { $set: {estrellas: 4}})
WriteResult({ "nMatched" : 1, "nUpserted" : 0, "nModified" : 1 })
> db.hoteles.find()
{ "_id" : ObjectId("5940af300a11111bb7d5e795"), "nombre" : "Hotel Avenida", "estrellas" : 3 }
{ "_id" : ObjectId("5940af8f0a11111bb7d5e796"), "nombre" : "Hotel Lux", "estrellas" : 4, "amenities" : [ "piscina", "gimnasio" ] }
{ "_id" : ObjectId("5940af9f0a11111bb7d5e797"), "nombre" : "Hotel Midas", "estrellas" : 4, "amenities" : [ "piscina" ] }
{ "_id" : ObjectId("5940afac0a11111bb7d5e798"), "nombre" : "Genova Hotel", "estrellas" : 3 }
{ "_id" : ObjectId("5940afc10a11111bb7d5e799"), "nombre" : "Paris Suites", "estrellas" : 5, "amenities" : [ "sauna" ] }


8. Agregue “sauna” al listado de amenities del “Hotel Midas”.

> db.hoteles.update({nombre: "Hotel Midas"}, { $push: {amenities: "sauna"}})
WriteResult({ "nMatched" : 1, "nUpserted" : 0, "nModified" : 1 })
> db.hoteles.find()
{ "_id" : ObjectId("5940af300a11111bb7d5e795"), "nombre" : "Hotel Avenida", "estrellas" : 3 }
{ "_id" : ObjectId("5940af8f0a11111bb7d5e796"), "nombre" : "Hotel Lux", "estrellas" : 4, "amenities" : [ "piscina", "gimnasio" ] }
{ "_id" : ObjectId("5940af9f0a11111bb7d5e797"), "nombre" : "Hotel Midas", "estrellas" : 4, "amenities" : [ "piscina", "sauna" ] }
{ "_id" : ObjectId("5940afac0a11111bb7d5e798"), "nombre" : "Genova Hotel", "estrellas" : 3 }
{ "_id" : ObjectId("5940afc10a11111bb7d5e799"), "nombre" : "Paris Suites", "estrellas" : 5, "amenities" : [ "sauna" ] }

9. Agregue una estrella más a todos los hoteles con piscina.

> db.hoteles.update({"amenities": "piscina"}, { $inc: {estrellas: 1}}, {multi:true})
WriteResult({ "nMatched" : 2, "nUpserted" : 0, "nModified" : 2 })
> db.hoteles.find()
{ "_id" : ObjectId("5940af300a11111bb7d5e795"), "nombre" : "Hotel Avenida", "estrellas" : 3 }
{ "_id" : ObjectId("5940af8f0a11111bb7d5e796"), "nombre" : "Hotel Lux", "estrellas" : 5, "amenities" : [ "piscina", "gimnasio" ] }
{ "_id" : ObjectId("5940af9f0a11111bb7d5e797"), "nombre" : "Hotel Midas", "estrellas" : 5, "amenities" : [ "piscina", "sauna" ] }
{ "_id" : ObjectId("5940afac0a11111bb7d5e798"), "nombre" : "Genova Hotel", "estrellas" : 3 }
{ "_id" : ObjectId("5940afc10a11111bb7d5e799"), "nombre" : "Paris Suites", "estrellas" : 5, "amenities" : [ "sauna" ] }

