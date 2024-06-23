Upute za lokalno podizanje

 Klonirati repozitorij

 Otvoriti direktorij u IDE-u (npr. Intellij IDEA)
 Pronađite glavnu klasu koja sadrži 'public static void main' metodu (ZadatakApplication)
 
 Za testiranje Endpoint-ova sluzi dokumentacija ispod pod nazivom Dokumentacija Endpoint-ova
 Endpoint-ovima su dodani logger zapisi koji zapisuju pozivanje Endpointa u zasebnu log datoteku koja se nalazi u 'logs/' repozitoriju

 Za pokretanje testova koji se nalaze u 'src/test/java' pokrenite 'ProductControllerTest' klasu pritom pripazite da ne runate 'ZadatakApplication'

 Autorizacija i autentikacija nije napravljena do kraja, pa je u SecurityConfiguration klasi puštena dozvola za dolje navedene Endpoint-ove da se mogu izvesti bez bearer tokena

 Kod više poziva endpointa pretrega ili filtriranja proizvoda s istim parametrima, da korisnik ne opterećuje toliko server i da poboljšamo performanse možemo implementirati Caching
 1. bi dodali dependency u pom.xml datoteku
 2. U glavnoj klasi bi dodali anotaciju @EnableCaching da omogućimo caching
 3. Moramo konfigurirati caching u 'resources/*.xml' datoteci
 4. Konfiguriramo CacheManager bean u njegovoj konfiguracijskoj klasi
 5. Dodamo caching u servisni sloj

Dokumentacija Endpoint-ova

1. localhost:8080/api/products/summary
   Opis: Dohvaća popis svih proizvoda u njihovom sažetom obliku
   Parametri: Nmea
   Odgovor:
   [
   {
        "image": [
            "https://cdn.dummyjson.com/products/images/beauty/Essence%20Mascara%20Lash%20Princess/1.png"
        ],
        "title": "Essence Mascara Lash Princess",
        "price": 9.99,
        "shortDescription": "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effe..."
    },
   ...
   ]

2. localhost:8080/api/products/details/{id}
   Opis: Dohvaća proizvod po njegovom identifikacijskom broju
   Parametri: id (integer): ID proizvoda
   Odogovor:
  [
    {
        "title": "Essence Mascara Lash Princess",
        "description": "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.",
        "category": "beauty",
        "price": 9.99,
        "rating": 4.94,
        "stock": 5,
        "tags": [
            "beauty",
            "mascara"
        ],
        "brand": "Essence",
        "weight": 2.0,
        "dimensions": {
            "width": 23.17,
            "height": 14.43,
            "depth": 28.01
        },
        "warrantyInformation": "1 month warranty",
        "shippingInformation": "Ships in 1 month",
        "availabilityStatus": "Low Stock",
        "reviews": [
            {
                "rating": 2,
                "comment": "Very unhappy with my purchase!",
                "date": "2024-05-23T08:56:21.618Z",
                "reviewerName": "John Doe",
                "reviewerEmail": "john.doe@x.dummyjson.com"
            },
            {
                "rating": 2,
                "comment": "Not as described!",
                "date": "2024-05-23T08:56:21.618Z",
                "reviewerName": "Nolan Gonzalez",
                "reviewerEmail": "nolan.gonzalez@x.dummyjson.com"
            },
            {
                "rating": 5,
                "comment": "Very satisfied!",
                "date": "2024-05-23T08:56:21.618Z",
                "reviewerName": "Scarlett Wright",
                "reviewerEmail": "scarlett.wright@x.dummyjson.com"
            }
        ],
        "returnPolicy": "30 days return policy",
        "minimumOrderQuantity": 24,
        "thumbnail": "https://cdn.dummyjson.com/products/images/beauty/Essence%20Mascara%20Lash%20Princess/thumbnail.png",
        "images": [
            "https://cdn.dummyjson.com/products/images/beauty/Essence%20Mascara%20Lash%20Princess/1.png"
        ]
    }
]

