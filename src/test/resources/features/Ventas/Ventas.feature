Feature: Realizar venta en sistema OSPOS

  Background:
    Given el usuario está en la página de login
    When ingresa el usuario "admin" y la contraseña "pointofsale"
    Then accede al sistema correctamente

  @ventas
  Scenario: Realizar una venta con al menos 2 productos
    Given el usuario ingresa al módulo de ventas
    And el usuario agrega el producto "producto_test1" con cantidad "1" y dscto "10" a la venta
    And el usuario agrega el producto "producto_test2" con cantidad "2" y dscto "0" a la venta
    And el usuario selecciona el cliente "Pepe_test123 Doe"
    When el usuario procede al pago
    And selecciona el método de pago "Debit Card" e ingresa el monto exacto
    And el usuario ingresa el comentario "Compra realizada con éxito"
    And confirma la venta
    Then se muestra mensaje de éxito tras registrar la venta
