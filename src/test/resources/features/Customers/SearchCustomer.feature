Feature: Eliminar Clientes

  Background:
    Given el usuario está en la página de login
    When ingresa el usuario "admin" y la contraseña "pointofsale"
    Then accede al sistema correctamente

  @cliente_buscado
  Scenario: Buscar un cliente previamente registrado
    Given el usuario ingresa al modulo customers
    When busca cliente de nombre "Pepe_test123" y apellido "Doe"
    Then verifica busqueda de nombre "Pepe_test123" y apellido "Doe"
