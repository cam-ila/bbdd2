for (var i = 1; i <= 50000; i++) {
  var randomAmenities = ['sauna', 'piscina', 'gimnasio', 'estacionamiento'].sort(
function() { return 0.5 - Math.random() } ).slice(1, Math.floor(Math.random() * 5));
  var randomStars = Math.ceil(Math.random() * 5);
  var randomLong = ((Math.random() * 10) + 63) * -1;
  var randomLat = ((Math.random() * 10) + 29) * -1;
  db.hoteles.insert({
    nombre:'Hotel '+i,
    estrellas:randomStars,
    amenities: randomAmenities,
    location: {
    type: "Point",
    coordinates: [randomLong, randomLat]
    }
  });
}
