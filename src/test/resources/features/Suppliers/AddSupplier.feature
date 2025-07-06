Feature: Registrar un supplier

  Background:
    Given el usuario está en la página de login
    When ingresa el usuario "admin" y la contraseña "pointofsale"
    Then accede al sistema correctamente

  @supplier_registrado
  Scenario: Registrar un nuevo supplier con campos mínimos requeridos
    Given el usuario ingresa al módulo suppliers
    And el usuario abre el formulario de nuevo supplier
    When el usuario ingresa company "proveedor_test123", categoría "Goods Supplier", nombre "Test", apellido "Auto" y presiona submit
    Then se muestra mensaje de éxito tras registrar supplier
