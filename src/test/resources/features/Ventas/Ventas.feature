@ventas
Feature: realizar venta en sistema ospos
  Background:
    Given el usuario está en la página de login
    And ingresa credenciales válidas
    And accede al sistema correctamente

  @2 @TEST_ATC_0020 @regresivo
  Scenario: venta en sistema con al menos 2 producto
    Given ingreso a la pagina de ventas
    Then agrega item a venta
    And ejecuta pago
