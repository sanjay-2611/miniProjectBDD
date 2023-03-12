
Feature: Automating demoblaze website

  
  Scenario: Add Item to cart
  
    Given Login Into App
    
    When Add Item to cart
    	|item|
			|Nokia lumia 1520|
#|Samsung galaxy s7|
#|Iphone 6 32gb|
#|Sony vaio i7|    
    Then Item must added to the cart
    


