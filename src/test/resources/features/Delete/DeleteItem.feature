
Feature: Eliminar Items

  @Items_eliminado
  Scenario: Eliminar un Item previamente registrado
    Given el usuario está en la página de login
    When ingresa el usuario "admin" y la contraseña "pointofsale"
    Then accede al sistema correctamente
    And el usuario ingresa al módulo items
    And elimina el item con nombre "Taladro_Test"
