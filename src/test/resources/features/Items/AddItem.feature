Feature: Registrar un ítem

  Background:
    Given el usuario está en la página de login
    When ingresa el usuario "admin" y la contraseña "pointofsale"
    Then accede al sistema correctamente

  @item_registrado
  Scenario: Registrar un nuevo ítem
    Given el usuario ingresa al módulo items
    And el usuario abre el formulario de nuevo ítem
    When el usuario ingresa nombre "Taladro_Test", categoría "Herramientas", costo "10000", precio "24990", stock "5", recibidos "5" y nivel de reorden "1" y presiona submit
    And el usuario ingresa nombre "producto_test1", categoría "Prueba", costo "10000", precio "24990", stock "15", recibidos "5" y nivel de reorden "1" y presiona submit
    And el usuario ingresa nombre "producto_test2", categoría "Prueba", costo "15000", precio "29990", stock "20", recibidos "10" y nivel de reorden "1" y presiona submit
    Then se muestra mensaje de éxito tras registro
