
Feature: Eliminar Clientes

  @cliente_eliminado
  Scenario: Eliminar un cliente previamente registrado
    Given el usuario está en la página de login
    When ingresa el usuario "admin" y la contraseña "pointofsale"
    Then accede al sistema correctamente
    And el usuario ingresa al modulo customers
    And selecciona al cliente con nombre "Pepe_test123" y apellido "Doe"
    And presiona el botón de eliminar cliente
    And acepta el mensaje de confirmación
    Then el cliente con nombre "Pepe_test123" y apellido "Doe" ya no está en la lista
