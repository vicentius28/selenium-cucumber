Feature: Flujo completo de gestión de clientes

  Background:
    Given el usuario está en la página de login
    When ingresa el usuario "admin" y la contraseña "pointofsale"
    Then accede al sistema correctamente

  @flujo_cliente
  Scenario: Registrar, buscar y eliminar cliente
    Given el usuario ingresa al modulo customers
    When el usuario ingresa su nombre "Juan", apellido "Perez" y presiona submit
    When busca cliente de nombre "Juan" y apellido "Perez"
    When presiona el botón de eliminar cliente
    And acepta el mensaje de confirmación
