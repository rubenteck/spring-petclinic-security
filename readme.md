# Goal
Adding security to this the Petclinic app (because medical data should be private according to the GDPR).

## Functional changes
- A publicly available route:
  http://localhost:8080/vets.html  
  get all veterinarians (publicly available data)
- A secured route without autorization
  http://localhost:8080/owners?lastName=  
  get all users (contains sensitive user data)
- 2 secured routes with autorization:
    - veterinarians only:
      http://localhost:8080/owners/11/pets/14/visits/new   
      only veterinarians can add visits.
    - the user itself only:
      http://localhost:8080/owners/11/edit
      http://localhost:8080/owners/11/pets/new  
      only the user itself can edit its data or add pets
