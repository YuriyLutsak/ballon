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