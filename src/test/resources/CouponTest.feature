Feature: Coupon Adding
  As a Developer
  I want to add a Coupon through API
  So that it can be available to customers.

  Background:
    Given The Endpoint "https://importidbackend.herokuapp.com/api/coupon" is available

  @post-adding
  Scenario: Add Coupon
    When A Coupon Request is sent with values "50% off your first purchase", "50%", "50OFF", "19/10/2022", "AVAILABLE", "Get 50% off any purchase the first time you use Import It"
    Then A Coupon with status 201 is received

  @delete-adding
    Scenario: Delete Coupon
      When A Coupon delete request is sent with id value "12"
      Then A Coupon with status 200 is received

  @get-coupon-by-id
  Scenario: Get Coupon By Id
    When A Coupon selected is sent with id value "2"
    Then A Coupon with status 200 is received