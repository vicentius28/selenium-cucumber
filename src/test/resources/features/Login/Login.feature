Feature: Login funcionalidad

  @login
  Scenario: Login exitoso
    Given el usuario está en la página de login
    When ingresa el usuario "admin" y la contraseña "pointofsale"
    Then accede al sistema correctamente

  @login_invalido
  Scenario: Login fallido
    Given el usuario está en la página de login
    When ingresa el usuario "admin" y la contraseña "clave_incorrecta"
    Then se muestra un mensaje de error
