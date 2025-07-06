
Feature: Eliminar Supplier

  Background:
    Given el usuario está en la página de login
    When ingresa el usuario "admin" y la contraseña "pointofsale"
    Then accede al sistema correctamente

  @Supplier_eliminado
  Scenario: Eliminar un proveedor previamente registrado
    Given el usuario ingresa al módulo suppliers
    When elimina el proveedor "proveedor_test123"
    Then se muestra mensaje de éxito tras eliminación de suppliers
