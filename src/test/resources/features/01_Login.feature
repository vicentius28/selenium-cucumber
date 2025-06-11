Feature: Login funcionalidad
  Scenario: Login y logout exitoso
    Given el usuario está en la página de login
    When ingresa credenciales válidas
    Then accede al sistema correctamente
    And cierra sesión
