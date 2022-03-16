

# Welcome to Duke!

# Getting started


# Features and usage



## Patient
There are three types of tasks, add, delete, view.
### Add

`add patient /info nric, fullname, age, gender, address, date of birth `

Example of a valid input:
`add patient /info S1234567A, John Doe, 23, M, 10 Baker Street, 1999-12-31, 2021-02-15`


### Delete

`delete patient /info nric`
Example of a valid input:
`delete patient /info S1234567A`

### View

#### View all patients
`view patient`

#### View specific patient by NRIC
`view patient /info nric`

Example of a valid input:
`view patient /info S1234567A`


## Doctor
There are three types of tasks, add, delete, view.
### Add

`add doctor /info nric, fullname, age, gender, address, specialization `

Example of a valid input:
`add doctor /info S1234567A, John Doe, 23, M, 10 Baker Street, 1999-12-31, Optometry`


### Delete

`delete doctor /info id_in_list`

Example of a valid input:
`delete doctor /info 1`

### View

#### View all doctor
`view doctor`

#### View specific patient by NRIC
`view doctor /info S1234567A`

Example of a valid input:
`view doctor /info S1234567A`

## Medicine
There are three types of tasks, add, delete, view.
### Add
`add medicine /info name, dosage, date of expiry, symptomps, quantity`

Example of a valid input:
`add medicine /info Paracetamol, 500, 2023-06-11, Slight headache, 10`


### Delete

`delete medicine /info id_in_list`

Example of a valid input:
`delete medicine /info 1`

### View

#### View all medicine
`view medicine`

#### View specific medicine by id in list
`view medicine /info id`

Example of a valid input:
`view medicine /info 1`



