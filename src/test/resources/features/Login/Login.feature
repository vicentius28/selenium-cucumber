Feature: Login funcionalidad

  @login
  Scenario: Login exitoso
    Given el usuario está en la página de login
    When ingresa credenciales válidas
    Then accede al sistema correctamente

  @login_invalido
  Scenario: Login fallido
    Given el usuario está en la página de login
    When ingresa credenciales inválidas
    Then se muestra un mensaje de error
