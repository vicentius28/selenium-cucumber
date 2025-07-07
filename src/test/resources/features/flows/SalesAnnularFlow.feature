Feature: Crear y Anular una Venta

  Background:
    Given el usuario está en la página de login
    When ingresa el usuario "admin" y la contraseña "pointofsale"
    Then accede al sistema correctamente

  @crear_anular_ventas
  Scenario: Crear y Anular una Venta
    Given el usuario ingresa al módulo de ventas
    And el usuario agrega el producto "Martillo_test" con cantidad "3" y dscto "3" a la venta
    And el usuario selecciona el cliente "Pepe_test123 Doe"
    When el usuario procede al pago
    And selecciona el método de pago "Debit Card" e ingresa el monto exacto
    And el usuario ingresa el comentario "Compra realizada con éxito"
    And confirma la venta
    Then se muestra mensaje de éxito tras registrar la venta
    And el usuario vuelve al home
    Given el usuario ingresa al módulo de ventas
    And el usuario ingresa a las ventas diarias
    And selecciona la venta con el cliente "Pepe_test123 Doe" y amount "$87,300.00"
    When presiona el botón de anular venta
    Then acepta el mensaje de confirmación de eliminación de Ventas



