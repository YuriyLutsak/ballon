интерфейс - это пульт, его имплементация(сервисИмпл) - телевизор
работаем всегда тольео с интерфейсом  чтоб достучаться до телевизора
(борьба с сильной связаностью - тогда можно сменить телевизор, а пульт остался)


* для чтения можно спокойно брать энтити из базы данных не переводя в дто


код метода latexBalloonToLatexBalloonDTO и его вызовы.
в поле latexBalloon объекта LatexBalloonQuantityInOrder.
 метод latexBalloonToLatexBalloonDTO
  для маппинга поля latexBalloon
 классе LatexBalloon

 LatexBalloonMapper
 LatexBalloonQuantityInOrderMapper
 LatexBalloonQuantityInOrder
 LatexBalloon

при get http://localhost:8080/api/orders я вычислил такую зациклинность
order -> latexBalloonQuantity -> latexBalloon -> latexBalloonQuantity

in latexBalloon обнулить latexBalloonQuantityInOrder







постим:
1
Customer
{

    "name": "name1",
    "phone_number": 1111111111,
    "totalBalance": 100005,
    "orders":null
}
{
    "name": "name2",
    "phone_number": 1111111111,
    "totalBalance": 70005,
    "orders":null
}
{
     "name": "name3",
     "phone_number": 1111111111,
     "totalBalance": 50005,
     "orders":null
 }
 {
     "name": "name4",
     "phone_number": 1111111111,
     "totalBalance": 40009,
     "orders":null
 }



2

взять 2 латекс и 2 фоил
 balloon
 {
     "balloonType": "GEMAR",
     "size": 30,
     "cost": 100,
     "stockBalance": 1111,
     "latexBalloonQuantityInOrder": null,
     "glue": false
 }
{
    "foilBalloonType": "STAR",
        "size": 45,
        "cost": 150,
        "stockBalance": 2222,
        "foilBalloonQuantityInOrders": []
}


3
4 одинаковых ордера с 4 разными кастумерами

order:

 "address": null,
    "dateTime": null,
    "orderStatus": null,
    "totalPrice": 0,
    "customer": {

        "id": "907c2851-51dc-44d8-95e9-a344ca7ac2f7"
    },
    "latexBalloonQuantity": [
        {
            "quantity": 8,
            "latexBalloon": {
               "id": "6981c379-3536-406d-a9f7-493f951ca665"
            }
        }
            {
                    "quantity": 8,
                    "latexBalloon": {
                        "id": "e1515207-0562-499d-9ed6-98a35197df94"
                    }
                }
    ],
    "foilBalloonQuantity": [
        {
            "quantity": 3,
            "foilBalloon": {
               "id": "c20bd606-9623-4396-abaf-553a32b4a945"
            }
        },
          {
                    "quantity": 3,
                    "foilBalloon": {
                         "id": "fa9e7305-3d23-46a3-863e-cd70e9dd0735"
                    }
                }
    ]
}

customer = "id": "907c2851-51dc-44d8-95e9-a344ca7ac2f7"

latex1 = "id": "6981c379-3536-406d-a9f7-493f951ca665",
latex2 =  "id": "e1515207-0562-499d-9ed6-98a35197df94"

foil1 =  "id": "fa9e7305-3d23-46a3-863e-cd70e9dd0735",
foil2 = "id": "c20bd606-9623-4396-abaf-553a32b4a945",


у 1 кастумена должно быть 475 тоталпрайс
у 2 кастумена должно быть 485 тоталпрайс
у 3 кастумена должно быть 495 тоталпрайс
у 4 кастумена должно быть 500 тоталпрайс
