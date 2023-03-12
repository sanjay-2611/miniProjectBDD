
Feature: Automating demoblaze website

  
  Scenario: Add Item to cart
  
    Given Login Into App
    
    When Add Item to cart
    	|item|
			|Samsung galaxy s7| 
			|Nokia lumia 1520|
#			|Iphone 6 32gb|
#			|Sony vaio i7| 
    Then Item must added to the cart
    

	Scenario: Delete an Item
#		Given Login Into App
		When  List of items available in the cart
		Then  Delete an item from cart
		

	Scenario: place order
			When Items should be available in the cart
			Then Place Order
			And purchase items
		