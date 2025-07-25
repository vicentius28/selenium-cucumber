Feature: Eliminar Clientes

  Background:
    Given el usuario está en la página de login
    When ingresa el usuario "admin" y la contraseña "pointofsale"
    Then accede al sistema correctamente

  @cliente_eliminado
  Scenario: Eliminar un cliente previamente registrado
    Given el usuario ingresa al modulo customers
    And selecciona al cliente con nombre "Pepe_test123" y apellido "Doe"
    When presiona el botón de eliminar cliente
    And acepta el mensaje de confirmación
