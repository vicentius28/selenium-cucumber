Feature: Eliminar Items

  Background:
    Given el usuario está en la página de login
    When ingresa el usuario "admin" y la contraseña "pointofsale"
    Then accede al sistema correctamente

  @Items_eliminado
  Scenario: Eliminar un Item previamente registrado
    Given el usuario ingresa al módulo items
    When elimina el item con nombre "Taladro_Test"
    Then se muestra mensaje de éxito tras eliminación
