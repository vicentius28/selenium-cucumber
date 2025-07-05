
Feature: Eliminar Supplier

  @Supplier_eliminado
  Scenario: Eliminar un proveedor previamente registrado
    Given el usuario está en la página de login
    When ingresa el usuario "admin" y la contraseña "pointofsale"
    Then accede al sistema correctamente
    And el usuario ingresa al módulo suppliers
    And elimina el proveedor "proveedor_test123"
