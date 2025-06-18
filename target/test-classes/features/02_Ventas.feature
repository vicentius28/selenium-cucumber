Feature: realizar venta en sistema ospos

  @2 @TEST_ATC_0020 @regresivo
  Scenario: venta en sistema con al menos 2 producto
    Given ingreso a la pagina de inicial de ospos
    When ingreso a la pagina de ventas
    Then agrega item a venta
    And ejecuta pago
