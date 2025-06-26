Feature: Clientes

  @cliente_obligatorio
  Scenario: Validar campos obligatorios vacíos
    Given el usuario está en la página de login
    When ingresa el usuario "admin" y la contraseña "pointofsale"
    Then accede al sistema correctamente
    And el usuario ingresa al modulo customers
    And el usuario abre el formulario de nuevo cliente
    And el usuario deja los campos obligatorios vacios y presiona submit
    Then se muestran errores por campos obligatorios


    @cliente_registrado
    Scenario: Registrar un cliente
      Given el usuario está en la página de login
      When ingresa el usuario "admin" y la contraseña "pointofsale"
      Then accede al sistema correctamente
      And el usuario ingresa al modulo customers
      And el usuario abre el formulario de nuevo cliente
      And el usuario ingresa su nombre "Pepe_test123", apellido "Doe" y presiona submit
      Then se muestra mensaje de exito tras registro
