# FoodOrdering Backend

Spring Boot backend for food ordering application.

## Tech Stack
- Spring Boot
- MYSQL
- JWT Authentication
- Docker

## Features
- User authentication
- Restaurant management
- Order management
- Payment integration
```mermaid
flowchart TD

subgraph group_group_bootstrap["Bootstrap"]
  node_node_app(("FoodorderingApp<br/>Spring Boot app"))
end

subgraph group_group_api["API Layer"]
  node_node_auth_controller["Auth API<br/>controller"]
  node_node_user_controller["User API<br/>controller"]
  node_node_restaurant_controller["Restaurant API<br/>controller"]
  node_node_admin_restaurant_controller["Admin Restaurant<br/>controller"]
  node_node_food_controller["Food API<br/>controller"]
  node_node_admin_food_controller["Admin Food<br/>controller"]
  node_node_category_controller["Category API<br/>controller"]
  node_node_ingredients_controller["Ingredients API<br/>controller"]
end

subgraph group_group_security["Security"]
  node_node_config["Security Config<br/>security setup"]
  node_node_jwt_filter["JWT Filter<br/>auth filter"]
  node_node_entrypoint["Auth Entry<br/>auth handler"]
  node_node_access_denied["Access Denied<br/>auth handler"]
end

subgraph group_group_service["Services"]
  node_node_user_service["User Service<br/>[UserService.java]"]
  node_node_catalog_services["Catalog Services<br/>service layer"]
  node_node_custom_user_details["User Details<br/>security service"]
  node_node_jwt_service["JWT Service<br/>token service"]
end

subgraph group_group_persistence["Persistence"]
  node_node_repos[("Repositories<br/>data access")]
end

subgraph group_group_domain["Domain Model"]
  node_node_models{{"Domain Models<br/>entities"}}
end

subgraph group_group_contracts["Contracts"]
  node_node_contracts["API Contracts<br/>dto/request/response"]
end

node_node_app -->|"configures"| node_node_config
node_node_app -->|"starts"| node_node_auth_controller
node_node_config -->|"registers"| node_node_jwt_filter
node_node_config -->|"uses"| node_node_entrypoint
node_node_config -->|"uses"| node_node_access_denied
node_node_jwt_filter -->|"loads user"| node_node_custom_user_details
node_node_auth_controller -->|"calls"| node_node_user_service
node_node_auth_controller -->|"uses"| node_node_contracts
node_node_user_controller -->|"calls"| node_node_user_service
node_node_restaurant_controller -->|"calls"| node_node_catalog_services
node_node_admin_restaurant_controller -->|"calls"| node_node_catalog_services
node_node_food_controller -->|"calls"| node_node_catalog_services
node_node_admin_food_controller -->|"calls"| node_node_catalog_services
node_node_category_controller -->|"calls"| node_node_catalog_services
node_node_ingredients_controller -->|"calls"| node_node_catalog_services
node_node_user_service -->|"persists via"| node_node_repos
node_node_catalog_services -->|"persists via"| node_node_repos
node_node_user_service -->|"uses"| node_node_models
node_node_catalog_services -->|"uses"| node_node_models
node_node_repos -->|"maps"| node_node_models
node_node_contracts -.->|"adapts"| node_node_models
node_node_jwt_service -->|"returns"| node_node_contracts
node_node_jwt_service -->|"integrates"| node_node_user_service

click node_node_app "https://github.com/madhusudan785/foodordering-backend/blob/master/src/main/java/com/authapi/foodordering/FoodorderingApplication.java"
click node_node_config "https://github.com/madhusudan785/foodordering-backend/blob/master/src/main/java/com/authapi/foodordering/config/SecurityConfig.java"
click node_node_jwt_filter "https://github.com/madhusudan785/foodordering-backend/blob/master/src/main/java/com/authapi/foodordering/filters/JwtAuthenticationFilter.java"
click node_node_entrypoint "https://github.com/madhusudan785/foodordering-backend/blob/master/src/main/java/com/authapi/foodordering/config/JwtAuthenticationEntryPoint.java"
click node_node_access_denied "https://github.com/madhusudan785/foodordering-backend/blob/master/src/main/java/com/authapi/foodordering/exception/CustomAccessDeniedhandler.java"
click node_node_auth_controller "https://github.com/madhusudan785/foodordering-backend/blob/master/src/main/java/com/authapi/foodordering/controller/AuthController.java"
click node_node_user_controller "https://github.com/madhusudan785/foodordering-backend/blob/master/src/main/java/com/authapi/foodordering/controller/UserController.java"
click node_node_restaurant_controller "https://github.com/madhusudan785/foodordering-backend/blob/master/src/main/java/com/authapi/foodordering/controller/RestaurantController.java"
click node_node_admin_restaurant_controller "https://github.com/madhusudan785/foodordering-backend/blob/master/src/main/java/com/authapi/foodordering/controller/AdminRestaurantController.java"
click node_node_food_controller "https://github.com/madhusudan785/foodordering-backend/blob/master/src/main/java/com/authapi/foodordering/controller/FoodController.java"
click node_node_admin_food_controller "https://github.com/madhusudan785/foodordering-backend/blob/master/src/main/java/com/authapi/foodordering/controller/AdminFoodController.java"
click node_node_category_controller "https://github.com/madhusudan785/foodordering-backend/blob/master/src/main/java/com/authapi/foodordering/controller/CategoryController.java"
click node_node_ingredients_controller "https://github.com/madhusudan785/foodordering-backend/blob/master/src/main/java/com/authapi/foodordering/controller/IngredientsController.java"
click node_node_user_service "https://github.com/madhusudan785/foodordering-backend/blob/master/src/main/java/com/authapi/foodordering/services/UserService.java"
click node_node_custom_user_details "https://github.com/madhusudan785/foodordering-backend/blob/master/src/main/java/com/authapi/foodordering/services/CustomUserDetailService.java"
click node_node_jwt_service "https://github.com/madhusudan785/foodordering-backend/blob/master/src/main/java/com/authapi/foodordering/services/impl/JwtServiceImpl.java"

classDef toneNeutral fill:#f8fafc,stroke:#334155,stroke-width:1.5px,color:#0f172a
classDef toneBlue fill:#dbeafe,stroke:#2563eb,stroke-width:1.5px,color:#172554
classDef toneAmber fill:#fef3c7,stroke:#d97706,stroke-width:1.5px,color:#78350f
classDef toneMint fill:#dcfce7,stroke:#16a34a,stroke-width:1.5px,color:#14532d
classDef toneRose fill:#ffe4e6,stroke:#e11d48,stroke-width:1.5px,color:#881337
classDef toneIndigo fill:#e0e7ff,stroke:#4f46e5,stroke-width:1.5px,color:#312e81
classDef toneTeal fill:#ccfbf1,stroke:#0f766e,stroke-width:1.5px,color:#134e4a
class node_node_app,node_node_contracts toneBlue
class node_node_auth_controller,node_node_user_controller,node_node_restaurant_controller,node_node_admin_restaurant_controller,node_node_food_controller,node_node_admin_food_controller,node_node_category_controller,node_node_ingredients_controller toneAmber
class node_node_config,node_node_jwt_filter,node_node_entrypoint,node_node_access_denied toneMint
class node_node_user_service,node_node_catalog_services,node_node_custom_user_details,node_node_jwt_service toneRose
class node_node_repos toneIndigo
class node_node_models toneTeal
