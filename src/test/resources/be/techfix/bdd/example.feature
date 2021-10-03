Feature: Testing hello and bye

  Scenario: Say Hello
    Given A user uzair
    When  user call hello
    Then  user should receive message 'Hello there'

  Scenario: Say Good Bye
    Given A user uzair
    When  user call bye
    Then  user should receive message 'Good Bye !!!'