3. localhost:8080/api/products/categoryAndPrice/{category}?minPrice={double}&maxPrice={double}
   Opis: Dohvaća proizvode po određenoj kategoriji u cijenovnom rangu između najmanje i najveće postavljene cijene
   Parametri: category (String): kategorija
              minPrice (double): minimalna cijena
              maxPrice (double): maksimalna cijena
   Odgovor:
   [
    {
        "title": "Generic Motorcycle",
        "description": "The Generic Motorcycle is a versatile and reliable bike suitable for various riding preferences. With a balanced design, it provides a comfortable and efficient riding experience.",
        "category": "motorcycle",
        "price": 3999.99,
        "rating": 4.85,
        "stock": 63,
        "tags": [
            "motorcycles"
        ],
        "brand": "Generic Motors",
        "weight": 9.0,
        "dimensions": {
            "width": 14.87,
            "height": 16.41,
            "depth": 12.98
        },
        "warrantyInformation": "3 months warranty",
        "shippingInformation": "Ships in 2 weeks",
        "availabilityStatus": "In Stock",
        "reviews": [
            {
                "rating": 4,
                "comment": "Would buy again!",
                "date": "2024-05-23T08:56:21.624Z",
                "reviewerName": "Mia Rodriguez",
                "reviewerEmail": "mia.rodriguez@x.dummyjson.com"
            },
            {
                "rating": 5,
                "comment": "Fast shipping!",
                "date": "2024-05-23T08:56:21.624Z",
                "reviewerName": "Owen Sullivan",
                "reviewerEmail": "owen.sullivan@x.dummyjson.com"
            },
            {
                "rating": 3,
                "comment": "Would not buy again!",
                "date": "2024-05-23T08:56:21.624Z",
                "reviewerName": "Nicholas Edwards",
                "reviewerEmail": "nicholas.edwards@x.dummyjson.com"
            }
        ],
        "returnPolicy": "90 days return policy",
        "minimumOrderQuantity": 1,
        "thumbnail": "https://cdn.dummyjson.com/products/images/motorcycle/Generic%20Motorcycle/thumbnail.png",
        "images": [
            "https://cdn.dummyjson.com/products/images/motorcycle/Generic%20Motorcycle/1.png",
            "https://cdn.dummyjson.com/products/images/motorcycle/Generic%20Motorcycle/2.png",
            "https://cdn.dummyjson.com/products/images/motorcycle/Generic%20Motorcycle/3.png",
            "https://cdn.dummyjson.com/products/images/motorcycle/Generic%20Motorcycle/4.png"
        ]
    }
   ...
   ]
4.localhost:8080/api/products/title/{title}
Opis: Dohvaća proizovde po određenom nazivu neovisno o tome dali je veliko ili malo slovo
Parametri: title (String): tekst po kojem se pretražuje
Odgovor:
[
    {
        "title": "Powder Canister",
        "description": "The Powder Canister is a finely milled setting powder designed to set makeup and control shine. With a lightweight and translucent formula, it provides a smooth and matte finish.",
        "category": "beauty",
        "price": 14.99,
        "rating": 3.82,
        "stock": 59,
        "tags": [
            "beauty",
            "face powder"
        ],
        "brand": "Velvet Touch",
        "weight": 8.0,
        "dimensions": {
            "width": 24.16,
            "height": 10.7,
            "depth": 11.07
        },
        "warrantyInformation": "2 year warranty",
        "shippingInformation": "Ships in 1-2 business days",
        "availabilityStatus": "In Stock",
        "reviews": [
            {
                "rating": 5,
                "comment": "Very happy with my purchase!",
                "date": "2024-05-23T08:56:21.618Z",
                "reviewerName": "Ethan Thompson",
                "reviewerEmail": "ethan.thompson@x.dummyjson.com"
            },
            {
                "rating": 4,
                "comment": "Great value for money!",
                "date": "2024-05-23T08:56:21.618Z",
                "reviewerName": "Levi Hicks",
                "reviewerEmail": "levi.hicks@x.dummyjson.com"
            },
            {
                "rating": 5,
                "comment": "Highly impressed!",
                "date": "2024-05-23T08:56:21.618Z",
                "reviewerName": "Hazel Gardner",
                "reviewerEmail": "hazel.gardner@x.dummyjson.com"
            }
        ],
        "returnPolicy": "60 days return policy",
        "minimumOrderQuantity": 25,
        "thumbnail": "https://cdn.dummyjson.com/products/images/beauty/Powder%20Canister/thumbnail.png",
        "images": [
            "https://cdn.dummyjson.com/products/images/beauty/Powder%20Canister/1.png"
        ]
    },
    {
        "title": "Protein Powder",
        "description": "Nutrient-packed protein powder, ideal for supplementing your diet with essential proteins.",
        "category": "groceries",
        "price": 19.99,
        "rating": 3.91,
        "stock": 65,
        "tags": [
            "health supplements"
        ],
        "brand": null,
        "weight": 2.0,
        "dimensions": {
            "width": 5.62,
            "height": 7.88,
            "depth": 23.48
        },
        "warrantyInformation": "1 year warranty",
        "shippingInformation": "Ships in 1-2 business days",
        "availabilityStatus": "In Stock",
        "reviews": [
            {
                "rating": 1,
                "comment": "Very dissatisfied!",
                "date": "2024-05-23T08:56:21.621Z",
                "reviewerName": "Logan Lawson",
                "reviewerEmail": "logan.lawson@x.dummyjson.com"
            },
            {
                "rating": 3,
                "comment": "Poor quality!",
                "date": "2024-05-23T08:56:21.621Z",
                "reviewerName": "Julian James",
                "reviewerEmail": "julian.james@x.dummyjson.com"
            },
            {
                "rating": 1,
                "comment": "Very unhappy with my purchase!",
                "date": "2024-05-23T08:56:21.621Z",
                "reviewerName": "Hazel Evans",
                "reviewerEmail": "hazel.evans@x.dummyjson.com"
            }
        ],
        "returnPolicy": "No return policy",
        "minimumOrderQuantity": 39,
        "thumbnail": "https://cdn.dummyjson.com/products/images/groceries/Protein%20Powder/thumbnail.png",
        "images": [
            "https://cdn.dummyjson.com/products/images/groceries/Protein%20Powder/1.png"
        ]
    }
]
