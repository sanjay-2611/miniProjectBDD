Feature: Automating demoblaze website

  
  Scenario: Login into App
  
    Given User is on launch Page    
    When  User Login
    Then  Should display Home Page
			
			
	Scenario Outline: Add items to cart
#		Given Login Into App
		When  Add an item to cart "<item>"
		Then  Items must be added to cart "<item>"
		Examples:
		|item|
		|Nokia lumia 1520|
#		|Samsung galaxy s7|
#		|Iphone 6 32gb|
#		|Sony vaio i7|
		

	Scenario: Delete an item
			When List of items should be available in cart
			Then Delete an item from cart
			
	Scenario: Place Order
			Given Items should be available in cart
			When Place Order
			Then Purchase Items 
		