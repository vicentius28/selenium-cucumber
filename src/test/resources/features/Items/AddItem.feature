Feature: Registrar un ítem

  @item_registrado
  Scenario: Registrar un nuevo ítem
    Given el usuario está en la página de login
    When ingresa el usuario "admin" y la contraseña "pointofsale"
    Then accede al sistema correctamente
    And el usuario ingresa al módulo items
    And el usuario abre el formulario de nuevo ítem
    And el usuario ingresa nombre "Taladro_Test", categoría "Herramientas", costo "10000", precio "24990", stock "5", recibidos "5" y nivel de reorden "1" y presiona submit
    And elimina el item con nombre "Taladro_Test"

    Then se muestra mensaje de éxito tras registro
