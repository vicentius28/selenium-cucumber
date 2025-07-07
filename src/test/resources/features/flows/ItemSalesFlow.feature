Feature: Registrar un ítem y venderlo

  Background:
  Given el usuario está en la página de login
  When ingresa el usuario "admin" y la contraseña "pointofsale"
  Then accede al sistema correctamente

@flujo_item_sales
Scenario: Registrar item y realizar venta con él
  Given el usuario ingresa al módulo items
  And el usuario abre el formulario de nuevo ítem
  When el usuario ingresa nombre "Martillo_test", categoría "Herramientas", costo "20000", precio "30000", stock "3", recibidos "3" y nivel de reorden "1" y presiona submit
  Then se muestra mensaje de éxito tras registro
  And el usuario vuelve al home
  Given el usuario ingresa al módulo de ventas
  And el usuario agrega el producto "Martillo_test" con cantidad "1" y dscto "5" a la venta
  And el usuario selecciona el cliente "Pepe_test123 Doe"
  When el usuario procede al pago
  And selecciona el método de pago "Debit Card" e ingresa el monto exacto
  And el usuario ingresa el comentario "Compra realizada con éxito"
  And confirma la venta
  Then se muestra mensaje de éxito tras registrar la venta
