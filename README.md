## Till new advice, I'm holding over this practice, It's just taking me too longer and I dont feel inspired now for microservices, JUST FOR NOW.
Maybe I'll do a more decent practice in the future. You can do pull requests if you like to this project if you feel you want to practice with
a premade project.

# Microservice introduction practice

Hi, welcome to my repo. I'm planning to get into Microservices, the design, programming
and deployment of these services, as the design patterns too, security and more stuff.

It's gonna be ruff :c

DIAGRAMS BELOW CAN BE CHANGED OVER THE TIME I'M INVESTING IN THIS REPO

This is the initial schema, for now I'll be designing with diagrams the architecture of this stuff,
hands off first, the general architecture of the small system I'm tryna make

![microservicios-arquitecturav1 0 drawio (1)](https://user-images.githubusercontent.com/78714792/197021480-b172184b-ebb9-43da-804f-687b4e1c344d.png)


Now I have to define the database schemas and the orchestor events with the saga pattern.

For stablishing solid procceses about what I want to do, I'm using use cases so I can set the 
processes that later I'll code, so I dont loose time thinking about what processes to do while
already coding.


![customer login use case](https://user-images.githubusercontent.com/78714792/197019044-89c58244-be59-4e96-a17f-cb270f9246d6.png)

![use_case-inventory-add](https://user-images.githubusercontent.com/78714792/197045239-c03956d2-edb1-4fef-8ec0-f7d6c504e6fd.png)

![user_case-inventory-remove](https://user-images.githubusercontent.com/78714792/197045252-dcde84de-5440-4b6f-9676-2962e2bf67d7.png)

![modoficar corregido](https://user-images.githubusercontent.com/78714792/197047771-19ebeffa-39d6-47da-b0a8-9ff760b200e2.png)

![usecase_sells_savetocart](https://user-images.githubusercontent.com/78714792/197240155-7a8fab54-5bd7-4362-b537-3d08e73d2719.png)

![usercase_sellbuy_checkout](https://user-images.githubusercontent.com/78714792/197240174-c30edc5b-2338-4f41-963d-29492bd92a7a.png)



# RELATIONAL DATABASES
I decided to try another relational to host user credentials instead of MongoDB as I specified in the arquitecture diagram

![Relational DATABASES MICROSERVICES](https://user-images.githubusercontent.com/78714792/197045336-151d4e94-3fa4-43b4-ade0-1d9ff0f24ebe.png)

# NoSQL DATABASES
It's just one :=P

![image](https://user-images.githubusercontent.com/78714792/197046922-1cee2872-fef7-4d7b-9f5a-b8fd18c93ff3.png)

# DOCKER? I think for this practice no

This practice is taking longer than expected, because of two reasons: I'm lazy as fuck and I feel it's taking
longer than expected. I wanted to use docker but the thing is that I want to reserve that topic for later.

While reading about microservices stuff with spring boot, I learned about a way to implement api gateways, by using 
Eureka from Netflix, far easier to use than docker.

![image](https://user-images.githubusercontent.com/78714792/200636964-4f227f47-27d9-4a40-9634-554b36df288f.png)


All microservices hosted, of course I can access them but in theory, if I restrict the access to each microservice from the firewall and
I just assign a DNS to the API Gateway, the clients can just interact with all microservices just from the gateway.



