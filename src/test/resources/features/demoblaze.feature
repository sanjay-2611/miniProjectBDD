
Feature: Automating demoblaze website

  
  Scenario: Add Item to cart
  
    Given Login Into App
    
    When Add Item to cart
    	|item|
			|Samsung galaxy s7|  
    Then Item must added to the cart
    


