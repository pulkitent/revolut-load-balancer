# Problem statement

We will ask you to analyse and implement one requirement at a time, so please focus on it only.

1. Implement a Load Balancer, which is a database of services, their instances and their
   locations (http://facebook.com). In general, The service registry needs to be updated each time a new service comes
   online

2. For this exercise, we'll build an imitation of a Load Balancer, which will be a simple object. No network calls
   should be made. Data should be stored in-memory instead of a real database

# Specific requirements

- It should be possible to register an instance, identified by an address
- Each address should be unique, it should not be possible to register the same address more than once
- Load Balancer should accept up to 10 addresses
- Develop an algorithm that, when invoking the Load Balancer 's get() method multiple times, should return one
  backend-instance choosing between the registered ones randomly