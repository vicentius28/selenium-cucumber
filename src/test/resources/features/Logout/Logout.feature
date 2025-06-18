Feature: Logout funcionalidad

  Background:
    Given el usuario está en la página de login
    And ingresa credenciales válidas
    And accede al sistema correctamente

  @logout
  Scenario: Logout exitoso
    When cierra sesión
    Then vuelve a la página de login
