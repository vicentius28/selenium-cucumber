Feature: Recepcion

  Background:
    Given el usuario está en la página de login
    When ingresa el usuario "admin" y la contraseña "pointofsale"
    Then accede al sistema correctamente

  @ingresar_recepcion
  Scenario: agrega una nueva recepcion con 1 producto existentes
    Given el usuario ingresa al modulo recepcion
    And el usuario agrega el producto "producto_test1" con cantidad "2" y tipo paquete "x1"
    Then  el usuario llena formluario de pago con proveedor "proveedor_test123", tipo de pago "Debit Card", monto "20000" y comentario "Prueba"
    And el usuario finaliza registro de recepcion