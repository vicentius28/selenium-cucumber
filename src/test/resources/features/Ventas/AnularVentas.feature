Feature: Anular una Venta

  Background:
    Given el usuario está en la página de login
    When ingresa el usuario "admin" y la contraseña "pointofsale"
    Then accede al sistema correctamente

  @anular_ventas
  Scenario: Anular una Venta
    Given el usuario ingresa al módulo de ventas
    And el usuario ingresa a las ventas diarias
    And selecciona la venta con el cliente "Pepe_test123 Doe" y amount "$22,491.00"
    When presiona el botón de anular venta
    Then acepta el mensaje de confirmación de eliminación de Ventas


