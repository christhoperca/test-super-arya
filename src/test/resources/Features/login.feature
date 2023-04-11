Feature: Test Login and Buy Product Sauce Demo
  Scenario: Login success on saucedemo
    Given Open web url "http://www.saucedemo.com/"
    And Input username "standard_user" and password "secret_sauce"
    When Click login button
    Then Should success login and redirected to homepage
  Scenario Outline: Login success multiple times on saucedemo using scenario outline
    Given Open web url "http://www.saucedemo.com/"
    And Input username "<username>" and password "<password>"
    When Click login button
    Then Should success login and redirected to homepage
   Examples:
    |username|password|
    |standard_user|secret_sauce|
    |standard_user|secret_sauce|
  Scenario Outline: Login failed on saucedemo using scenario outline locked out user
    Given Open web url "http://www.saucedemo.com/"
    And Input username "<username>" and password "<password>"
    When Click login button
    Then Failed to login and show error message locked out user
   Examples:
     |username|password|
     |locked_out_user|secret_sauce|
  Scenario Outline: Login failed on saucedemo using scenario outline mismatch username and password
    Given Open web url "http://www.saucedemo.com/"
    And Input username "<username>" and password "<password>"
    When Click login button
    Then Failed to login and show error message username and password do not match
   Examples:
     |username|password|
     |standard_user|sdqwctnrbdfgqwdqwd|
     |locked_out_user|asdf12edssfadsad|
     |problem_user|asddqwfqfdwqdqwqggg|
     |performance_glitch_user|ccwqdccc|
     |qcfgewfdsqqdwxscasd|secret_sauce|
  Scenario: Login success on saucedemo and sort product from A to Z
    Given Open web url "http://www.saucedemo.com/"
    And Input username "standard_user" and password "secret_sauce"
    When Click login button
    Then Should success login and redirected to product page
    When Sort product to Name A to Z
    Then Filter Name A to Z activated
  Scenario: Login success on saucedemo and sort product from Z to A
    Given Open web url "http://www.saucedemo.com/"
    And Input username "standard_user" and password "secret_sauce"
    When Click login button
    Then Should success login and redirected to product page
    When Sort product to Name Z to A
    Then Filter Name Z to A activated
  Scenario: Login success on saucedemo and sort product from Low to High
    Given Open web url "http://www.saucedemo.com/"
    And Input username "standard_user" and password "secret_sauce"
    When Click login button
    Then Should success login and redirected to product page
    When Sort product to Price Low to High
    Then Filter Price Low to High activated
  Scenario: Login success on saucedemo and sort product from High to Low
    Given Open web url "http://www.saucedemo.com/"
    And Input username "standard_user" and password "secret_sauce"
    When Click login button
    Then Should success login and redirected to product page
    When Sort product to Price High to Low
    Then Filter Price High to Low activated
  Scenario: Login success on saucedemo and Order The Highest Price Product
    Given Open web url "http://www.saucedemo.com/"
    And Input username "standard_user" and password "secret_sauce"
    When Click login button
    Then Should success login and redirected to product page
    When Sort product to Price Low to High
    Then Highest Product Appeared
    When Click The Highest Product Detail
    Then Product Name and Price are the Highest
    When Buy the product
    And Fill Bio
    Then Order Status Created
