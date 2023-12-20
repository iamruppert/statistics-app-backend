# Project: Data Compilation on the Development of Industry and the State of the Natural Environment in Poland

## Project Description
The project aims to create a web application that visualizes and compiles data on the state of the natural environment and 
industrial development. The data has been carefully selected to convey the desired information effectively. Users can access 
specific information on how industrial development impacts the environment. The application provides filtering options for 
users to view only the desired information. It offers a compilation of data based on selected years and presents comparisons in the form of tables and charts.
Functionalities implemented in the project include data export and import in JSON and XML formats. The export operates by downloading data to the disk in the 
appropriate format. The website requires user accounts, and users have the ability to delete their accounts from the database along with all associated tokens at
any time. The application uses a MySQL database. Additional functionalities involve Object-Relational Mapping (ORM) utilizing Hibernate and Spring Data JPA.
ORM is employed for mapping between entities and tables in the database. Annotations such as @Entity, @Table, @Column assist in this mapping process. JpaRepository 
is another ORM mechanism used for operations on entities, such as filtering and locating specific records. The application leverages these technologies to enhance data management and presentation.

## Team Division

### Łukasz Ziółkiewicz
- Work on code related to data import and export
- Work on code handling the form and user system functionality
- Work on the token and authentication system
- Work on code related to isolation in the database
- Work on ORM for database access
- Work on REST commands

### Kacper Ziemski
- Finding and preparing data for further analysis in the application
- Creating registration and login form
- Work on ORM for database access
- Work on the token and authentication system
- Creating the graphical layer of the application and presenting data on the website

### Michał Zdybel
- Finding and preparing data for further analysis in the application
- Work on code related to data import and export
- Work on REST commands
- Creating the graphical layer of the application and presenting data on the website

## Technologies Used

- React
- SpringBoot
- Hibernate
- JPA
- ORM
- JWT
- SQL database
- REST

## The user can find the following information on the website:

- Amount of produced pollutants categorized by chemical compounds in the years 2005, 2010, 2015, and 2020
- Monetary value, converted to złoty, indicating how much money each sector of the industry generated: mining, electricity, overall industry, and manufacturing industry
- Number of jobs in various industrial sectors
- Average earnings of individuals, considering the value of złoty, working in specific industrial fields
- The amount that a particular industrial branch earned per employee

1. Clone the repository to your local machine.
2. Run the backend using SpringBoot.
3. Run the frontend using React.
4. Configure the connection to the MySQL database.
5. The application should be accessible at `http://localhost:3000` after successful setup.
