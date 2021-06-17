# PromotionEngine

It is a simple promotion engine for a checkout process

The promotion engine will need to calculate the total order value after applying the promotion defined

The promotion engine allows for more promotion types to be added at a later date

The promotion rules are defined in the Yaml file which can be later modified to consume from any other external modules

The promotion rules and definitions can be redefined in any rulesEngine available like drools,easyRules

These are promoLevels Defined

    ITEM,
    CART,
    COMBO_ITEM

SwaggerAPI Link:  http://localhost:8080/swagger-ui.html#/

API:

http://localhost:8080/v1/cart

## Tech Stack

- Spring Boot
- maven

## Quick Start

- Clone the git repository
- Import maven project in Intelli-J
- mvn clean install
- docker build --tag=promotion .
- docker run -p8080:8080 promotion
- Application starts in 8080 port

