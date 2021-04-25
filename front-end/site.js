function getProducts(brand) {
  var url = "http://localhost:8080/api/products";
  if (brand) {
    url += "/brand/" + brand;
  }

  fetch(url)
    .then((data) => data.json())
    .then((data) => {
      let out = "";
      data.forEach(
        (p) => (out += `<li>${p.description} - ₹${p.unitPrice}</li>`)
      );
      document.getElementById("plist").innerHTML = out;
    })
    .catch(console.error);
}
