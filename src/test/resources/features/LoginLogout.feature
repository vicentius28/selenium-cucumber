Feature: Login y Logout funcionalidad

  @login_logout
  Scenario: Login y logout exitoso
    Given el usuario está en la página de login
    When ingresa el usuario "admin" y la contraseña "pointofsale"
    Then accede al sistema correctamente
    When cierra sesión
    Then vuelve a la página de login
